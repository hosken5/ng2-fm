import {Injectable}  from  '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Page} from "../common/page";
import  {Coalsell} from "./coalsell" ;

@Injectable()
export class CoalsellService{

    private  coalsellUrl = '/coalsell' ;

    constructor (private  http:Http){
    }
    getCoalsellList(param):Promise<Page<Coalsell>>{
        return this.http.post(`${this.coalsellUrl}/list`,param).toPromise()
            .then(response=>response.json() as Page<Coalsell>)
            .catch(this.handleError);
    };

    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    }

    getCoalsellOne(id:number):Promise<Coalsell> {
       return  this.http.post("/coalsell/query/"+id,"").toPromise()
        .then(response=>response.json() as Coalsell)
        .catch(this.handleError)
    }

    private headers =  new Headers({'Content-type':'application/json'});

    create(coalsell:Coalsell):Promise<Coalsell>{
        const url = `${this.coalsellUrl}/add` ;
        return this.http.post(url,JSON.stringify(coalsell),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Coalsell)
            .catch(this.handleError) ;
    }


    //deleteCoalsell(task:Coalsell):Promise<Coalsell>{
    //    const url = `${this.coalsellUrl}/delete` ;
    //    return this.http.post(url,JSON.stringify({id:task.id}),{headers:this.headers})
    //        .toPromise()
    //        .then(()=>task)
    //        .catch(this.handleError)
    //}
    //update (hero:Coalsell):Promise<Coalsell>{
    //    const url = `${this.coalsellUrl}/${hero.id}` ;
    //    return  this.http.put(url,JSON.stringify(hero),{headers:this.headers})
    //        .toPromise()
    //        .then(()=>hero)
    //        .catch(this.handleError);
    //}
    //create(task:Coalsell):Promise<Coalsell>{
    //    const url = `${this.coalsellUrl}/add` ;
    //    return this.http.post(url,JSON.stringify(task),{headers:this.headers})
    //        .toPromise()
    //        .then(resp=>resp.json().data as Coalsell)
    //        .catch(this.handleError) ;
    //
    //}
    //delete(id:number):Promise<void>{
    //    let url =  `${this.coalsellUrl}/${id}` ;
    //    return this.http
    //        .delete(url,{headers:this.headers})
    //        .toPromise()
    //        .then(()=>null)
    //        .catch(this.handleError);
    //}
}