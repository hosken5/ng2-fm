import {Component, OnInit,ViewContainerRef} from "@angular/core";
declare var __moduleName: string;
import {PanelMenuModule,MenuItem} from 'primeng/primeng';

@Component({
    selector: "app",
    moduleId    : __moduleName || module.id,
    templateUrl: "app.html",
    styleUrls:["app.css"],
})
export class AppComponent implements OnInit {
    private items: MenuItem[];
    private viewContainerRef: ViewContainerRef;

    constructor(viewContainerRef:ViewContainerRef) {
        this.viewContainerRef = viewContainerRef  ;
    }

    ngOnInit() {
        console.log("Application component initialized ...");
        this.items=[
            {
                label:"欢迎",
                routerLink: ['/welcome']
            },{
                label:"上游采购信息录入",
                routerLink: ['/coalbuy']
            },
        ] ;
    }
}