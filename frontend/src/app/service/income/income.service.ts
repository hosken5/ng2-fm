import  {Injectable}  from '@angular/core' ;
import  {Http,Headers}  from  '@angular/http' ;
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Income}  from './income';

@Injectable()
export class IncomeService{
    private headers =  new Headers({'Content-type':'application/json'});
    constructor(private http:Http){}
    getIncomes(param):Promise<Income[]>{
        return this.http.post("income/list",param
        ).toPromise()
            .then(response=> {
                var res = response.json()  as Income[] ;
                var opts = []  ;
                for (let i = 0; i < res.length; i++){
                    opts[i] = {
                        value: res[i].id,
                        label: res[i].name
                    };
                }
                return  opts ;
            })
            .catch(this.handleError) ;
    };
    getIncomeList(param):Promise<Income[]>{
        return this.http.post("income/list",param)
            .toPromise()
            .then(response=>response.json() as Income[])
            .catch(this.handleError) ;
    };

    getIncomeListByCoalSellId(coalsellid):Promise<Income[]>{
        return this.http.post("income/listbycoalsellid/"+coalsellid)
            .toPromise()
            .then(response=>response.json() as Income[])
            .catch(this.handleError) ;
    };

    handleError(error:any):Promise<any>{
        console.error('An error occurred',error) ;
        //location.reload(true) ;
        return  Promise.reject(error.message||error) ;
    };
    addOrUpdateIncome(income:Income):Promise<Income>{
        const url = 'income/addorupdate' ;
        return this.http.post(url,JSON.stringify(income),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Income)
            .catch(this.handleError) ;
    }
}