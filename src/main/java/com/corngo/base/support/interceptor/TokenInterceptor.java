package com.corngo.base.support.interceptor;


import com.corngo.base.support.PUBConstants;
import com.corngo.base.support.anno.Token;
import com.corngo.base.support.enums.TokenAction;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 防表单重复提交拦截器
 * Created by kfc on 2016/7/25.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token token = method.getAnnotation(Token.class);
            if (token != null) {
                TokenAction value = token.value();
                if (value.equals(TokenAction.GET)) {
                    String tokenVal = RandomStringUtils.randomAlphabetic(24);
                    request.setAttribute(PUBConstants.TOKEN, tokenVal);
                    request.getSession().setAttribute(PUBConstants.TOKEN, tokenVal);
                    return true;
                } else if (value.equals(TokenAction.VALID.toString())) {
                    if (check(request)) {
                        // 重复提交
                        log.info("token is null or repeat submit URI[{}]", request.getRequestURI());
                        return false;
                    }
                    // token校验通过后，清楚token
                    request.getSession().removeAttribute(PUBConstants.TOKEN);
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 检查是否重复提交
     *
     * @param request
     */
    private boolean check(HttpServletRequest request) {
        Object token = request.getSession().getAttribute(PUBConstants.TOKEN);
        if (null == token) {
            return true;
        }
        String clientToken = request.getParameter(PUBConstants.TOKEN);
        if (null == clientToken) {
            return true;
        }
        if (!clientToken.equals(token.toString())) {
            return true;
        }
        return false;
    }
}
