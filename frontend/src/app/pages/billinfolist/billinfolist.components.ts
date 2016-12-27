import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {ConfirmOptions, Position} from 'angular2-bootstrap-confirm';
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Positioning} from 'angular2-bootstrap-confirm/position';
import {BillinfoService} from "../../service/billinfo/billinfo.service";
import {Billinfo} from  "../../service/billinfo/billinfo"
import { ModalModule,ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';
import {Billinfo} from "../../service/billinfo/billinfo";
declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'billinfolist.html',
    styleUrls:['billinfolist.css'],
    providers:[BillinfoService,ConfirmOptions,{provide: Position, useClass: Positioning}]
})
export class BillinfolistComponent implements OnInit {

    @ViewChild('staticModal') public staticModal:ModalDirective;

    ngOnInit() {
        this.load({});
    }
    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }
    public openEditView(billinfo:Billinfo):void {
        this.addBillinfoForm.setValue(billinfo);
        this.staticModal.show();
    }
    saveOrUpdate():void {
        for( let key in this.addBillinfoForm.value){
            this.addBillinfoForm.controls[key].markAsDirty();
        }

        if(!this.addBillinfoForm.valid) return ;
        console.log(this.addBillinfoForm.value);
        this.billinfoService.addOrUpdateBillinfo(this.addBillinfoForm.value).then(()=>{
            this.addBillinfoForm.reset();
            this.staticModal.hide() ;
            this.reload();
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });
    }

    load(param){
        this.lastparam = param  ;
        this.billinfoService.getBillinfoList(param).then(data=>{
            console.log(data) ;
            this.billinfos= data ||[]  ;
        }) ;
    }
    addBillinfoForm:FormGroup ;
    addBillinfoName =  new FormControl("", Validators.required);
    addBillinfoMessage =  new FormControl() ;
    constructor(
        private builder:FormBuilder,
        private  router : Router,
        private billinfoService: BillinfoService
    ){
        this.addBillinfoForm = builder.group({
            id:new FormControl(),
            name:this.addBillinfoName,
            message:this.addBillinfoMessage,
        }) ;
    }
    public billinfos:Billinfo[]  = [] ;
}
