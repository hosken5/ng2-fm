import  {Component}  from  '@angular/core';
import {TabComponent} from "./tab.component";

@Component({
    selector: 'tabs',
    template: `
    <ul>
            <li *ngFor="let tab of tabs" (click)="selectTab(tab)">
                {{ tab.tabTitle }}
            </li>
            <ng-content></ng-content>
    </ul>
  `
})

export class TabsComponent  {
    // typescript needs to know what properties will exist on class instances
    tabs: TabComponent[] = [];

    addTab(tab:TabComponent){
        if(this.tabs.length===0){
            tab.active = true ;
        }
        this.tabs.push(tab) ;
    }

    selectTab(tab:TabComponent) {
        this.tabs.forEach((tab) => {
            tab.active = false;
        });
        tab.active = true
    }

}