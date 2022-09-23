package com.rainy.common.core.handle;

import com.rainy.common.core.annotation.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
@ConditionalOnProperty(name = {"custom-response.enabled"}, havingValue = "true", matchIfMissing = true)
public class CustomResponseInterceptor implements HandlerInterceptor {
    public static final String RESPONSE_RESULT = "RESPONSE_RESULT";
    public static final String REQUEST_ID = "request_Id";
    private static final String REQUEST_TIME = "REQUEST_TIME";

    public CustomResponseInterceptor() {
        log.info("custom-response.enabled use default values: true");
    }

    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        System.out.println("hello");
        if (handler instanceof HandlerMethod) {
            //handler转化为HandlerMethod
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取此次访问的method方法
            final Method method = handlerMethod.getMethod();
            //判断method是否被IdcResponse注解标注
            if (method.isAnnotationPresent(CustomResponse.class)) {
                //如果method被IdcResponse注解标记，取出放入HttpServletRequest中
                request.setAttribute(RESPONSE_RESULT, method.getAnnotation(CustomResponse.class));
                //记录时间戳
                request.setAttribute(REQUEST_TIME, System.currentTimeMillis());
            }
        }
        return true;
    }


    /**
     * postHandle方法在业务处理器处理请求执行完成后，生成视图之前执行
     * 最后一步，打印此次访问的信息
     */
    public void postHandle(@NotNull final HttpServletRequest request, @NotNull final HttpServletResponse response, @NotNull final Object handler, final ModelAndView modelAndView) {
    }


    /**
     * 在DispatcherServlet完全处理完请求后被调用，可用于数据返回处理
     * 最后一步，打印此次访问的信息
     */
    public void afterCompletion(@NotNull final HttpServletRequest request, @NotNull final HttpServletResponse response, @NotNull final Object handler, final Exception ex) {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(CustomResponse.class)) {
                final String requestID = request.getHeader(REQUEST_ID);
                final long requestTime = (long) request.getAttribute(REQUEST_TIME);
                final int statusCode = response.getStatus();
                if (statusCode == HttpStatus.BAD_REQUEST.value() || statusCode == HttpStatus.NOT_FOUND.value() || statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                    log.error("RequestID: {}, Method: {}, Response Time: {}ms, HttpStatus: {}", requestID, method.getName(), System.currentTimeMillis() - requestTime, statusCode, ex);
                } else {
                    log.info("RequestID: {}, Method: {}, Response Time: {}ms", requestID, method.getName(), System.currentTimeMillis() - requestTime);
                }
            }
        }
    }

}


