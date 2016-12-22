import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import { ActivatedRoute, Params } from '@angular/router';

import {CoalsellService} from "../../service/coalsell/coalsell.service";
import {Coalsell} from "../../service/coalsell/coalsell";
declare var __moduleName: string ;
@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'coalsell.html',
    providers:[CoalsellService]
})
export class CoalsellComponent implements OnInit {
    coalsellid:number ;
    coalsell:Coalsell= {} ;
    ngOnInit() {
        this.route.params.forEach((params:Params)=>{
            this.coalsellid = +params['id'];
            console.log("id:"+this.coalsellid);
            this.coalsellService.getCoalsellOne(this.coalsellid).then(data=>{
                console.log("returndata:"+data);
                this.coalsell =data  ;
            });
        })
    }
    constructor(
        private route: ActivatedRoute,
        private coalsellService: CoalsellService
    ) {}

}