import {Injectable}  from  '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Page} from "../common/page";
import  {Coalbuy} from "./coalbuy" ;

@Injectable()
export class CoalbuyService{

    private  coalbuyUrl = '/coalbuy' ;

    constructor (private  http:Http){}

    getCoalbuys(param):Promise<Page<Coalbuy>>{
        return this.http.post(`${this.coalbuyUrl}/list`,param).toPromise()
            .then(response=>response.json() as Page<Coalbuy>)
            .catch(this.handleError);
    };

    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    }

    getCoalbuy(id:number):Promise<Coalbuy> {
        return  this.getCoalbuys({}).then(coalbuys=>coalbuys.data.find(hero=>hero.id===id)) ;
    }
    private headers =  new Headers({'Content-type':'application/json'});

    deleteCoalbuy(task:Coalbuy):Promise<Coalbuy>{
        const url = `${this.coalbuyUrl}/delete` ;
        return this.http.post(url,JSON.stringify({id:task.id}),{headers:this.headers})
            .toPromise()
            .then(()=>task)
            .catch(this.handleError)
    }
    update (hero:Coalbuy):Promise<Coalbuy>{
        const url = `${this.coalbuyUrl}/${hero.id}` ;
        return  this.http.put(url,JSON.stringify(hero),{headers:this.headers})
            .toPromise()
            .then(()=>hero)
            .catch(this.handleError);
    }
    create(task:Coalbuy):Promise<Coalbuy>{
        const url = `${this.coalbuyUrl}/add` ;
        return this.http.post(url,JSON.stringify(task),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Coalbuy)
            .catch(this.handleError) ;

    }
    delete(id:number):Promise<void>{
        let url =  `${this.coalbuyUrl}/${id}` ;
        return this.http
            .delete(url,{headers:this.headers})
            .toPromise()
            .then(()=>null)
            .catch(this.handleError);
    }
}