import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule}   from '@angular/http' ;

import {AppComponent} from "./app.component";

import {routing, appRoutingProviders} from './app.routing';
import {FormsModule} from "@angular/forms";
import {ReactiveFormsModule} from "@angular/forms";
import {WelcomeComponent} from "./pages/welcome/welcome.components";
import {TasklogComponent} from "./pages/tasklog/tasklog.components";
import {TasklistComponent} from "./pages/tasklist/tasklist.components";
import {TaskaddComponent} from "./pages/taskadd/taskadd.components";
import {ResourcelistComponent} from "./pages/resourcelist/resourcelist.components";
import {PaginationModule} from 'ng2-bootstrap/ng2-bootstrap';
import {ConfirmModule} from 'angular2-bootstrap-confirm';
import {TooltipModule} from 'ng2-bootstrap/ng2-bootstrap';
import {BuylistComponent} from "./pages/buylist/buylist.components";
import {TeamlistComponent} from "./pages/teamlist/teamlist.components";
import {SelectModule} from 'angular2-select';
import {DropdownModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import { Ng2BootstrapModule } from 'ng2-bootstrap/ng2-bootstrap';
import {CalendarModule,MenuModule,PanelMenuModule,MenubarModule} from 'primeng/primeng';
import {CoalselllistComponent} from  './pages/coalselllist/coalselllist.components';
import {CoalsellComponent} from "./pages/coalsell/coalsell.components";
import {MyDatePickerModule } from 'mydatepicker/dist/my-date-picker.module';
import {FinancecelllistComponent} from './pages/financecelllist/financecelllist.components' ;
import {CoalselladdComponent} from "./pages/coalselladd/coalselladd.components";
import {CoalselldetailComponent} from "./pages/coalselldetail/coalselldetail.components";
import {TabViewModule} from 'primeng/primeng';
import {BillinfolistComponent} from "./pages/billinfolist/billinfolist.components";
import {PaymentinfolistComponent} from "./pages/paymentinfolist/paymentinfolist.components";
import {HkinfolistComponent} from "./pages/hkinfolist/hkinfolist.components";
import {PaymentinfozylistComponent} from "./pages/paymentinfozylist/paymentinfozylist.components";
import {UserlistComponent} from "./pages/userlist/userlist.components";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        routing,
        PaginationModule,
        TooltipModule,
        HttpModule,
        ReactiveFormsModule,
        ConfirmModule,
        SelectModule,
        DropdownModule,
        ButtonModule,
        CalendarModule,
        MenuModule,
        PanelMenuModule,
        MenubarModule,
        Ng2BootstrapModule,
        MyDatePickerModule,
        TabViewModule
    ],
    declarations: [
        AppComponent,
        WelcomeComponent,
        TasklogComponent,
        TasklistComponent,
        TaskaddComponent,
        ResourcelistComponent,
        BuylistComponent,
        TeamlistComponent,
        CoalselladdComponent,
        CoalselllistComponent,
        CoalsellComponent,
        CoalselldetailComponent,
        FinancecelllistComponent,
        BillinfolistComponent,
        PaymentinfolistComponent,
        HkinfolistComponent,
        PaymentinfozylistComponent,
        UserlistComponent
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