import {Component,Input,ViewChild} from "@angular/core";
import {OnInit}  from  "@angular/core";
import {Router}  from  '@angular/router' ;
import {ConfirmOptions, Position} from 'angular2-bootstrap-confirm';
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Positioning} from 'angular2-bootstrap-confirm/position';
import {PaymentinfoService} from "../../service/paymentinfo/paymentinfo.service";
import {Paymentinfo} from  "../../service/paymentinfo/paymentinfo"
import { ModalModule,ModalDirective} from 'ng2-bootstrap/ng2-bootstrap';
import {Paymentinfo} from "../../service/paymentinfo/paymentinfo";
declare var __moduleName: string;

interface ValidationResult {
    [key:string]:boolean;
}
class CustomerValidator {
    static ismoney(control: FormControl): ValidationResult {
        return /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/.test(control.value) ? null:{"ismoney":true}
    }
}

@Component({
    moduleId    : __moduleName || module.id,
    selector:'paymentinfolist',
    templateUrl: 'paymentinfolist.html',
    styleUrls:['paymentinfolist.css'],
    providers:[PaymentinfoService,ConfirmOptions,{provide: Position, useClass: Positioning}]
})
export class PaymentinfolistComponent implements OnInit {

    @ViewChild('staticModal') public staticModal:ModalDirective;

    isedit:boolean  = false ;

    ngOnInit() {
        //this.load({});
    }

    ngOnChanges(changes: SimpleChanges) {
        for (let propName in changes) {
            if(propName=="coalsell"){
                let chng = changes[propName];
                if(chng.currentValue.id){
                    this.load(chng.currentValue.id);
                }
            }
        }
    }

    @Input()  coalsell: Coalsell ;

    parseDate(date){
        if(!date){
            return null ;
        }
        var array = date.split("-") ;
        return  {year:parseInt(array[0]),month:parseInt(array[1]),day:parseInt(array[2])}
    }

    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }
    public openEditView(paymentinfo:Paymentinfo):void {
        this.isedit = true ;
        this.addPaymentinfoForm.reset();
        this.addPaymentinfoForm.setValue(paymentinfo);
        this.hkrqi = this.parseDate(paymentinfo.hkrq) ;
        this.fkrqi = this.parseDate(paymentinfo.fkrq) ;
        this.staticModal.show();

    }
    public openAddView():void {
        this.isedit = false ;
        this.addPaymentinfoForm.reset() ;
        this.hkrqi = "";
        this.fkrqi = "";
        this.staticModal.show();
    }
    saveOrUpdate():void {
        this.coalsellid.setValue(this.coalsell.id);
        for( let key in this.addPaymentinfoForm.value){
            this.addPaymentinfoForm.controls[key].markAsDirty();
            if(!this.addPaymentinfoForm.controls[key].valid){
                console.log("["+key+"] is not valid");
            }
        }

        if(!this.addPaymentinfoForm.valid) return  ;
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

    hkrqi:Any ;
    fkrqi:Any ;
    onDateChangedhkrq(event){
        this.addPaymentinfoForm.controls["hkrq"].setValue(event.formatted);
    }
    onDateChangedfkrq(event){
        this.addPaymentinfoForm.controls["fkrq"].setValue(event.formatted);
    }

    load(param){
        this.lastparam = param ;
        this.paymentinfoService.getPaymentinfoListByCoalSellId(param).then(data=>{
            console.log(data) ;
            this.paymentinfos = data || [];
        }) ;
    }

    myDatePickerOptions= {
        dayLabels:{su: '日', mo: '一', tu: '二', we: '三', th: '四', fr: '五', sa: '六'},
        todayBtnTxt: '今天',
        monthLabels:{ 1: '一月', 2: '二月', 3: '三月', 4: '四月',
            5: '五月', 6: '六月', 7: '七月', 8: '八月',
            9: '九月',10: '十月', 11: '十一月', 12: '十二月' },
        dateFormat: 'yyyy-mm-dd',
        firstDayOfWeek: 'mo',
        sunHighlight: true,
        height: '30px',
        width: '150px',
        inline: false,
        showDateFormatPlaceholder:true,
        selectionTxtFontSize: '12px'
    };
    addPaymentinfoForm :  FormGroup ;
    id             =  new FormControl("")          ;
    coalsellid     =  new FormControl("",Validators.required)          ;
    fkrq           =  new FormControl("",Validators.required)          ;
    fkje           =  new FormControl("",Validators.compose([Validators.required,Validators.maxLength(30),CustomerValidator.ismoney]))          ;
    jxts           =  new FormControl("")          ;
    ll             =  new FormControl("")          ;
    rmtsy          =  new FormControl("")          ;
    cfsy           =  new FormControl("")          ;
    hkrq           =  new FormControl("")          ;
    hkje           =  new FormControl("")          ;
    hkjxts         =  new FormControl("")          ;
    hkll           =  new FormControl("")          ;
    hkrmtsy        =  new FormControl("")          ;
    createtime     =  new FormControl("")          ;
    lastupdatetime =  new FormControl("")          ;
    creator        =  new FormControl("")          ;
    bz             =  new FormControl("")          ;
    jklx           =  new FormControl("",Validators.required)          ;

    constructor(
        private builder:FormBuilder,
        private router : Router,
        private paymentinfoService: PaymentinfoService
    ){
        this.addPaymentinfoForm = builder.group({
            id            :this.id            ,
            coalsellid    :this.coalsellid    ,
            fkrq          :this.fkrq          ,
            fkje          :this.fkje          ,
            jxts          :this.jxts          ,
            ll            :this.ll            ,
            rmtsy         :this.rmtsy         ,
            cfsy          :this.cfsy          ,
            hkrq          :this.hkrq          ,
            hkje          :this.hkje          ,
            hkjxts        :this.hkjxts        ,
            hkll          :this.hkll          ,
            hkrmtsy       :this.hkrmtsy       ,
            createtime    :this.createtime    ,
            lastupdatetime:this.lastupdatetime,
            creator       :this.creator       ,
            bz            :this.bz            ,
            jklx          :this.jklx
        });
        //this.addPaymentinfoForm.valueChanges.subscribe(data => {
        //    //console.log('Form changes', data)
        //    //this.output = data
        //    //this.addPaymentinfoForm
        //})
    }
    public paymentinfos:Paymentinfo[]  = [] ;
}
