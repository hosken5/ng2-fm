import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {TeamService} from "../../service/team/team.service";
import {Team} from  "../../service/team/team"
import { ModalModule,ModalDirective } from 'ng2-bootstrap';
declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'teamlist.html',
    styleUrls:['teamlist.css'],
    providers:[TeamService]
})
export class TeamlistComponent implements OnInit {

    @ViewChild('staticModal') public staticModal:ModalDirective;

    ngOnInit() {
        this.load({});
    }
    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }
    public openEditView(team:Team):void {
        this.addTeamForm.setValue(team);
        this.staticModal.show();
    }
    saveOrUpdate():void {
        for( let key in this.addTeamForm.value){
            this.addTeamForm.controls[key].markAsDirty();
        }
        if(!this.addTeamForm.valid) return ;
        console.log(this.addTeamForm.value);
        this.teamService.addOrUpdateTeam(this.addTeamForm.value).then(()=>{
            this.addTeamForm.reset();
            this.staticModal.hide() ;
            this.reload();
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });
    }

    load(param){
        this.lastparam = param  ;
        this.teamService.getTeamList(param).then(data=>{
            console.log(data) ;
            this.teams= data ||[]  ;
        }) ;
    }
    addTeamForm:FormGroup ;
    addTeamName =  new FormControl("", Validators.required);
    addTeamMessage =  new FormControl() ;
    constructor(
        private builder:FormBuilder,
        private  router : Router,
        private teamService: TeamService
    ){
        this.addTeamForm = builder.group({
            id:new FormControl(),
            name:this.addTeamName,
            message:this.addTeamMessage,
        }) ;
    }
    public teams:Team[]  = [] ;
}
