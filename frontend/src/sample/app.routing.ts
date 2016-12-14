import {ModuleWithProviders}  from '@angular/core';
import {Routes,RouterModule}  from  '@angular/router';
import {HeroesComponent}  from './heroes.component' ;
import {DashboardComponent} from "./dashboard.component";
import {HeroDetailComponent} from "./hero-detail.component";
import {TabsWrapper} from "./tabs/tabs-wrapper.component";
import  {ModalWrapComponent}  from './bootstrap-sample/modals/model.wrap.component' ;
import {Demo} from "./ng2-confirm/Demo";

const appRoutes:Routes=[
    {
        path:'detail/:id',
        component:HeroDetailComponent
    },
    {
        path:'heroes',
        component:HeroesComponent
    },
    {
        path:'tabs-showcase',
        component:TabsWrapper
    },
    {
        path:'dashboard',
        component:DashboardComponent
    }
    ,{
        path:'',
        redirectTo:'/dashboard',
        pathMatch:'full'
    },
    {
        path:'modal-showcase',
        component:ModalWrapComponent
    },
    {
        path:'ng2-confirm-showcase',
        component:Demo
    }
];
export const routing:ModuleWithProviders = RouterModule.forRoot(appRoutes);