import  {Injectable}  from '@angular/core' ;
import  {Http,Headers}  from  '@angular/http' ;
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Paymentinfo}  from './paymentinfo';

@Injectable()
export class PaymentinfoService{
    private headers =  new Headers({'Content-type':'application/json'});
    constructor(private http:Http){}
    getPaymentinfos(param):Promise<Paymentinfo[]>{
        return this.http.post("paymentinfo/list",param
        ).toPromise()
            .then(response=> {
                var res = response.json()  as Paymentinfo[] ;
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
    getPaymentinfoList(param):Promise<Paymentinfo[]>{
        return this.http.post("paymentinfo/list",param)
            .toPromise()
            .then(response=>response.json() as Paymentinfo[])
            .catch(this.handleError) ;
    };
    getPaymentinfoListByCoalSellId(param):Promise<Paymentinfo[]>{
        return this.http.post("paymentinfo/listbycoalsellid/"+param)
            .toPromise()
            .then(response=>response.json() as Paymentinfo[])
            .catch(this.handleError) ;
    };
    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    };
    addOrUpdatePaymentinfo(paymentinfo:Paymentinfo):Promise<Paymentinfo>{
        const url = 'paymentinfo/addorupdate' ;
        return this.http.post(url,JSON.stringify(paymentinfo),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Paymentinfo)
            .catch(this.handleError) ;
    }
}