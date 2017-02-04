import  {Injectable}  from '@angular/core' ;
import  {Http,Headers}  from  '@angular/http' ;
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";
import {Team}  from './team';

@Injectable()
export class TeamService{
    private headers =  new Headers({'Content-type':'application/json'});
    constructor(private http:Http){}
    getTeams(param):Promise<Team[]>{
        return this.http.post("team/list",param
        ).toPromise()
            .then(response=> {
                var res = response.json()  as Team[] ;
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
    getTeamList(param):Promise<Team[]>{
        return this.http.post("team/list",param)
            .toPromise()
            .then(response=>response.json() as Team[])
            .catch(this.handleError) ;
    };
    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    };
    addOrUpdateTeam(team:Team):Promise<Team>{
        const url = 'team/addorupdate' ;
        return this.http.post(url,JSON.stringify(team),{headers:this.headers})
            .toPromise()
            .then(resp=>resp.json().data as Team)
            .catch(this.handleError) ;
    }
}