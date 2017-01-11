import {Component,Input,ViewChild,SimpleChanges} from "@angular/core";
import {OnInit,OnChanges} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {ConfirmOptions, Position} from 'angular2-bootstrap-confirm';
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Positioning} from 'angular2-bootstrap-confirm/position';
import {BillinfoService} from "../../service/billinfo/billinfo.service";
import {Billinfo} from  "../../service/billinfo/billinfo"
import { ModalModule,ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';
import {Coalsell} from "../../service/coalsell/coalsell";
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
    selector:'billinfolist',
    templateUrl: 'billinfolist.html',
    styleUrls:['billinfolist.css'],
    providers:[BillinfoService,ConfirmOptions,{provide: Position, useClass: Positioning}]
})
export class BillinfolistComponent implements OnInit ,OnChanges {

    @ViewChild('staticModal') public staticModal : ModalDirective;

    @Input()  coalsell: Coalsell ;


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
    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }
    public openEditView(billinfo:Billinfo):void {
        this.addBillinfoForm.setValue(billinfo);
        this.kpdatei =  this.parseDate (billinfo.kpdate );
        this.staticModal.show();
    }
    openAddView(){
        this.addBillinfoForm.reset();
        this.kpdatei="" ;
        this.staticModal.show();
    }

    parseDate(date){
        if(!date){
            return null ;
        }
        var array = date.split("-") ;
        return  {year:parseInt(array[0]),month:parseInt(array[1]),day:parseInt(array[2])}
    }

    saveOrUpdate():void {

        this.coalsellid.setValue(this.coalsell.id);
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
        this.billinfoService.getBillinfoListByCoalSellId(param).then(data=>{
            console.log(data) ;
            this.billinfos= data ||[]  ;
        });
    }
    addBillinfoForm:FormGroup ;
    id   =       new FormControl("");

    coalsellid = new FormControl("",Validators.required)  ;

    amount     = new FormControl("",Validators.compose([Validators.required,Validators.maxLength(30),CustomerValidator.ismoney])) ;

    quantity   = new FormControl("",Validators.compose([Validators.required,Validators.maxLength(30),Validators.pattern('^[0-9]*[1-9][0-9]*$')])) ;

    createtime = new FormControl("") ;

    lastupdatetime= new FormControl("") ;

    creator= new FormControl("") ;
    kpdatei="" ;
    kpdate = new FormControl("",Validators.compose([Validators.required,Validators.maxLength(30)]));

    bz  = new FormControl("") ;


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


    onDateChangedkpdate(event){
        this.addBillinfoForm.controls["kpdate"].setValue(event.formatted);
    }

    constructor(
        private builder:FormBuilder,
        private  router : Router,
        private billinfoService: BillinfoService
    ){
        this.addBillinfoForm = builder.group({
            id:this.id ,
            coalsellid:this.coalsellid,
            amount:this.amount,
            quantity:this.quantity,
            createtime:this.createtime,
            lastupdatetime:this.lastupdatetime,
            creator:this.creator,
            bz:this.bz,
            kpdate:this.kpdate
        }) ;
    }
    public billinfos:Billinfo[]  = [] ;
}
