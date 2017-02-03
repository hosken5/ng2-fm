import  {Injectable}  from '@angular/core' ;
import  {Http,Headers}  from  '@angular/http' ;
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {User}  from './user';
import {Menu} from "./menu";

@Injectable()
export class UserService{
    private headers =  new Headers({'Content-type':'application/json'});
    constructor(private http:Http){}
    getUsers(param):Promise<User[]>{
        return this.http.post("user/list",param
        ).toPromise()
            .then(response=> {
                var res = response.json()  as User[] ;
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
    getSessionUser():Promise<User>{
        return  this.http.post("user/session","").toPromise()
            .then(response=>response.json() as User).catch(this.handleError);
    };
    getUserMenu():Promise<Menu[]>{
        return  this.http.post("user/menu","").toPromise().then(response=>response.json() as Menu[]).catch(this.handleError);
    };
    getUserList(param):Promise<User[]>{
        return this.http.post("user/list",param)
            .toPromise()
            .then(response=>response.json() as User[])
            .catch(this.handleError) ;
    };
    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    };
    addOrUpdateUser(user:User):Promise<User>{
        const url = 'user/addorupdate' ;
        return this.http.post(url,JSON.stringify(user),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as User)
            .catch(this.handleError) ;
    }
}