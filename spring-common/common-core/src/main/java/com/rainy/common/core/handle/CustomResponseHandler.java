package com.rainy.common.core.handle;


import com.rainy.common.core.annotation.CustomResponse;
import com.rainy.common.core.entity.CustomResult;
import com.rainy.common.core.entity.Results;
import com.rainy.common.core.util.HttpServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
@ConditionalOnBean({CustomResponseInterceptor.class})
public class CustomResponseHandler implements ResponseBodyAdvice<Object> {

    public static final String RESPONSE_RESULT = "RESPONSE_RESULT";
    public static final String REQUEST_ID = "request_Id";

    /**
     * 第二步判断HttpServletRequest中是否有注解对象
     */
    @Override
    public boolean supports(@NotNull MethodParameter methodParameter, @NotNull Class<? extends HttpMessageConverter<?>> aClass) {
        //生成HttpServletRequest
        HttpServletRequest hsr = HttpServletUtil.getRequest();
        //取出HttpServletRequest中的注解
        Object obj = hsr.getAttribute(RESPONSE_RESULT);
        //转换成IdcResponse注解
        final CustomResponse responseResultAnn = (CustomResponse) obj;
        //不等于空返回true
        return responseResultAnn != null;
    }

    /**
     * 在数据返回之前更改格式
     */
    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter methodParameter, @NotNull MediaType mediaType, @NotNull Class<? extends HttpMessageConverter<?>> aClass, @NotNull ServerHttpRequest serverHttpRequest, @NotNull ServerHttpResponse serverHttpResponse) {
        //获取HttpServletRequest对象
        final HttpServletRequest httpRequest = HttpServletUtil.getRequest();
        //取出IdcResponse注解类
        final CustomResponse idcResponse = (CustomResponse) httpRequest.getAttribute(RESPONSE_RESULT);
        //取出IdcResponse的valus，Results或者其子类
        final Class<? extends Results> resultClazz = idcResponse.values();
        Object objBuffer = null;
        try {
            //ServerHttpResponse赋值，在ServerHttpRequest中取
            HttpServletUtil.getResponse().setHeader(REQUEST_ID, httpRequest.getHeader(REQUEST_ID));
            //resultClazz实例化
            final Results result = (Results) resultClazz.newInstance();
            //给Results赋值，body为查询到的数据
            result.setCode(Integer.toString(HttpStatus.OK.value()));
            result.setMsg(HttpStatus.OK.getReasonPhrase());
            result.setData(body);
            if (body instanceof String || aClass.isAssignableFrom(StringHttpMessageConverter.class)) {
                objBuffer = result.toJson();
            } else if (body instanceof Results) {
                objBuffer = body;
            } else {
                objBuffer = result;
            }
        } catch (InstantiationException | IllegalAccessException ex2) {
            final ReflectiveOperationException e = null;
            objBuffer = new CustomResult(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
            log.error("BeforeBodyWrite append erro!", (Throwable) e);
        }
        return objBuffer;
    }
}

