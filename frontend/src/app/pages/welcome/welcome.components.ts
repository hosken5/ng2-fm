import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {FormControl, FormGroup} from '@angular/forms';
import {DropdownModule} from 'primeng/primeng';
declare var __moduleName: string ;
import {SelectItem} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'welcome.html',
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

    cars: SelectItem[];
    selectedCar: string;
    constructor() {
        this.cars = [];
        this.cars.push({label: 'Audi', value: 'Audi'});
        this.cars.push({label: 'BMW', value: 'BMW'});
        this.cars.push({label: 'Fiat', value: 'Fiat'});
        this.cars.push({label: 'Ford', value: 'Ford'});
        this.cars.push({label: 'Honda', value: 'Honda'});
        this.cars.push({label: 'Jaguar', value: 'Jaguar'});
        this.cars.push({label: 'Mercedes', value: 'Mercedes'});
        this.cars.push({label: 'Renault', value: 'Renault'});
        this.cars.push({label: 'VW', value: 'VW'});
        this.cars.push({label: 'Volvo', value: 'Volvo'});
    }
}