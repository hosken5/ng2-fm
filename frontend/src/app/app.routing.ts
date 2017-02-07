import {Routes, RouterModule, ExtraOptions} from '@angular/router';
import {AboutComponent} from "./about/components/about.components";
import {ModuleWithProviders} from "@angular/core";
import {WelcomeComponent} from "./pages/welcome/welcome.components";
import {TeamlistComponent} from "./pages/teamlist/teamlist.components";
import {CoalselllistComponent} from "./pages/coalselllist/coalselllist.components";
import {CoalsellComponent} from "./pages/coalsell/coalsell.components";
import {FinancecelllistComponent} from "./pages/financecelllist/financecelllist.components";
import {CoalselladdComponent} from "./pages/coalselladd/coalselladd.components";
import {CoalselldetailComponent} from "./pages/coalselldetail/coalselldetail.components";
import {UserlistComponent} from "./pages/userlist/userlist.components";
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
        path:'coalselladd',
        component:CoalselladdComponent
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
    }
    ,{
        path:'userlist',
        component:UserlistComponent
    }
];

export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes, { useHash: false });
