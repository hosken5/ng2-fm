import {Injectable}  from  '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Task} from "./task";
import {Page} from "../common/page";
import {TaskaddComponent} from "../../pages/taskadd/taskadd.components";

@Injectable()
export class TaskService{

    private  taskUrl = '/task/list' ;

    constructor (private  http:Http){}

    getTaskes(param):Promise<Page<Task>>{
        return this.http.post(this.taskUrl,param).toPromise()
            .then(response=>response.json() as Page<Task>)
            .catch(this.handleError);
    };

    getTaskesSlowly():Promise<Task[]>{
        // return new   Promise<Task[]>().resolve(HEROES);
        return  new  Promise<Task[]>(resolve =>setTimeout(resolve,2000)).then(
            ()=>this.getTaskes()
        );
    };

    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    }

    getTask(id:number):Promise<Task> {
        return  this.getTaskes().then(heroes=>heroes.find(hero=>hero.id===id)) ;
    }

    private headers =  new Headers({'Content-type':'application/json'});

    startTask(task:Task):Promise<Task>{
        const url = `${this.taskUrl}/start` ;
        return this.http.post(url,JSON.stringify({id:task.id}),{headers:this.headers})
            .toPromise()
            .then(()=>task)
            .catch(this.handleError)
    }

    deleteTask(task:Task):Promise<Task>{
        const url = `${this.taskUrl}/delete` ;
        return this.http.post(url,JSON.stringify({id:task.id}),{headers:this.headers})
            .toPromise()
            .then(()=>task)
            .catch(this.handleError)
    }

    stopTask(task:Task):Promise<Task>{
        const url = `${this.taskUrl}/stop` ;
        return this.http.post(url,JSON.stringify({id:task.id}),{headers:this.headers})
            .toPromise()
            .then(()=>task)
            .catch(this.handleError)
    }
    update (hero:Task):Promise<Task>{
        const url = `${this.taskUrl}/${hero.id}` ;
        return  this.http.put(url,JSON.stringify(hero),{headers:this.headers})
            .toPromise()
            .then(()=>hero)
            .catch(this.handleError);
    }

    create(task:Task):Promise<Task>{
        const url = `${this.taskUrl}/add` ;
        return this.http.post(url,JSON.stringify(task),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Task)
            .catch(this.handleError) ;
    }
    delete(id:number):Promise<void>{
        let url =  `${this.taskUrl}/${id}` ;
        return this.http
            .delete(url,{headers:this.headers})
            .toPromise()
            .then(()=>null)
            .catch(this.handleError);
    }
}