import {Component} from "@angular/core";
import {OnInit} from "@angular/core";

declare var __moduleName: string;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'about.html'
})
export class AboutComponent implements OnInit {
    ngOnInit() {
    }
}