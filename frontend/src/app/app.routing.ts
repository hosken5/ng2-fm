import {Routes, RouterModule, ExtraOptions} from '@angular/router';
import {TaskListComponent} from "./todo/components/task-list.component";
import {AboutComponent} from "./about/components/about.components";
import {ModuleWithProviders} from "@angular/core";
import {WelcomeComponent} from "./pages/welcome/welcome.components";
import {TasklogComponent} from "./pages/tasklog/tasklog.components";
import {TasklistComponent} from "./pages/tasklist/tasklist.components";
import {ResourcelistComponent} from "./pages/resourcelist/resourcelist.components";
import {TaskaddComponent} from "./pages/taskadd/taskadd.components";
import {BuyComponent}  from "./pages/buy/buy.components"
import {BuylistComponent} from "./pages/buylist/buylist.components";
import {TeamlistComponent} from "./pages/teamlist/teamlist.components";
const appRoutes: Routes = <Routes>[
    {
        path:'',
        redirectTo:'welcome',
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
        path:'buy',
        component:BuyComponent
    },
    {
        path:'buylist',
        component:BuylistComponent
    },
    {
        path:'teamlist',
        component:TeamlistComponent
    }

];

export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes, { useHash: false });
