import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {Task} from  "../../service/coalsell/coalsell"
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Router}  from  '@angular/router' ;
import {CoalsellService} from "../../service/coalsell/coalsell.service";
import {Coalsell} from "../../service/coalsell/coalsell";
import {TeamService} from "../../service/team/team.service";
import {InputTextModule} from 'primeng/primeng';

declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'coalselladd.html',
    styleUrls:['coalselladd.css'],
    providers:[CoalsellService,TeamService]
})
export class CoalselladdComponent implements OnInit {

    constructor(
        private router : Router,
        private coalsellService:CoalsellService ,
        private builder:FormBuilder,
        private teamService:TeamService
    ){
        this.form = builder.group({
            teamid:this.teamid,
            fydate:this.fydate,
        });
    }

    ngOnInit() {
        this.teamService.getTeams().then(data=>{
            console.log(data)  ;
            this.teaminfo = data || [] ;
        });
    }
    form:FormGroup ;

    teamid = new FormControl() ;

    financecellid= new FormControl() ;

    uppercomp= new FormControl();

    lowercomp= new FormControl() ;

    ywx= new FormControl() ;

    rzfs= new FormControl() ;

    htzjll= new FormControl() ;

    zrll= new FormControl() ;

    yfkbl= new FormControl() ;

    jsl= new FormControl() ;

    upperjsrq= new FormControl() ;

    lowerjsrq= new FormControl() ;

    ysfs= new FormControl() ;

    fyrq= new FormControl() ;

    xshsze= new FormControl() ;

    cgmze= new FormControl() ;

    rmtzsy= new FormControl() ;

    upperzjzy= new FormControl() ;

    lowerzjzy= new FormControl() ;

    createtime= new FormControl() ;

    lastupdatetime= new FormControl() ;

    creator:string;

    fydate =  new FormControl() ;
    teaminfo = [];
    goBack():void {}
    coalsell :Coalsell;
    description:string;
    save():void {
        for( let key in this.form.value){
            this.form.controls[key].markAsDirty();
        }
        if(!this.form.valid) return ;
        console.log(this.form.value);
        this.coalsellService.create(this.form.value).then(()=>{
            this.router.navigate(['/coalselllist']) ;
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });
    }
}