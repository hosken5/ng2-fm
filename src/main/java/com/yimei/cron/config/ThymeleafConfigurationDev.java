package com.yimei.cron.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.annotation.PostConstruct;

/**
 * Created by hongpf on 16/5/17.
 */
@Configuration
@Profile("dev")
@AutoConfigureAfter(ThymeleafAutoConfiguration.class)
public class ThymeleafConfigurationDev {

    private final Logger log = LoggerFactory.getLogger(ThymeleafConfigurationDev.class);



    @Autowired
    SpringTemplateEngine engine ;

    @PostConstruct
    public void  setTemplateResolver(){
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix("./src/main/resources/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
//        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
    }
}
