import {Component,Input} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {ConfirmOptions, Position} from 'angular2-bootstrap-confirm';
import {Positioning} from 'angular2-bootstrap-confirm/position';
import {TaskService} from "../../service/task/task.service";
import {Task} from  "../../service/task/task"
declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'tasklist.html',
    styleUrls:['tasklist.css'],
    providers:[TaskService,ConfirmOptions,{provide: Position, useClass: Positioning}]
})
export class TasklistComponent implements OnInit {
    ngOnInit() {
        //this.taskService.getTaskes({page:1,pageSize:15}).then(page=>{
        //    console.log(page);
        //    this.tasks = page.data  ;
        //    this.totalItems =page.total ;
        //    this.itemsPerPage = page.pageSize ;
        //    this.currentPage = page.page;
        //});
        this.load({page:1,pageSize:15});
    }
    lastparam:any ;
    reload(){
        this.load(this.lastparam);
    }
    load(param){
        this.lastparam = param  ;
        this.taskService.getTaskes(param).then(page=>{
            console.log(page);
            this.tasks = page.data  ;
            this.totalItems =page.total ;
            this.itemsPerPage = page.pageSize ;
            this.currentPage = page.page;
        });
    }

    constructor(
        private  router : Router,
        private taskService: TaskService
    ){}

    //title: string = 'Are you sure?';
    //message: string = 'Are you really <b>sure</b> you want to do this?';
    //confirmText: string = '确定 <i class="glyphicon glyphicon-ok"></i> ';
    //cancelText: string = '取消 <i class="glyphicon glyphicon-remove"></i>';
    //confirmClicked: boolean = false;
    //cancelClicked: boolean = false;

    public tasks:Task[]  = [] ;

    itemsPerPage:number=20 ;
    public totalItems:number = 0;
    public  currentPage:number = 4 ;
    public maxSize:number = 11;


    public setPage(pageNo:number):void {
        this.currentPage = pageNo;
    };

    public pageChanged(event:any):void {
        this.load({page:event.page,pageSize:event.itemsPerPage});
        //console.log('Page changed to: ' + event.page);
        //console.log('Number items per page: ' + event.itemsPerPage);
        //this.taskService.getTaskes({page:event.page,pageSize:event.itemsPerPage})
        //.then(
        //    page=>{
        //        console.log(page);
        //        this.tasks = page.data  ;
        //        this.totalItems =page.total ;
        //        this.itemsPerPage = page.pageSize ;
        //        this.currentPage = page.page;
        //    }
        //);
    };

    public gotoTaskLog(task:Task){
        this.router.navigate(['/tasklog',task.id]) ;
    }

    public deleteTask(task:Task):void {
        this.taskService.deleteTask(task).then((t)=>{
           this.reload() ;
        });
    }


    public stopTask(task:Task):void {
        task.stopping = true  ;
        this.taskService.stopTask(task).then((t)=>{
            console.log(t);
            task.stopping = false ;
            task.status = 0 ;

        });
    }

    public startTask(task:Task):void {
        task.starting = true  ;
        this.taskService.startTask(task).then((t)=>{
            console.log(t);
            task.starting = false ;
            task.status = 1  ;
        });
    }
}