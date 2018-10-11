package com.injoin.ijboss;

import com.alibaba.fastjson.JSON;
import in.togetu.tscommon.exception.BizException;
import in.togetu.tscommon.exception.CommonApiResultCode;
import in.togetu.tscommon.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Autowired
    private MessageSource messageSource;

    private ConcurrentHashMap<String, Locale> localeMap = new ConcurrentHashMap<>();

    private static final String SPLIT = "|";

    private static final String APP_EXCEPTION = "TSUSER_GLOBAL_EXCEPTION";

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Exception> jsonHandler(HttpServletRequest request, Exception ex) {
        if (CollectionUtils.isEmpty(localeMap)) {
            Locale[] localeList = NumberFormat.getAvailableLocales();
            for (Locale locale1 : localeList) {
                localeMap.put(locale1.toString(), locale1);
            }
        }
        LOGGER.info("request lang is {}", request.getParameter("lang"));
        String lang = Optional.ofNullable(request.getParameter("lang")).orElse("en_US");
        Locale locale = localeMap.getOrDefault(lang, LocaleContextHolder.getLocale());
        LOGGER.info("locale is {}, default is {}", locale, LocaleContextHolder.getLocale());
        Result<Exception> fail = new Result<>();
        if (ex instanceof BizException) {
            BizException bizExcept = (BizException) ex;
            LOGGER.warn("biz exception happened, code:{}, msg:{}",
                    bizExcept.getResultCode().getCode(), bizExcept.getResultCode().getMessage());
            fail.setResultCode(bizExcept.getResultCode().getCode());
            fail.setResultMessage(messageSource.getMessage(fail.getResultCode(), null, locale));
        } else {
            fail.setResultCode(CommonApiResultCode.SYSTEM_ERROR.getCode());
            fail.setResultMessage(messageSource.getMessage(fail.getResultCode(), null, locale));
            StackTraceElement[] stackTrace = ex.getStackTrace();
            List<StackTraceElement> stlist = new ArrayList<>();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("togetu") && element.getLineNumber() > 0) {
                    stlist.add(element);
                }
            }
            LOGGER.error(getErrorMsg(request, ex, JSON.toJSONString(stlist)), ex);
        }
        LOGGER.error("error",ex);
        return fail;
    }

    private String getErrorMsg(HttpServletRequest request, Exception e, String stack) {
        StringBuilder builder = new StringBuilder(SPLIT);
        builder.append(APP_EXCEPTION)
                .append(SPLIT).append("ip:").append(getIP(request))
                .append(SPLIT).append("url:").append(request.getRequestURL())
                .append(SPLIT).append("request:").append(JSON.toJSONString(request.getParameterMap()))
                .append(SPLIT).append("stack:").append(stack)
                .append(SPLIT).append("errMsg:").append(e.getMessage());
        return builder.toString();
    }


    private String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}
