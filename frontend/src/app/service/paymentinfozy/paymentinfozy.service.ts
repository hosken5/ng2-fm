import  {Injectable}  from '@angular/core' ;
import  {Http,Headers}  from  '@angular/http' ;
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Paymentinfozy}  from './paymentinfozy';

@Injectable()
export class PaymentinfozyService{
    private headers =  new Headers({'Content-type':'application/json'});
    constructor(private http:Http){}
    getPaymentinfozys(param):Promise<Paymentinfozy[]>{
        return this.http.post("paymentinfozy/list",param
        ).toPromise()
            .then(response=> {
                var res = response.json()  as Paymentinfozy[] ;
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
    getPaymentinfozyList(param):Promise<Paymentinfozy[]>{
        return this.http.post("paymentinfozy/list",param)
            .toPromise()
            .then(response=>response.json() as Paymentinfozy[])
            .catch(this.handleError) ;
    };
    getPaymentinfozyListByCoalSellId(param):Promise<Paymentinfozy[]>{
        return this.http.post("paymentinfozy/listbycoalsellid/"+param)
            .toPromise()
            .then(response=>response.json() as Paymentinfozy[])
            .catch(this.handleError) ;
    };
    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    };
    addOrUpdatePaymentinfozy(paymentinfozy:Paymentinfozy):Promise<Paymentinfozy>{
        const url = 'paymentinfozy/addorupdate' ;
        return this.http.post(url,JSON.stringify(paymentinfozy),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Paymentinfozy)
            .catch(this.handleError) ;
    }
}