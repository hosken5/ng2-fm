import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {Task} from  "../../service/coalbuy/coalbuy"
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Router}  from  '@angular/router' ;
import {CoalbuyService} from "../../service/coalbuy/coalbuy.service";
import {Coalbuy} from "../../service/coalbuy/coalbuy";
import {TeamService} from "../../service/team/team.service";
import {InputTextModule} from 'primeng/primeng';

declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'coalbuyadd.html',
    styleUrls:['coalbuyadd.css'],
    providers:[CoalbuyService,TeamService]
})
export class CoalbuyaddComponent implements OnInit {
    private saving : boolean  = false ;
    constructor(
        private  router : Router,
        private coalbuyService:CoalbuyService ,
        private builder:FormBuilder,
        private teamService:TeamService
    ){
        //teamid:number ;
        //fydate:string  ;
        //rzfs:string  ;
        //rzll:string  ;
        //yfkbl:string  ;
        //ysfs:string  ;
        //mz:string;
        //ch:string;
        //customerid:number;
        //cgdw:string  ;
        //cgmze:string  ;
        //createtime:string  ;
        //lastupdatetime:string  ;

        this.form = builder.group({
            teamid:this.teamid,
            fydate:this.fydate,
            //fydate:this.fydate,
            //rzfs:this.rzfs,
            //rzll:this.rzll,
            //yfkbl:this.yfkbl,
            //ysfs:this.ysfs,
            //mz:this.mz,
            //ch:this.ch,
            //customerid:this.customerid,
            //cgdw:this.cgdw,
            //cgmze:this.cgmze,
            //coalbuyname:this.coalbuyname
            //this.form.addControl('selectSingle', new FormControl());
    }) ;
    }
    ngOnInit() {
        this.teamService.getTeams().then(data=>{
            console.log(data)  ;
            this.teaminfo = data || [] ;
        });
    }
    form:FormGroup ;
    fydate =  new FormControl() ;
    //coalbuyname =  new FormControl() ;
    //coalbuyname =     new FormControl("", Validators.required);
    //triggertype =  new FormControl(2);
    //description =  new FormControl("", Validators.maxLength(200));
    //timeunit =     new FormControl("our")  ;
    //time =         new FormControl();
    //startHour =    new FormControl();
    //startMin =     new FormControl();
    //startSec =     new FormControl();
    //resource =     new FormControl();
    //cron =         new FormControl();
    teamid=        new FormControl();

    teaminfo = [];
    goBack():void {}
    coalbuy :Coalbuy;
    description:string;
    save():void {
        for( let key in this.form.value){
            this.form.controls[key].markAsDirty();
        }

        if(!this.form.valid) return ;

        console.log(this.form.value);

        this.coalbuyService.create(this.form.value).then(()=>{
            this.router.navigate(['/coalbuylist']) ;
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });
    }
}