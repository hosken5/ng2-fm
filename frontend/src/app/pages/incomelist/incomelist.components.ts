import {Component,Input,ViewChild} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {ConfirmOptions, Position} from 'angular2-bootstrap-confirm';
import {FormGroup,Validators,FormBuilder,FormControl} from  '@angular/forms';
import {IncomeService} from "../../service/income/income.service";
import {Income} from  "../../service/income/income"
import {ModalModule,ModalDirective } from 'ng2-bootstrap/ng2-bootstrap';
import {Income} from "../../service/income/income";
declare var __moduleName: string;


@Component({
    moduleId    : __moduleName || module.id,
    selector:'incomelist',
    templateUrl: 'incomelist.html',
    styleUrls:['incomelist.css'],
    providers:[IncomeService]
})
export class IncomelistComponent implements OnInit {


    @Input()  coalsell: Coalsell ;

    ngOnChanges(changes: SimpleChanges) {
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
