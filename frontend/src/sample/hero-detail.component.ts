import { Component, Input ,OnInit } from '@angular/core';
import { Hero }  from  './hero'  ;
import {HeroService}  from  './hero.service' ;
import { ActivatedRoute, Params } from '@angular/router';
declare var __moduleName: string;
@Component({
    selector: 'my-hero-detail',
    moduleId    : __moduleName || module.id,
    templateUrl:'hero-detail.component.html',
    styleUrls:['hero-detail.component.css'],
    providers:[HeroService]
})
export class HeroDetailComponent implements  OnInit {
    ngOnInit():void {
        this.route.params.forEach((params:Params)=>{
            let id = +params['id'];
            this.heroService.getHero(id).then(
                hero=>this.hero=hero
            ) ;
        })
    }
    constructor(
        private heroService: HeroService,
        private route: ActivatedRoute) {
    }
    // @Input()
    hero: Hero ;
    goBack(): void {
        window.history.back();
    }

    save():void {
        this.heroService.update(this.hero).then(this.goBack);
    }
}