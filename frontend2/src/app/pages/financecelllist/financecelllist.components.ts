import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {FinancecellService} from "../../service/financecell/financecell.service";
import { ModalModule,ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';
import {Financecell} from "../../service/financecell/financecell";
declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'financecelllist.html',
    styleUrls:['financecelllist.css'],
    providers:[FinancecellService]
})
export class FinancecelllistComponent implements OnInit {

    @ViewChild('staticModal') public staticModal:ModalDirective;

    ngOnInit() {
        this.load({});
    }
    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }
    public openEditView(financecell:Financecell):void {
        this.addFinancecellForm.setValue(financecell);
        this.staticModal.show();
    }
    saveOrUpdate():void {
        for( let key in this.addFinancecellForm.value){
            this.addFinancecellForm.controls[key].markAsDirty();
        }
        if(!this.addFinancecellForm.valid) return ;
        console.log(this.addFinancecellForm.value);
        this.financecellService.addOrUpdateFinancecell(this.addFinancecellForm.value).then(()=>{
            this.addFinancecellForm.reset();
            this.staticModal.hide() ;
            this.reload();
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });
    }

    load(param){
        this.lastparam = param  ;
        this.financecellService.getFinancecellList(param).then(data=>{
            console.log(data) ;
            this.financecells= data ||[]  ;
        }) ;
    }
    addFinancecellForm:FormGroup ;
    addFinancecellName =  new FormControl("", Validators.required);
    addFinancecellMessage =  new FormControl() ;
    constructor(
        private builder:FormBuilder,
        private  router : Router,
        private financecellService: FinancecellService
    ){
        this.addFinancecellForm = builder.group({
            id:new FormControl(),
            name:this.addFinancecellName,
            message:this.addFinancecellMessage,
        }) ;
    }
    public financecells:Financecell[]  = [] ;
}
