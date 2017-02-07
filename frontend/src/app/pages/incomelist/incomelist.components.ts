import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {IncomeService} from "../../service/income/income.service";
import {Income} from  "../../service/income/income"
import {ModalModule,ModalDirective } from 'ng2-bootstrap';
import {Coalsell} from "../../service/coalsell/coalsell";
declare var __moduleName: string;


@Component({
    moduleId    : __moduleName || module.id,
    selector:'incomelist',
    templateUrl: 'incomelist.html',
    styleUrls:['incomelist.css'],
    providers:[IncomeService]
})
export class IncomelistComponent {


    @Input()  coalsell: Coalsell ;

    ngOnChanges(changes ) {
        for (let propName in changes) {
            if(propName=="coalsell"){
                let chng = changes[propName] ;
                if(chng.currentValue.id){
                    this.load(chng.currentValue.id);
                }
            }
        }
    }

    lastparam:any;
    reload(){
        this.load(this.lastparam);
    }

    load(param){
        this.lastparam = param  ;
        this.incomes = [] ;
        this.incomeService.getIncomeListByCoalSellId(param).then(data=>{
            console.log(data) ;
            this.incomes= data ||[] ;
        }) ;
    }

    constructor(
        private  router : Router,
        private incomeService: IncomeService
    ){}

    public incomes:Income[]  = [] ;
}
