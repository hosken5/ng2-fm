export class Task {
    id:number  ;
    taskname:string ;
    description:string;
    triggertype:string;
    cron:string ;
    resource:string ;
    startHour:number ;
    startMin:number ;
    startSec:number ;
    creatdatetime:string ;
    creator:string ;
    lastUpdateTime:string ;
    lastaccessstarttime:string ;
    lastaccessendtime:string ;
    lastaccessmsg:string ;
    status:number ;
    stopping:boolean ;
    starting:boolean ;
}
