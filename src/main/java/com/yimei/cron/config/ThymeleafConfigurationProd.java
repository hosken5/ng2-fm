package com.yimei.cron.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.annotation.PostConstruct;

/**
 * Created by hongpf on 16/8/8.
 */
@Configuration
@Profile("prod")
@AutoConfigureAfter(ThymeleafAutoConfiguration.class)


public class ThymeleafConfigurationProd {

    @Autowired
    SpringTemplateEngine engine ;

    @PostConstruct
    public void  setTemplateResolver(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
        engine.setTemplateResolver(templateResolver);
        engine.addDialect(new Java8TimeDialect());
    }


}
