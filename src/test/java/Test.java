import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongpf on 16/9/27.
 */

public class Test {
    Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String [] args ) throws Exception {

        List<Integer> a = new ArrayList<Integer>(){{
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }}  ;
        for (int i = 0; i < a.size()  ; i++) {
            if(a.get(i)==7){
                a.remove(i) ;
                a.add(i,2);
                a.add(i+1,1);
            }
            System.out.println(a.get(i));
        }
//        System.out.println(a);
    }


    @org.junit.Test
    public void test (){
        java.util.List list = new ArrayList<>() ;

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        System.out.println(list.subList(1,1));

    }

    @org.junit.Test
    public void cronjob ()   throws Exception{
        JobDetail  job = JobBuilder.newJob(HelloJob.class).withIdentity("dummyjobname","sfa").build() ;
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group2")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    @org.junit.Test
    public  void ttt ()  throws   Exception  {

//        JobDetail job = new JobDetail();
//        job.setName("dummyJobName");
//        job.setJobClass(HelloJob.class);
//        //configure the scheduler time
//        SimpleTrigger trigger = new SimpleTrigger();
//        trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
//        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//        trigger.setRepeatInterval(30000);
//        //schedule it
//        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        scheduler.start();
//        scheduler.scheduleJob(job, trigger);

        JobDataMap  jobDataMap =  new JobDataMap () ;
        jobDataMap.put("url","http://gturnquist-quoters.cfapps.io/api/random") ;

        JobDetail  jobDetail = JobBuilder.newJob(HelloJob.class).setJobData(jobDataMap).withIdentity("dummyjobname","sfa").build() ;

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(5).repeatForever())
                .build();

        Scheduler scheduler = null;
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);

    }
}
