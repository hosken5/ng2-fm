import {Component, OnInit,ViewContainerRef} from "@angular/core";
import {UserService} from "./service/user/user.service";
import {Menu} from "./service/user/menu";
import {User} from "./service/user/user";
declare var __moduleName: string;


@Component({
    selector: "app-root",
    moduleId    : __moduleName || module.id,
    templateUrl: "app.html",
    styleUrls:["app.css"],
    providers:[UserService]
})
export class AppComponent implements OnInit {
    constructor(
        private   userService:UserService,
        private   viewContainerRef:ViewContainerRef
    ) {
        this.viewContainerRef = viewContainerRef  ;
    }
    menus: Menu[] = [] ;
    user:User = new User();
    ngOnInit() {
        this.userService.getSessionUser().then((user)=>{
            console.log(user);
            this.user = user ;
        });

        this.userService.getUserMenu().then((menus)=>{
            console.log("menus:"+JSON.stringify(menus));
            this.menus = menus ||[];
        });
        console.log("Application component initialized ...");
    }
}