import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {ConfirmOptions, Position} from 'angular2-bootstrap-confirm';
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Positioning} from 'angular2-bootstrap-confirm/position';
import {PaymentinfoService} from "../../service/paymentinfo/paymentinfo.service";
import {Paymentinfo} from  "../../service/paymentinfo/paymentinfo"
import { ModalModule,ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';
import {Paymentinfo} from "../../service/paymentinfo/paymentinfo";
declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'paymentinfolist.html',
    styleUrls:['paymentinfolist.css'],
    providers:[PaymentinfoService,ConfirmOptions,{provide: Position, useClass: Positioning}]
})
export class PaymentinfolistComponent implements OnInit {

    @ViewChild('staticModal') public staticModal:ModalDirective;

    ngOnInit() {
        this.load({});
    }
    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }
    public openEditView(paymentinfo:Paymentinfo):void {
        this.addPaymentinfoForm.setValue(paymentinfo);
        this.staticModal.show();
    }
    saveOrUpdate():void {
        for( let key in this.addPaymentinfoForm.value){
            this.addPaymentinfoForm.controls[key].markAsDirty();
        }

        if(!this.addPaymentinfoForm.valid) return ;
        console.log(this.addPaymentinfoForm.value);
        this.paymentinfoService.addOrUpdatePaymentinfo(this.addPaymentinfoForm.value).then(()=>{
            this.addPaymentinfoForm.reset();
            this.staticModal.hide() ;
            this.reload();
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });
    }

    load(param){
        this.lastparam = param  ;
        this.paymentinfoService.getPaymentinfoList(param).then(data=>{
            console.log(data) ;
            this.paymentinfos= data ||[]  ;
        }) ;
    }
    addPaymentinfoForm:FormGroup ;
    addPaymentinfoName =  new FormControl("", Validators.required);
    addPaymentinfoMessage =  new FormControl() ;
    constructor(
        private builder:FormBuilder,
        private  router : Router,
        private paymentinfoService: PaymentinfoService
    ){
        this.addPaymentinfoForm = builder.group({
            id:new FormControl(),
            name:this.addPaymentinfoName,
            message:this.addPaymentinfoMessage,
        }) ;
    }
    public paymentinfos:Paymentinfo[]  = [] ;
}
