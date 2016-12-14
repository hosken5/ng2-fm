import  { Component ,OnInit} from  '@angular/core' ;
import {Router}  from  '@angular/router' ;
import {Hero}  from  './hero';
import {HeroService}  from  './hero.service' ;
declare var __moduleName: string;
@Component({
    selector:'my-dashboard',
    moduleId    : __moduleName || module.id,
    templateUrl:'dashboard.component.html',
    styleUrls:['dashboard.component.css'],
    providers:[HeroService]
})
export class  DashboardComponent implements  OnInit{
    heroes:Hero[] = [] ;
    constructor(
        private  heroService:HeroService,
        private  router : Router
    ){}
    ngOnInit():void {
        this.heroService.getHeroes().then(heroes=>(this.heroes = heroes.slice(1,5)));
    }
    gotoDetail(hero:Hero):void{
        this.router.navigate(['/detail',hero.id]) ;
    }
}