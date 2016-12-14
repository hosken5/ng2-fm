import { Component, ContentChildren, QueryList, AfterContentInit } from '@angular/core';
import {Tab2} from "./tab2.component";

@Component({
    selector: 'tabs2',
    template:`
    <ul class="nav nav-tabs">
      <li *ngFor="tab of tabs" (click)="selectTab(tab)" [class.active]="tab.active">
        <a href="#">{{tab.title}}</a>
      </li>
    </ul>
    <ng-content></ng-content>
  `
})
export class Tabs2 implements AfterContentInit {

    @ContentChildren(Tab2) tabs: QueryList<Tab2>;

    // contentChildren are set
    ngAfterContentInit() {
        // get all active tabs
        let activeTabs = this.tabs.filter((tab)=>tab.active);

        // if there is no active tab set, activate the first
        if(activeTabs.length === 0) {
            this.selectTab(this.tabs.first);
        }
    }

    selectTab(tab: Tab2){
        // deactivate all tabs
        this.tabs.toArray().forEach(tab => tab.active = false);

        // activate the tab the user has clicked on.
        tab.active = true;
    }

}
