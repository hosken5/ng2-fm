import  { Component, ViewContainerRef } from  '@angular/core' ;
declare var __moduleName: string;

@Component({
    selector:'my-app',
    moduleId    : __moduleName || module.id,
    template:
        `
            <h1>
                    {{title}}        
            </h1>
            <nav>
                <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
                <a routerLink="/heroes"  routerLinkActive="active" >Heroes</a>
                <!--<a routerLink="/tabs-showcase"  routerLinkActive="active" >Tabs-showcase</a>-->
                <a routerLink="/modal-showcase"  routerLinkActive="active" >modal-showcase</a>
                <a routerLink="/ng2-confirm-showcase"  routerLinkActive="active" >ng2-confirm</a>
            </nav>
            <router-outlet></router-outlet>
        `,
    styleUrls:['app.component.css']
})
export class AppComponent {
    private viewContainerRef: ViewContainerRef;

    constructor(viewContainerRef:ViewContainerRef) {
        this.viewContainerRef = viewContainerRef  ;
    }
    title= 'Tour of Heroes' ;

}