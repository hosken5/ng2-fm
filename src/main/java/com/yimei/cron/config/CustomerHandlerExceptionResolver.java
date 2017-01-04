package com.yimei.cron.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yimei.cron.basic.common.Result;
import com.yimei.cron.basic.exception.BankException;
import com.yimei.cron.basic.exception.BusinessException;
import com.yimei.cron.basic.exception.NotFoundException;
import com.yimei.cron.basic.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


@Service
public class CustomerHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(CustomerHandlerExceptionResolver.class);

//    @Autowired
//    ExceptionReporter reporter;

    @Autowired
    private ObjectMapper om;

//    @Autowired
//    private Session session;

    public static final String ERROR_TEMPLATE_PREFIX = "/error";

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();

        if (ex instanceof NotFoundException
                || ex instanceof NoSuchRequestHandlingMethodException
                || ex instanceof NoHandlerFoundException
                || ex instanceof HttpRequestMethodNotSupportedException
                || ex instanceof org.springframework.web.multipart.MultipartException) {
            logger.warn("404", ex);
            modelAndView.setViewName(ERROR_TEMPLATE_PREFIX + "/404");
            response.setStatus(404);
            modelAndView.addObject("errorInfo", ex.getMessage());
        } else if (ex instanceof UnauthorizedException) {
            modelAndView.setViewName("/login");
            modelAndView.addObject("exception", ((UnauthorizedException) ex).getUrl());
            response.setStatus(401);
        } else if (ex instanceof BindException
                || ex instanceof TypeMismatchException
                || ex instanceof MissingServletRequestParameterException
                || ex instanceof MethodArgumentNotValidException) {
            logger.error("400",ex);
            response.setStatus(400);
            modelAndView.setViewName(ERROR_TEMPLATE_PREFIX+"/400");
        } else if (ex instanceof BusinessException) {
            logger.error("409", ex);
            response.setHeader("content-type", "application/json;charset=UTF-8");
            response.setStatus(409);
            try {
                om.writeValue(response.getOutputStream(), Result.error(ex.getMessage()).setErrorCode("409"));
            } catch (IOException e) {
                logger.error("error", e);
            }
        } else if (ex instanceof BankException) {
            logger.error("503", ex);
            response.setStatus(503);
//            handler500(request, ex);
        } else {
            logger.error("500", ex);
            modelAndView.setViewName(ERROR_TEMPLATE_PREFIX + "/500");
            response.setStatus(500);
            modelAndView.addObject("errorInfo", ex.getMessage());
        }
        return modelAndView;
    }

//    @Async
//    private void handler500(HttpServletRequest request, Exception ex) {
//        logger.warn("开始发送邮件");
//        try {
//            if ("application/json".equals(request.getContentType())) {
//                reporter.handle(ex, request.getRequestURL().toString(), om.writeValueAsString(extractPostRequestBody(request)), getHeadersInfo(request), session.getOrganization());
//            } else {
//                reporter.handle(ex, request.getRequestURL().toString(), om.writeValueAsString(request.getParameterMap()), getHeadersInfo(request), session.getOrganization());
//            }
//        } catch (Exception e) {
//            logger.warn("邮件发送失败", e);
//        }
//        logger.warn("邮件发送结束");
//    }


    /**
     * 判断是微信的请求、pc主站的请求
     *
     * @param url
     * @return true 微信请求、false 主站请求
     */

    private boolean isFromWxRequest(String url) {
        Pattern p = Pattern.compile("/m/{1}.*");
        return p.matcher(url).matches();
    }


    //获取header对象
    private String getHeadersInfo(HttpServletRequest request) throws JsonProcessingException {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        map.put("Request Method", request.getMethod());
        map.put("requestURL", request.getRequestURL().toString());
        return om.writeValueAsString(map);
    }


    //获取  application/json 数据
    static String extractPostRequestBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s.hasNext() ? s.next() : "";
        } else {
            return "{}";
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

}
