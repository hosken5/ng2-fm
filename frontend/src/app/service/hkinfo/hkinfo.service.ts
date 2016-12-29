import  {Injectable}  from '@angular/core' ;
import  {Http,Headers}  from  '@angular/http' ;
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Hkinfo}  from './hkinfo';

@Injectable()
export class HkinfoService{
    private headers =  new Headers({'Content-type':'application/json'});
    constructor(private http:Http){}
    getHkinfos(param):Promise<Hkinfo[]>{
        return this.http.post("hkinfo/list",param
        ).toPromise()
            .then(response=> {
                var res = response.json()  as Hkinfo[] ;
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
    getHkinfoList(param):Promise<Hkinfo[]>{
        return this.http.post("hkinfo/list",param)
            .toPromise()
            .then(response=>response.json() as Hkinfo[])
            .catch(this.handleError) ;
    };

    getHkinfoListByCoalSellId(coalsellid):Promise<Hkinfo[]>{
        return this.http.post("hkinfo/listbycoalsellid/"+coalsellid)
            .toPromise()
            .then(response=>response.json() as Hkinfo[])
            .catch(this.handleError) ;
    };

    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    };
    addOrUpdateHkinfo(hkinfo:Hkinfo):Promise<Hkinfo>{
        const url = 'hkinfo/addorupdate' ;
        return this.http.post(url,JSON.stringify(hkinfo),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Hkinfo)
            .catch(this.handleError) ;
    }
}