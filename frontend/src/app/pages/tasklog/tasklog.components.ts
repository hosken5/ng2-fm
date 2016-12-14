import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {TaskLogService} from "../../service/tasklog/tasklog.service";
import { ActivatedRoute, Params } from '@angular/router';

declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'tasklog.html',
    providers:[TaskLogService]
})
export class TasklogComponent implements OnInit {
    ngOnInit() {
        this.route.params.forEach((params:Params)=>{
            this.taskid = +params['id'];
            console.log("id:"+this.taskid);
            this.tasklogService.getTaskLogs({page:1,pageSize:15,taskid:this.taskid}).then(page=>{
                console.log(page);
                this.tasklogs = page.data  ;
                this.totalItems =page.total ;
                this.itemsPerPage = page.pageSize ;
                this.currentPage = page.page;
            });
        })
    }
    constructor(
        private route: ActivatedRoute,
        private tasklogService:TaskLogService
    ){};
    taskid:number ;
    tasklogs:TasklogComponent[] = [];

    itemsPerPage:number=20 ;
    public totalItems:number = 0;
    public  currentPage:number = 4 ;
    public maxSize:number = 11;


    public setPage(pageNo:number):void {
        this.currentPage = pageNo;
    }

    public goBack():void{
        window.history.back();
    };

    public pageChanged(event:any):void {
        console.log('Page changed to: ' + event.page);
        console.log('Number items per page: ' + event.itemsPerPage);
        this.tasklogService.getTaskLogs({page:event.page,pageSize:event.itemsPerPage,taskid:this.taskid})
            .then(
                page=>{
                    console.log(page);
                    this.tasklogs = page.data  ;
                    this.totalItems =page.total ;
                    this.itemsPerPage = page.pageSize ;
                    this.currentPage = page.page;
                }
            );
    };
}