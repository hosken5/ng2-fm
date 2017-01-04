package com.yimei.cron.web.support;


import com.yimei.cron.basic.exception.UnauthorizedException;
import com.yimei.cron.service.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
public class ACLInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    protected Session session;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!session.isLogined()){
            throw new UnauthorizedException() ;
        }
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod method = (HandlerMethod) handler;
//
//            if (method.getMethodAnnotation(LoginRequired.class) != null || method.getBeanType().getDeclaredAnnotation(LoginRequired.class) != null) {
//                if (!session.isLogined()) {
//                    String url = request.getRequestURI();
//                    if (request.getQueryString() != null)
//                        url = url + "?" + request.getQueryString();
//                    throw new UnauthorizedException(url);
//                }
//            }
//        }
        return super.preHandle(request, response, handler);
    }
}
