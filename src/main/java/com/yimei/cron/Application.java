package com.yimei.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by hongpf on 16/5/17.
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
public class Application {
    static Logger   log = LoggerFactory.getLogger(Application.class) ;
    public static void main(String [] args ) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"), InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

//    @Autowired
//    TaskService taskService ;

//
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

//    @Autowired
//    RestTemplate restTemplate;


//    @Override
//    public void run(String... strings) throws Exception {
//        taskService.runAllTask();
////        try {
////            // Grab the Scheduler instance from the Factory
////            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
////            scheduler.start();
////            scheduler.shutdown();
////        } catch (SchedulerException se) {
////            se.printStackTrace();
////        }
//    }
}