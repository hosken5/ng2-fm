import {Component} from "@angular/core";
import {OnInit} from "@angular/core";

declare var __moduleName: string ;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'welcome.html'
})
export class WelcomeComponent implements OnInit {
    ngOnInit() {}
}