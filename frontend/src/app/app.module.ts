import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import  {HttpModule}   from '@angular/http' ;

import {AppComponent} from "./app.component";
//import {TaskListComponent} from "./todo/components/task-list.component";
//import {AboutComponent} from "./about/components/about.components";
//import {TaskComponent} from "./todo/components/task.component";

import {routing, appRoutingProviders} from './app.routing';
import {FormsModule} from "@angular/forms";
import {ReactiveFormsModule} from "@angular/forms";
import {WelcomeComponent} from "./pages/welcome/welcome.components";
import {TasklogComponent} from "./pages/tasklog/tasklog.components";
import {TasklistComponent} from "./pages/tasklist/tasklist.components";
import {TaskaddComponent} from "./pages/taskadd/taskadd.components";
import {ResourcelistComponent} from "./pages/resourcelist/resourcelist.components";
import { PaginationModule } from 'ng2-bootstrap/ng2-bootstrap';
import {ConfirmModule} from 'angular2-bootstrap-confirm';
import { TooltipModule } from 'ng2-bootstrap/ng2-bootstrap';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        routing,
        PaginationModule,
        TooltipModule,
        HttpModule,
        ReactiveFormsModule,
        ConfirmModule
    ],
    declarations: [
        AppComponent,
        WelcomeComponent,
        TasklogComponent,
        TasklistComponent,
        TaskaddComponent,
        ResourcelistComponent
        ],
    providers: [
        appRoutingProviders
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
    sideNavClosed:boolean = true ;
    constructor() {

    }
}