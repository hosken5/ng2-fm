import {Component, Input} from  '@angular/core'  ;
import {TabsComponent} from "./tabs.component";

@Component({
    selector: 'tab',
    template: `
    <div [hidden]="!active">
      <ng-content></ng-content>
    </div>
  `
})

export class TabComponent {
    @Input() tabTitle;
    active:boolean ;
    constructor(tabs: TabsComponent) {
        tabs.addTab(this)
    }
}