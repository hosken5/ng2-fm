import  {Injectable}  from '@angular/core' ;
import  {Http,Headers}  from  '@angular/http' ;
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Billinfo}  from './billinfo';

@Injectable()
export class BillinfoService{
    private headers =  new Headers({'Content-type':'application/json'});
    constructor(private http:Http){}
    getBillinfos(param):Promise<Billinfo[]>{
        return this.http.post("billinfo/list",param
        ).toPromise()
            .then(response=> {
                var res = response.json()  as Billinfo[] ;
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
    getBillinfoList(param):Promise<Billinfo[]>{
        return this.http.post("billinfo/list",param)
            .toPromise()
            .then(response=>response.json() as Billinfo[])
            .catch(this.handleError) ;
    };
    getBillinfoListByCoalSellId(coalsellid):Promise<Billinfo[]>{
        return this.http.post("billinfo/listbycoalsellid/"+coalsellid)
            .toPromise()
            .then(response=>response.json() as Billinfo[])
            .catch(this.handleError) ;
    };
    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        location.reload(true);
        return  Promise.reject(error.message||error);
    };
    addOrUpdateBillinfo(billinfo:Billinfo):Promise<Billinfo>{
        const url = 'billinfo/addorupdate' ;
        return this.http.post(url,JSON.stringify(billinfo),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Billinfo)
            .catch(this.handleError) ;
    }
}