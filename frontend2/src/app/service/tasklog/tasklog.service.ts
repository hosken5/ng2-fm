import  {Injectable}  from '@angular/core' ;

import  {Http,Headers}  from  '@angular/http' ;
import {TaskLog} from "./tasklog";
import {TasklogComponent} from "../../pages/tasklog/tasklog.components";
import {Page} from "../common/page";
import 'rxjs/add/operator/toPromise';
import {toPromise} from "rxjs/operator/toPromise";


@Injectable()
export class TaskLogService{
    constructor(private http:Http){}

    getTaskLogs(param):Promise<Page<TaskLog>>{
        return this.http.post("tasklog/list",param
        ).toPromise()
            .then(response=>response.json() as Page<Tasklog>)
            .catch(this.handleError);
    };

    handleError(error:any):Promise<any>{
        console.error('An error occurred',error);
        return  Promise.reject(error.message||error);
    }
}