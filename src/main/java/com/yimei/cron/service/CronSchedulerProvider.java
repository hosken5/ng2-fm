package com.yimei.cron.service;

import com.yimei.cron.config.ExtMyBatisConfig;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hongpf on 16/10/13.
 */
@Configuration
@AutoConfigureAfter(ExtMyBatisConfig.class)
public class CronSchedulerProvider {

    @Autowired
    UrlJobListener urlJobListener  ;

    @Bean
    public Scheduler  getScheduler(){
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.getListenerManager().addJobListener(urlJobListener);
            return  scheduler ;
        } catch (SchedulerException e) {
            throw new  RuntimeException("SchedulerException",e) ;
        }
    }
}