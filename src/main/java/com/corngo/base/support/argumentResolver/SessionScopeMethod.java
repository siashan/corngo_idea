package com.corngo.base.support.argumentResolver;


import com.corngo.base.support.anno.SessionScope;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 获取session中的值  
 * @author zhangkeyuan
 *
 */
public class SessionScopeMethod implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 让方法和参数，两种target通过
		if (parameter.hasParameterAnnotation(SessionScope.class))
			return true;
		else if (parameter.getMethodAnnotation(SessionScope.class) != null)
			return true;
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		String annoVal = null;

		if (parameter.getParameterAnnotation(SessionScope.class) != null) {
			annoVal = parameter.getParameterAnnotation(SessionScope.class)
					.value();
		} else if (parameter.getMethodAnnotation(SessionScope.class) != null) {
			annoVal = parameter.getMethodAnnotation(SessionScope.class) != null ? StringUtils
					.defaultString(parameter.getMethodAnnotation(
							SessionScope.class).value()) : StringUtils.EMPTY;
		}

		if (webRequest.getAttribute(annoVal, RequestAttributes.SCOPE_SESSION) != null) {
			return webRequest.getAttribute(annoVal,
					RequestAttributes.SCOPE_SESSION);
		} else
			return null;
	}

}