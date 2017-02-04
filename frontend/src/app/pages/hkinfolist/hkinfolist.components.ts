import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {HkinfoService} from "../../service/hkinfo/hkinfo.service";
import {Hkinfo} from  "../../service/hkinfo/hkinfo"
import { ModalModule,ModalDirective } from 'ng2-bootstrap';
import {Coalsell} from "../../service/coalsell/coalsell";
declare var __moduleName: string;


interface ValidationResult {
    [key:string]:boolean;
}
class CustomerValidator {
    static ismoney(control: FormControl): ValidationResult {
        return  /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/.test(control.value) ? null:{"ismoney":true}
    }
}

@Component({
    moduleId    : __moduleName || module.id,
    selector:'hkinfolist',
    templateUrl: 'hkinfolist.html',
    styleUrls:['hkinfolist.css'],
    providers:[HkinfoService]
})
export class HkinfolistComponent {

    @ViewChild('staticModal') public staticModal:ModalDirective;

    isedit:boolean  = false ;

    @Input()  coalsell: Coalsell ;

    ngOnChanges(changes) {
        for (let propName in changes) {
            if(propName=="coalsell"){
                let chng = changes[propName] ;
                //console.log("loading chngaaaa"+JSON.stringify(chng.currentValue));
                //console.log("loading chng"+JSON.stringify(chng.currentValue.id));
                if(chng.currentValue.id){
                    this.load(chng.currentValue.id);
                }
            }
        }
    }

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
    public openEditView(hkinfo:Hkinfo):void {
        this.isedit = true ;
        this.addHkinfoForm.setValue(hkinfo);
        this.hkrqi = this.parseDate(hkinfo.hkrq) ;
        this.dqri = this.parseDate(hkinfo.dqr) ;
        this.staticModal.show();
    }
    public openAddView():void {
        this.isedit = false ;
        this.addHkinfoForm.reset();
        this.hkrqi = "";
        this.dqri ="" ;
        this.staticModal.show();
    }
    saveOrUpdate():void {
        this.coalsellid.setValue(this.coalsell.id);
        for( let key in this.addHkinfoForm.value){
            this.addHkinfoForm.controls[key].markAsDirty();
            if(!this.addHkinfoForm.controls[key].valid){
                console.log("["+key+"] is not valid");
            }
        }

        if(!this.addHkinfoForm.valid){
            return ;
        }
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
        this.hkinfoService.getHkinfoListByCoalSellId(param).then(data=>{
            console.log(data) ;
            this.hkinfos= data ||[] ;
        }) ;
    }

    addHkinfoForm:FormGroup ;

    id     = new FormControl("");

    coalsellid = new FormControl("",Validators.required)  ;

    hkrq   = new FormControl("",Validators.required)  ;

    hkrqi  : any;

    hkje   = new FormControl("",Validators.compose([Validators.required,Validators.maxLength(30),CustomerValidator.ismoney])) ;

    hkfs   = new FormControl("",Validators.required)  ;

    ll     = new FormControl("") ;

    dqr = new FormControl("")   ;

    dqri:any ;

    txts = new FormControl("")  ;

    txx = new FormControl("")  ;
    bz = new FormControl("")  ;

    createtime = new FormControl("") ;

    lastupdatetime= new FormControl("") ;

    creator= new FormControl("") ;

    pmje = new  FormControl("") ;


    onDateChangedhkrq(event){
        this.addHkinfoForm.controls["hkrq"].setValue(event.formatted);

    }
    onDateChangeddqr(event){
        this.addHkinfoForm.controls["dqr"].setValue(event.formatted);
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



    constructor(
        private builder:FormBuilder,
        private  router : Router,
        private hkinfoService: HkinfoService
    ){
        this.addHkinfoForm = builder.group({
            id:this.id ,
            coalsellid:this.coalsellid,
            hkrq:this.hkrq,
            hkje:this.hkje,
            hkfs:this.hkfs,
            ll:this.ll,
            dqr:this.dqr,
            txts:this.txts,
            txx:this.txx,
            bz:this.bz,
            createtime:this.createtime,
            lastupdatetime:this.lastupdatetime,
            creator:this.creator,
            pmje:this.pmje
        }) ;

        this.addHkinfoForm.valueChanges.subscribe(data=>{
            console.log(data);
            if(data.dqr!=null && data.hkrq!=null){
                var start = new Date(data.dqr.replace(/-/g,"/")).getTime() ;
                var end = new Date(data.hkrq.replace(/-/g,"/")).getTime() ;
              var  temp =   (start -end  ) / (24*60*60*1000)  ;
              console.log(""+temp);
              if(temp != data.txts ){
                  this.addHkinfoForm.controls["txts"].setValue(temp);
              }
            }else  if(data.txts){
                this.addHkinfoForm.controls["txts"].reset();
            }
        }) ;

        this.addHkinfoForm.controls["txts"].valueChanges.subscribe(data=>{
            this.cacltxx() ;
        });
        this.addHkinfoForm.controls["pmje"].valueChanges.subscribe(data=>{
            this.cacltxx() ;
        });
        this.addHkinfoForm.controls["ll"].valueChanges.subscribe(data=>{
            this.cacltxx() ;
        });

    }
    cacltxx(){
        if( this.hkfs.value=='2'){
            var pmje:number = + this.addHkinfoForm.controls["pmje"].value ;
            var ll  =this.addHkinfoForm.controls["ll"].value  ;
            var txts =this.addHkinfoForm.controls["txts"].value  ;
            var oritxx = this.addHkinfoForm.controls["txts"].value ;
            var txx:number  =  + (pmje * ll * txts /36000*1.17).toFixed(2) ;
            if (oritxx!=txx ){
                this.addHkinfoForm.controls["txx"].setValue(txx);
            }
            if(pmje-txx){
                this.addHkinfoForm.controls["hkje"].setValue(pmje-txx);
            }else {
                this.hkje.reset();
            }
        }
    }

    public hkinfos:Hkinfo[]  = [] ;
}
