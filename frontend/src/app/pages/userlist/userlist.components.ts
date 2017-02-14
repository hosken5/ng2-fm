import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {UserService} from "../../service/user/user.service";
import { ModalModule,ModalDirective } from 'ng2-bootstrap';
import {User} from "../../service/user/user";
declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'userlist.html',
    styleUrls:['userlist.css'],
    providers:[UserService]
})
export class UserlistComponent implements OnInit {

    @ViewChild('staticModal') public staticModal:ModalDirective;

    //that  =  this  ;
    //validPass(c:FormControl){
    //    return  (!c.value && !that.isedit) ?  {
    //        validPass:false
    //    } : null ;
    //};

    ngOnInit() {
        this.load({});
    }
    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }

    public openAddView(user:User):void {
        this.isedit = false ;
        this.plainpassword.clearValidators();
        this.addUserForm.reset();
        this.staticModal.show();
    }
    public openEditView(user:User):void {
        this.isedit = true  ;
        //this.plainpassword.valid
        this.addUserForm.reset();
        this.errormessage= "";
        this.addUserForm.patchValue(user);
        this.staticModal.show();
    }
    saveOrUpdate():void {

        for( let key in this.addUserForm.value){
            this.addUserForm.controls[key].markAsDirty();
            console.log(key+"_"+ this.addUserForm.controls[key].valid) ;
        }
        if(!this.addUserForm.valid) return ;

        //if(!this.isedit  && !this.plsainpassword.value){
        //    this.plainpassword.invalid(false); return ;
        //}

        console.log(this.addUserForm.value);
        this.userService.addOrUpdateUser(this.addUserForm.value).then((result)=>{
            console.log("userServie.addOrUpdateUser:",result );
            if(result.success){
                this.addUserForm.reset() ;
                this.staticModal.hide() ;
                this.reload() ;
                console.log("succes");
            }else{
                this.errormessage =result.error;
            }
        }).catch((error)=>{
            this.errormessage =error;
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
    errormessage:string="";
    isedit:boolean = false ;
    addUserForm:FormGroup ;
    id             = new FormControl("")  ;
    phone          = new FormControl("")  ;
    name           = new FormControl("",Validators.required)  ;
    loginname      = new FormControl("",Validators.required)  ;
    plainpassword  = new FormControl("") ;
    email          = new FormControl("",Validators.required)  ;
    activated      = new FormControl("",Validators.required)  ;
    creator        = new FormControl("")  ;
    //createtime     = new FormControl("")  ;
    role           = new FormControl("",Validators.required)  ;

    constructor(
        private builder:FormBuilder,
        private  router : Router,
        private userService: UserService
    ){
        this.addUserForm = builder.group({
                 id           :this.id ,
                 name         :this.name  ,
                 phone        :this.phone        ,
                 loginname    :this.loginname    ,
                 plainpassword     :this.plainpassword     ,
                 email        :this.email        ,
                 activated    :this.activated    ,
                 creator      :this.creator      ,
                 //createtime   :this.createtime   ,
                 role         :this.role
        },{
            validator:(control)=>{
                var id =  control.controls.id ;
                var plainpassword = control.controls.plainpassword;
                if(!id.value && !plainpassword.value){
                    return {
                        password:true
                    }
                }
            }
        }) ;
    }
    public users:User[]  = [] ;
}
