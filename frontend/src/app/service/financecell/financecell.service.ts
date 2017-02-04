import  {Injectable}  from '@angular/core' ;
import  {Http,Headers}  from  '@angular/http' ;
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Financecell}  from './financecell';

@Injectable()
export class FinancecellService{
    private headers =  new Headers({'Content-type':'application/json'});
    constructor(private http:Http){}
    getFinancecells(param):Promise<any[]>{
        return this.http.post("financecell/list",param
        ).toPromise()
            .then(response=> {
                var res = response.json()  as Financecell[] ;
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
    getFinancecellList(param):Promise<Financecell[]>{
        return this.http.post("financecell/list",param)
            .toPromise()
            .then(response=>response.json() as Financecell[])
            .catch(this.handleError) ;
    };
    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    };
    addOrUpdateFinancecell(financecell:Financecell):Promise<Financecell>{
        const url = 'financecell/addorupdate' ;
        return this.http.post(url,JSON.stringify(financecell),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Financecell)
            .catch(this.handleError) ;
    }
}