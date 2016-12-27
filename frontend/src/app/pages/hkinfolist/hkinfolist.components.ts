import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {ConfirmOptions, Position} from 'angular2-bootstrap-confirm';
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Positioning} from 'angular2-bootstrap-confirm/position';
import {HkinfoService} from "../../service/hkinfo/hkinfo.service";
import {Hkinfo} from  "../../service/hkinfo/hkinfo"
import { ModalModule,ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';
import {Hkinfo} from "../../service/hkinfo/hkinfo";
declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'hkinfolist.html',
    styleUrls:['hkinfolist.css'],
    providers:[HkinfoService,ConfirmOptions,{provide: Position, useClass: Positioning}]
})
export class HkinfolistComponent implements OnInit {

    @ViewChild('staticModal') public staticModal:ModalDirective;

    ngOnInit() {
        this.load({});
    }
    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }
    public openEditView(hkinfo:Hkinfo):void {
        this.addHkinfoForm.setValue(hkinfo);
        this.staticModal.show();
    }
    saveOrUpdate():void {
        for( let key in this.addHkinfoForm.value){
            this.addHkinfoForm.controls[key].markAsDirty();
        }

        if(!this.addHkinfoForm.valid) return ;
        console.log(this.addHkinfoForm.value);
        this.hkinfoService.addOrUpdateHkinfo(this.addHkinfoForm.value).then(()=>{
            this.addHkinfoForm.reset();
            this.staticModal.hide() ;
            this.reload();
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });
    }

    load(param){
        this.lastparam = param  ;
        this.hkinfoService.getHkinfoList(param).then(data=>{
            console.log(data) ;
            this.hkinfos= data ||[]  ;
        }) ;
    }
    addHkinfoForm:FormGroup ;
    addHkinfoName =  new FormControl("", Validators.required);
    addHkinfoMessage =  new FormControl() ;
    constructor(
        private builder:FormBuilder,
        private  router : Router,
        private hkinfoService: HkinfoService
    ){
        this.addHkinfoForm = builder.group({
            id:new FormControl(),
            name:this.addHkinfoName,
            message:this.addHkinfoMessage,
        }) ;
    }
    public hkinfos:Hkinfo[]  = [] ;
}
