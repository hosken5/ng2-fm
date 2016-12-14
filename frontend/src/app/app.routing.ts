import {Routes, RouterModule, ExtraOptions} from '@angular/router';
import {TaskListComponent} from "./todo/components/task-list.component";
import {AboutComponent} from "./about/components/about.components";
import {ModuleWithProviders} from "@angular/core";
import {WelcomeComponent} from "./pages/welcome/welcome.components";
import {TasklogComponent} from "./pages/tasklog/tasklog.components";
import {TasklistComponent} from "./pages/tasklist/tasklist.components";
import {ResourcelistComponent} from "./pages/resourcelist/resourcelist.components";
import {TaskaddComponent} from "./pages/taskadd/taskadd.components";

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
    }
];

export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes, { useHash: false });
