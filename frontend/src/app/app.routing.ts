import {Routes, RouterModule, ExtraOptions} from '@angular/router';
import {TaskListComponent} from "./todo/components/task-list.component";
import {AboutComponent} from "./about/components/about.components";
import {ModuleWithProviders} from "@angular/core";
import {WelcomeComponent} from "./pages/welcome/welcome.components";
import {TasklogComponent} from "./pages/tasklog/tasklog.components";
import {TasklistComponent} from "./pages/tasklist/tasklist.components";
import {ResourcelistComponent} from "./pages/resourcelist/resourcelist.components";
import {TaskaddComponent} from "./pages/taskadd/taskadd.components";
import {BuylistComponent} from "./pages/buylist/buylist.components";
import {TeamlistComponent} from "./pages/teamlist/teamlist.components";
import {CoalselllistComponent} from "./pages/coalselllist/coalselllist.components";
import {CoalsellComponent} from "./pages/coalsell/coalsell.components";
import {FinancecelllistComponent} from "./pages/financecelllist/financecelllist.components";
import {CoalselladdComponent} from "./pages/coalselladd/coalselladd.components";
import {CoalselldetailComponent} from "./pages/coalselldetail/coalselldetail.components";
import {BillinfolistComponent} from "./pages/billinfolist/billinfolist.components";
import {PaymentinfolistComponent} from "./pages/paymentinfolist/paymentinfolist.components";
import {HkinfolistComponent} from "./pages/hkinfolist/hkinfolist.components";

const appRoutes: Routes = <Routes>[
    {
        path:'',
        redirectTo:'coalselllist',
        pathMatch:'full'
    },
    {
        path:'welcome',
        component:WelcomeComponent
    },
    {
        path:'tasklog/:id',
        component:TasklogComponent
    },
    {
        path:'tasklist',
        component:TasklistComponent
    },
    {
        path:'resourcelist',
        component:ResourcelistComponent
    },
    {
        path:'taskadd',
        component:TaskaddComponent
    },
    {
        path:'coalselladd',
        component:CoalselladdComponent
    },
    {
        path:'buylist',
        component:BuylistComponent
    },
    {
        path:'teamlist',
        component:TeamlistComponent
    },
    {
        path:'coalselllist',
        component:CoalselllistComponent
    },
    {
        path:'coalsell/:id',
        component:CoalsellComponent
    },
    {
        path:'coalselledit/:id',
        component:CoalselladdComponent
    },
    {
        path:'financecell',
        component:FinancecelllistComponent
    },{
        path:'coalselldetail/:id',
        component:CoalselldetailComponent
    },
    //{
    //    path:'billinfolist',
    //    component:BillinfolistComponent
    //},
    //{
    //    path:'paymentinfolist',
    //    component:PaymentinfolistComponent
    //}
    //,{
    //    path:'hkinfolist',
    //    component:HkinfolistComponent
    //},
];

export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes, { useHash: false });
