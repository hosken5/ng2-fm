import {Component} from "@angular/core";
import {Router}  from  '@angular/router' ;
import {OnInit} from "@angular/core";
import {FormControl, FormGroup} from '@angular/forms';
import {DropdownModule} from 'primeng/primeng';
import {CoalsellService} from "../../service/coalsell/coalsell.service";
import {Coalsell} from "../../service/coalsell/coalsell";
import {TeamService} from "../../service/team/team.service";
import {FinancecellService} from "../../service/financecell/financecell.service";
declare var __moduleName: string ;
import {SelectItem} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
@Component({
    moduleId    : __moduleName || module.id,
    templateUrl: 'coalselllist.html',
    styleUrls:['coalselllist.css'],
    providers:[CoalsellService,TeamService,FinancecellService]
})
export class CoalselllistComponent implements OnInit {

    queryCoalsellForm:FormGroup ;
    teaminfo = [];
    financecells= [] ;
    teamid=        new FormControl();
    financecellid= new FormControl();

    myDatePickerOptions= {
        dayLabels:{su: '日', mo: '一', tu: '二', we: '三', th: '四', fr: '五', sa: '六'},
        todayBtnTxt: '今天',
        monthLabels:{ 1: '一月', 2: '二月', 3: '三月', 4: '四月',
                      5: '五月', 6: '六月', 7: '七月', 8: '八月',
                      9: '九月',10: '十月', 11: '十一月', 12: '十二月' },
        dateFormat: 'yyyy-mm-dd',
        firstDayOfWeek: 'mo',
        sunHighlight: true,
        height: '30px',
        width: '150px',
        inline: false,
        showDateFormatPlaceholder:true,
        selectionTxtFontSize: '16px'
    };

    onDateChangedS(event:any) {
        this.queryCoalsellForm.controls["sfydate"].setValue(event.formatted) ;
    }
    onDateChangedE(event:any) {
        this.queryCoalsellForm.controls["efydate"].setValue(event.formatted) ;
    }
    query(){
        console.log(this.queryCoalsellForm.value);
        this.load(this.queryCoalsellForm.value);
    }
    ngOnInit() {
        this.teamService.getTeams().then(data=>{
            console.log(data)  ;
            this.teaminfo = data || [] ;
        });

        this.financecellService.getFinancecells().then(data=>{
            console.log(data)  ;
            this.financecells  = data || [] ;
        });
        this.load({page:1,pageSize:10});
    }
    constructor(
        private teamService: TeamService,
        private router : Router,
        private coalsellService : CoalsellService,
        private financecellService:FinancecellService
    ){
        this.queryCoalsellForm = new FormGroup({
            teamid:this.teamid,
            financecellid:this.financecellid,
            sfydate:new FormControl(),
            efydate:new FormControl(),
            uppercomp:new FormControl(),
            lowercomp:new FormControl()
        });
    }
    public itemsPerPage:number=20 ;
    public coalsells:Coalsell[]  = [] ;
    public totalItems:number = 0;
    public currentPage:number = 1 ;
    public maxSize:number = 11;
    public lastparam = {};

    load(param){
        Object.assign(this.lastparam,param) ;
        this.coalsellService.getCoalsellList(this.lastparam).then(page=>{
            this.coalsells = page.data   ;
            this.totalItems =page.total  ;
            this.itemsPerPage = page.pageSize ;
            this.currentPage = page.page ;
        });
    }
    gotoaddcoalsell(){
        this.router.navigate(['/coalselladd']) ;
    }
    editCoalsell(coalsell){
        this.router.navigate(['/coalselledit',coalsell.id]) ;
    }
    public setPage(pageNo:number):void {
        this.currentPage = pageNo;
    };

    public pageChanged(event:any):void {
        this.load({page:event.page,pageSize:event.itemsPerPage});
    };
    detailCoalsell(coalsell){
        this.router.navigate(['/coalselldetail',coalsell.id]) ;
    }
}