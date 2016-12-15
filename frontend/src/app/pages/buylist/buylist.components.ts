import {Component,Input} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {ConfirmOptions, Position} from 'angular2-bootstrap-confirm';
import {Positioning} from 'angular2-bootstrap-confirm/position';
import {CoalbuyService} from "../../service/coalbuy/coalbuy.service";

declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'buylist.html',
    styleUrls:['buylist.css'],
    providers:[CoalbuyService,ConfirmOptions,{provide: Position, useClass: Positioning}]
})

export class BuylistComponent implements OnInit {
    ngOnInit() {
        this.load({page:1,pageSize:15});
    }
    lastparam:any ;
    reload(){
        this.load(this.lastparam);
    }
    load(param){
        this.lastparam = param  ;
        this.coalbuyService.getCoalbuys(param).then(page=>{
            console.log(page);
            this.coalbuys = page.data  ;
            this.totalItems =page.total ;
            this.itemsPerPage = page.pageSize ;
            this.currentPage = page.page;
        });
    }

    constructor(
        private  router : Router,
        private coalbuyService: CoalbuyService
    ){}

    public coalbuys:Task[]  = [] ;

    itemsPerPage:number=20 ;
    public totalItems:number = 0;
    public  currentPage:number = 4 ;
    public maxSize:number = 11;


    public setPage(pageNo:number):void {
        this.currentPage = pageNo;
    };

    public pageChanged(event:any):void {
        this.load({page:event.page,pageSize:event.itemsPerPage});
    };

    public gotoTaskLog(coalbuy:Task){
        this.router.navigate(['/coalbuylog',coalbuy.id]) ;
    }

    public deleteTask(coalbuy:Task):void {
        this.coalbuyService.deleteTask(coalbuy).then((t)=>{
           this.reload() ;
        });
    }


    public stopTask(coalbuy:Task):void {
        coalbuy.stopping = true  ;
        this.coalbuyService.stopTask(coalbuy).then((t)=>{
            console.log(t);
            coalbuy.stopping = false ;
            coalbuy.status = 0 ;

        });
    }

    public startTask(coalbuy:Task):void {
        coalbuy.starting = true  ;
        this.coalbuyService.startTask(coalbuy).then((t)=>{
            console.log(t);
            coalbuy.starting = false ;
            coalbuy.status = 1  ;
        });
    }
}