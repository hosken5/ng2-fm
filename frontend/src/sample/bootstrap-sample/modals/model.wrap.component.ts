import  { Component,ViewChild } from  '@angular/core' ;
import { ModalModule,ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';
declare var __moduleName: string;

@Component({
    //selector:'modal-wrap',
    moduleId    : __moduleName || module.id,
    templateUrl:'model.wrap.component.html'
})
export class ModalWrapComponent {

    @ViewChild('childModal') public childModal:ModalDirective;

    public showChildModal():void {
        this.childModal.show();
    }

    public hideChildModal():void {
        this.childModal.hide();
    }
    title='modal-showcase'

}