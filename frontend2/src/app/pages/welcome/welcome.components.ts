import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {FormControl, FormGroup} from '@angular/forms';
declare var __moduleName: string ;
@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'welcome.html',
})
export class WelcomeComponent implements OnInit {
    date = "" ;
    myDatePickerOptions= {
        dayLabels:{su: '日', mo: '一', tu: '二', we: '三', th: '四', fr: '五', sa: '六'},
        todayBtnTxt: '今天',
        monthLabels:{ 1: '一月', 2: '二月', 3: '三月', 4: '四月',
            5: '五月', 6: '六月', 7: '七月', 8: '八月', 9: '九月', 10: '十月', 11: '十一月', 12: '十二月' },
        dateFormat: 'yyyy-mm-dd',
        firstDayOfWeek: 'mo',
        sunHighlight: true,
        height: '34px',
        width: '260px',
        inline: false,
        showDateFormatPlaceholder:true,
        //disableUntil: {year: 2016, month: 8, day: 10},
        selectionTxtFontSize: '16px'
    };
    form: FormGroup;
    myOptions = [];
    ngOnInit() {
        //this.myDatePickerOptions =
        //this.myOptions = [
        //    {value: 'a', label: 'Alpha'},
        //    {value: 'b', label: 'Beta'},
        //    {value: 'c', label: 'Gamma'},
        //];
        //this.form = new FormGroup({});
        //this.form.addControl('mySelect', new FormControl());
    }
    //
    //cars: SelectItem[];
    //selectedCar: string;
    constructor() {
        //this.cars = [];
        //this.cars.push({label: 'Audi', value: 'Audi'});
        //this.cars.push({label: 'BMW', value: 'BMW'});
        //this.cars.push({label: 'Fiat', value: 'Fiat'});
        //this.cars.push({label: 'Ford', value: 'Ford'});
        //this.cars.push({label: 'Honda', value: 'Honda'});
        //this.cars.push({label: 'Jaguar', value: 'Jaguar'});
        //this.cars.push({label: 'Mercedes', value: 'Mercedes'});
        //this.cars.push({label: 'Renault', value: 'Renault'});
        //this.cars.push({label: 'VW', value: 'VW'});
        //this.cars.push({label: 'Volvo', value: 'Volvo'});
    }
}