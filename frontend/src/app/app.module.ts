import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule}   from '@angular/http' ;

import {AppComponent} from "./app.component";

import {routing, appRoutingProviders} from './app.routing';
import {FormsModule} from "@angular/forms";
import {ReactiveFormsModule} from "@angular/forms";
import {WelcomeComponent} from "./pages/welcome/welcome.components";
import {ResourcelistComponent} from "./pages/resourcelist/resourcelist.components";
import {PaginationModule } from 'ng2-bootstrap/pagination';
import {TooltipModule} from 'ng2-bootstrap/tooltip';
import {TeamlistComponent} from "./pages/teamlist/teamlist.components";
import {SelectModule} from 'angular2-select';
import {DropdownModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import {Ng2BootstrapModule } from 'ng2-bootstrap';
import {CalendarModule,MenuModule,PanelMenuModule,MenubarModule} from 'primeng/primeng';
import {CoalselllistComponent} from  './pages/coalselllist/coalselllist.components';
import {CoalsellComponent} from "./pages/coalsell/coalsell.components";
import {MyDatePickerModule } from 'mydatepicker';
import {FinancecelllistComponent} from './pages/financecelllist/financecelllist.components' ;
import {CoalselladdComponent} from "./pages/coalselladd/coalselladd.components";
import {CoalselldetailComponent} from "./pages/coalselldetail/coalselldetail.components";
import {TabViewModule} from 'primeng/primeng';
import {BillinfolistComponent} from "./pages/billinfolist/billinfolist.components";
import {PaymentinfolistComponent} from "./pages/paymentinfolist/paymentinfolist.components";
import {HkinfolistComponent} from "./pages/hkinfolist/hkinfolist.components";
import {PaymentinfozylistComponent} from "./pages/paymentinfozylist/paymentinfozylist.components";
import {UserlistComponent} from "./pages/userlist/userlist.components";
import {IncomelistComponent} from "./pages/incomelist/incomelist.components";
import { TabsModule } from 'ng2-bootstrap/tabs';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        routing,
        PaginationModule.forRoot(),
        TooltipModule.forRoot(),
        TabsModule.forRoot(),
        HttpModule,
        ReactiveFormsModule,
        SelectModule,
        DropdownModule,
        ButtonModule,
        CalendarModule,
        MenuModule,
        PanelMenuModule,
        MenubarModule,
        Ng2BootstrapModule.forRoot(),
        MyDatePickerModule,
        TabViewModule
    ],
    declarations: [
        AppComponent,
        WelcomeComponent,
        ResourcelistComponent,
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
        UserlistComponent,
        IncomelistComponent
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