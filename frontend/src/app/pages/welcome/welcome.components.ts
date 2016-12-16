import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {FormControl, FormGroup} from '@angular/forms';
declare var __moduleName: string ;

@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'welcome.html'
})
export class WelcomeComponent implements OnInit {
    form: FormGroup;
    myOptions = [];
    ngOnInit() {
        this.myOptions = [
            {value: 'a', label: 'Alpha'},
            {value: 'b', label: 'Beta'},
            {value: 'c', label: 'Gamma'},
        ];
        this.form = new FormGroup({});
        this.form.addControl('mySelect', new FormControl());
    }
}