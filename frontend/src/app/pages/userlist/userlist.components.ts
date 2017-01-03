import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {ConfirmOptions, Position} from 'angular2-bootstrap-confirm';
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Positioning} from 'angular2-bootstrap-confirm/position';
import {UserService} from "../../service/user/user.service";
import { ModalModule,ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';
import {User} from "../../service/user/user";
declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'userlist.html',
    styleUrls:['userlist.css'],
    providers:[UserService,ConfirmOptions,{provide: Position, useClass: Positioning}]
})
export class UserlistComponent implements OnInit {

    @ViewChild('staticModal') public staticModal:ModalDirective;

    ngOnInit() {
        this.load({});
    }
    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }
    public openEditView(user:User):void {
        this.addUserForm.setValue(user);
        this.staticModal.show();
    }
    saveOrUpdate():void {
        for( let key in this.addUserForm.value){
            this.addUserForm.controls[key].markAsDirty();
        }
        if(!this.addUserForm.valid) return ;
        console.log(this.addUserForm.value);
        this.userService.addOrUpdateUser(this.addUserForm.value).then(()=>{
            this.addUserForm.reset() ;
            this.staticModal.hide() ;
            this.reload() ;
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });
    }

    load(param){
        this.lastparam = param  ;
        this.userService.getUserList(param).then(data=>{
            console.log(data) ;
            this.users= data ||[]  ;
        }) ;
    }
    addUserForm:FormGroup ;
    id             = new FormControl("",Validators.required)  ;
    phone          = new FormControl("",Validators.required)  ;
    loginname      = new FormControl("",Validators.required)  ;
    password       = new FormControl("",Validators.required)  ;
    passwordsalt   = new FormControl("",Validators.required)  ;
    email          = new FormControl("",Validators.required)  ;
    activated      = new FormControl("",Validators.required)  ;
    creator        = new FormControl("",Validators.required)  ;
    createtime     = new FormControl("",Validators.required)  ;
    role           = new FormControl("",Validators.required)  ;

    constructor(
        private builder:FormBuilder,
        private  router : Router,
        private userService: UserService
    ){
        this.addUserForm = builder.group({
                 id           :this.id ,
                 phone        :this.phone        ,
                 loginname    :this.loginname    ,
                 password     :this.password     ,
                 //passwordsalt :this.passwordsalt ,
                 email        :this.email        ,
                 activated    :this.activated    ,
                 creator      :this.creator      ,
                 createtime   :this.createtime   ,
                 role         :this.role
        }) ;
    }
    public users:User[]  = [] ;
}
