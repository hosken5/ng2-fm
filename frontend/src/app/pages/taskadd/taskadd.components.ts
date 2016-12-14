import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {TaskService} from "../../service/task/task.service";
import {Task} from  "../../service/task/task"
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {Router}  from  '@angular/router' ;

declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'taskadd.html',
    styleUrls:['taskadd.css'],
    providers:[TaskService]
})
export class TaskaddComponent implements OnInit {
    private saving : boolean  = false ;
    constructor(
        private  router : Router,
        private taskService:TaskService ,
        private builder:FormBuilder
    ){
        this.form = builder.group({
            taskname:this.taskname,
            description:this.description,
            triggertype:this.triggertype,
            timeunit:this.timeunit,
            time:this.time,
            startHour:this.startHour,
            startMin:this.startMin,
            startSec:this.startSec,
            resource:this.resource,
            cron:this.cron
        }) ;
    }
    ngOnInit() {
        //this.startSec.valueChanges.subscribe((value:string)=>{
        //    console.log(value);
        //})
    }
    form:FormGroup ;
    taskname =     new FormControl("", Validators.required);
    triggertype =  new FormControl(2);
    description =  new FormControl("", Validators.maxLength(200));
    timeunit =     new FormControl("our")  ;
    time =         new FormControl();
    startHour =    new FormControl();
    startMin =     new FormControl();
    startSec =     new FormControl();
    resource =     new FormControl();
    cron =         new FormControl();

    goBack():void {
        //Array.prototype.forEach((control:FormControl)=>{
        //    control.markAsDirty();
        //},this.form.controls) ;
        //console.log(Object.keys(this.form.value));
    }
    task :Task;
    description:string;
    save():void {
        for( let key in this.form.value){
            this.form.controls[key].markAsDirty();
        }

        if(!this.form.valid) return ;

        console.log(this.form.value);

        this.taskService.create(this.form.value).then(()=>{
            this.router.navigate(['/tasklist']) ;
            console.log("succes");
        }).catch((error)=>{
            console.log("failure:"+error);
        });
    }
    //
    //skuValidator(control: FormControl): { [s: string]: boolean } {
    //    if (!control.value.match(/^123/)) {
    //        return {invalidSku: true};
    //    }
    //}
}