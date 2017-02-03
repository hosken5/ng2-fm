import  {Injectable}  from '@angular/core' ;
import  {Http,Headers}  from  '@angular/http' ;
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Income}  from './income';

@Injectable()
export class IncomeService{
    constructor(private http:Http){}
    getIncomeListByCoalSellId(coalsellid):Promise<Income[]>{
        return this.http.post("income/listbycoalsellid/"+coalsellid,"")
            .toPromise()
            .then(response=>response.json() as Income[])
            .catch(this.handleError) ;
    };
    handleError(error:any):Promise<any>{
        console.error('An error occurred',error) ;
        //location.reload(true) ;
        return  Promise.reject(error.message||error) ;
    };
}