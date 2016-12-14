import {Injectable}  from  '@angular/core';
import {Hero} from "./hero";
import { Headers, Http } from '@angular/http';
import {HEROES} from "./mock-heroes";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";

@Injectable()
export class HeroService{

    private  heroesUrl = 'app/heroes' ;
    constructor (private  http:Http){}

    getHeroes():Promise<Hero[]>{
        // return Promise.resolve(HEROES);
        return this.http.get(this.heroesUrl)
            .toPromise()
            .then(response=>response.json().data as Hero[])
            .catch(this.handleError);
    };
    getHeroesSlowly():Promise<Hero[]>{
        // return new   Promise<Hero[]>().resolve(HEROES);
        return  new  Promise<Hero[]>(resolve =>setTimeout(resolve,2000)).then(
            ()=>this.getHeroes()
        );
    };
    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    }
    getHero(id:number):Promise<Hero> {
        return  this.getHeroes().then(heroes=>heroes.find(hero=>hero.id===id)) ;
    }

    private headers =  new Headers({'Content-type':'application/json'});

    update (hero:Hero):Promise<Hero>{
        const url = `${this.heroesUrl}/${hero.id}` ;
        return  this.http.put(url,JSON.stringify(hero),{headers:this.headers})
            .toPromise()
            .then(()=>hero)
            .catch(this.handleError);
    }
    create(name:string):Promise<Hero>{
        const url = `${this.heroesUrl}/${name}` ;
        return this.http.put(url,JSON.stringify({name:name}),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Hero)
            .catch(this.handleError) ;
    }
    delete(id:number):Promise<void>{
        let url =  `${this.heroesUrl}/${id}` ;
        return this.http
            .delete(url,{headers:this.headers})
            .toPromise()
            .then(()=>null)
            .catch(this.handleError);
    }
}