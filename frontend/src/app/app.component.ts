import {Component, OnInit} from "@angular/core";
declare var __moduleName: string;


@Component({
    selector: "app",
    moduleId    : __moduleName || module.id,
    templateUrl: "app.html",
    styleUrls:["app.css"]
})
export class AppComponent implements OnInit {
    ngOnInit() {
        console.log("Application component initialized ...");
    }
}