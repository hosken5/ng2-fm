import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Router,Params,ActivatedRoute}  from  '@angular/router' ;
import {CoalsellService} from "../../service/coalsell/coalsell.service";
import {Coalsell} from "../../service/coalsell/coalsell";
import {TeamService} from "../../service/team/team.service";
import {InputTextModule} from 'primeng/primeng';
import {FinancecellService} from "../../service/financecell/financecell.service";

declare var __moduleName: string ;

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
    templateUrl: 'coalselladd.html',
    styleUrls:['coalselladd.css'],
    providers:[CoalsellService,TeamService,FinancecellService]
})
export class EditFormTestComponent implements OnInit {
    constructor(
        private router : ActivatedRoute,
        private route :Router,
        private coalsellService:CoalsellService ,
        private builder:FormBuilder,
        private teamService:TeamService,
        private financecellService : FinancecellService
    ){}
    lowerjsrqv:Object = {} ;
    fyrqv = {} ;
    upperjsrqv = {} ;

    isedit:boolean  = false ;

    ngOnInit() {
        this.teamService.getTeams("").then(data=>{
            console.log(data)  ;
            this.teaminfo = data || [] ;
        });
        this.financecellService.getFinancecells("").then(data=>{
            console.log("financecellService.getFinancecells:",data);
            this.financecellinfo = data ||[] ;
        });

        this.form = this.builder.group({
            id:this.id,

            teamid:this.teamid,

            financecellid:this.financecellid,

            uppercomp:this.uppercomp,

            lowercomp:this.lowercomp,

            ywx:this.ywx,

            rzfs:this.rzfs,

            htzjll:this.htzjll,

            zrll:this.zrll,

            yfkbl:this.yfkbl,

            jsl:this.jsl,

            upperjsrq:this.upperjsrq,

            lowerjsrq:this.lowerjsrq,

            ysfs:this.ysfs,

            fyrq:this.fyrq,

            xshsze:this.xshsze,

            cgmze:this.cgmze,

            rmtzsy:this.rmtzsy,

            upperzjzy:this.upperzjzy,

            lowerzjzy:this.lowerzjzy,

            createtime:this.createtime,

            lastupdatetime:this.lastupdatetime,

            creator:this.creator
        });

        this.router.params.forEach((params:Params)=>{
            this.coalsellid = +params['id'];
            console.log("coalsellid:"+this.coalsellid);
            if(this.coalsellid) {
                console.log("loading coalsellinfo ....")
                this.coalsellService.getCoalsellOne(this.coalsellid).then(data=>{
                    console.log("coalsellService.getCoalsellOne:",data);
                    this.lowerjsrqv = this.parseDate(data.lowerjsrq) ;
                    this.fyrqv =  this.parseDate(data.fyrq) ;
                    this.upperjsrqv =  this.parseDate(data.upperjsrq) ;
                    this.form.patchValue(data);
                    //this.form.patchValue(
                    //    {createtime
                    //:
                    //"2016-12-29",
                    //creator
                    //    :
                    //    "3",
                    //financecellid
                    //    :
                    //    "1",
                    //financecellname
                    //    :
                    //    null,
                    //fyrq
                    //    :
                    //    "2016-12-09",
                    //htzjll
                    //    :
                    //    1,
                    //id
                    //    :
                    //    1,
                    //jsl
                    //    :
                    //    1,
                    //lastupdatetime
                    //    :
                    //    null,
                    //lowercomp
                    //    :
                    //    "1",
                    //lowerjsrq
                    //    :
                    //    "2016-12-08",
                    //lowerzjzy
                    //    :
                    //    null,
                    //rmtzsy
                    //    :
                    //    null,
                    //rzfs
                    //    :
                    //    "1",
                    //teamid
                    //    :
                    //    "1",
                    //teamname
                    //    :
                    //    null,
                    //uppercomp
                    //    :
                    //    "1",
                    //upperjsrq
                    //    :
                    //    "2016-12-04",
                    //upperzjzy
                    //    :
                    //    null,
                    //xshsze
                    //    :
                    //    1,
                    //yfkbl
                    //    :
                    //    1,
                    //ysfs
                    //    :
                    //    "3",
                    //ywx
                    //    :
                    //    "11" ,
                    //zrll
                    //    :
                    //    1
                    //}
                    //);
                    //this.teamid.setValue("3");
                    this.isedit = true;
                });
            }else {
                this.isedit = false;
            }
        })
    }

    parseDate(date){
        if(!date){
            return null ;
        }
        var array = date.split("-") ;
        return  {year:parseInt(array[0]),month:parseInt(array[1]),day:parseInt(array[2])}
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
        selectionTxtFontSize: '16px'
    };

    onDateChangedlowerjsrq(event){
        this.form.controls["lowerjsrq"].setValue(event.formatted);
    }
    onDateChangedupperjsrq(event){
        this.form.controls["upperjsrq"].setValue(event.formatted);
    }
    onDateChangedFyrq(event){
        this.form.controls["fyrq"].setValue(event.formatted);
    }


    coalsellid:any ;

    form:FormGroup ;

    id = new FormControl("") ;

    teamid = new FormControl(null,Validators.required) ;

    financecellid= new FormControl(null,Validators.required) ;

    uppercomp= new FormControl("",Validators.compose([Validators.required,Validators.maxLength(30)]));

    lowercomp= new FormControl("",Validators.required) ;

    ywx= new FormControl("",Validators.required) ;

    rzfs= new FormControl("",Validators.required) ;

    htzjll= new FormControl("",Validators.compose([Validators.required,CustomerValidator.ismoney])) ;

    zrll= new FormControl("",Validators.compose([Validators.required,CustomerValidator.ismoney])) ;

    yfkbl= new FormControl("",Validators.compose([Validators.required,CustomerValidator.ismoney])) ;

    jsl= new FormControl("",Validators.compose([Validators.required]));

    upperjsrq= new FormControl("",Validators.required) ;

    lowerjsrq= new FormControl("",Validators.required) ;

    ysfs= new FormControl("",Validators.required) ;

    fyrq= new FormControl("",Validators.required) ;

    xshsze= new FormControl("",Validators.compose([Validators.required,CustomerValidator.ismoney,Validators.maxLength(9)])) ;

    cgmze= new FormControl("") ;

    rmtzsy= new FormControl("") ;

    upperzjzy= new FormControl("") ;

    lowerzjzy= new FormControl("") ;

    createtime= new FormControl("") ;

    lastupdatetime= new FormControl("") ;

    creator= new FormControl("");

    teaminfo = [{value: '0', label: 'Aech'},
        {value: '1', label: 'Art3mis'},
        {value: '2', label: 'Daito'},
        {value: '3', label: 'Parzival'},
        {value: '4', label: 'Shoto'}];
    financecellinfo= [{value: '0', label: 'Aech'},
        {value: '1', label: 'Art3mis'},
        {value: '2', label: 'Daito'},
        {value: '3', label: 'Parzival'},
        {value: '4', label: 'Shoto'}] ;
    goBack():void {
        window.history.back();
    }
    coalsell :Coalsell;
    description:string;
    save():void {
        for( let key in this.form.value){
            this.form.controls[key].markAsDirty();
        }

        //private route: ActivatedRoute,

        if(!this.form.valid) return ;
        this.coalsellService.create(this.form.value).then(()=>{
            this.route.navigate(['/coalselllist']) ;
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });

    }
}