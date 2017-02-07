package com.yimei.cron.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yimei.cron.config.jackson.Java8TimeModule;
import com.yimei.cron.web.support.ACLInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.context.embedded.ErrorPage;
//import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.validation.Validator;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by hongpf on 16/5/19.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ObjectMapper objectMapper ;

    @Autowired
    protected ACLInterceptor aclInterceptor;

    @Autowired
    private CustomerHandlerExceptionResolver customerHandlerExceptionResolver;
//
//    @Autowired
//    private CurrentUserMethodArgumentHandler currentUserMethodArgumentHandler;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:./frontend/dist/");
//        registry.addResourceHandler("/bower_components/**").addResourceLocations("file:./static/bower_components/");
//        registry.addResourceHandler("/styles/**").addResourceLocations("file:./static/.tmp/styles/");
//        registry.addResourceHandler("/images/**").addResourceLocations("file:./static/app/images/");
//        registry.addResourceHandler("/scripts/**").addResourceLocations("file:./static/app/scripts/");
//        registry.addResourceHandler("/zrjtFile/**").addResourceLocations("file:../zrjtFile/");
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(aclInterceptor).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/");
//        registry.addInterceptor(verifyAuthentiyHandler).addPathPatterns("/**").excludePathPatterns("/login");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(aclInterceptor).addPathPatterns("/**");//logininrequired
//    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(customerHandlerExceptionResolver);
    }

//
//    @Overrides
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(currentUserMethodArgumentHandler);
//    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
                container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/401"));
                container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
            }
        };
    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000");
//    }


    //JSR-303
    @Bean(name = "validator")
    public Validator createBeanValidator() {
        return new LocalValidatorFactoryBean();
    }


    @PostConstruct
    private void jacksonConfig() {
        objectMapper.registerModule(new Java8TimeModule());
    }
}
