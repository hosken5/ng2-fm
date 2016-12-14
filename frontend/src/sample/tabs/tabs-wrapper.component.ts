import  {Component}  from  '@angular/core';
import {Tabs2} from "./tabs2.component";
import {Tab2} from "./tab2.component";

@Component({
     //selector: 'tabs',
    template: `
       <tabs>
            <tab  tabTitle="Foo" >Foo Content</tab>
            <tab  tabTitle="Bar" >Bar Content</tab>
       </tabs>
  `
})
export class TabsWrapper {}