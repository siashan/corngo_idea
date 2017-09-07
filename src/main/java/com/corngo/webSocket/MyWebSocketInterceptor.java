package com.corngo.webSocket;

import com.corngo.admin.model.SysUser;
import com.corngo.base.support.PUBConstants;
import com.corngo.base.support.utils.Shiro;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 此类用来获取登录用户信息并交由websocket管理
 * @author ts
 *
 */
public class MyWebSocketInterceptor  implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		//将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息	
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest  = (ServletServerHttpRequest) request;
			HttpServletRequest httpRequest = servletRequest.getServletRequest();
			SysUser user1 = (SysUser) httpRequest.getSession().getAttribute("user");
			SysUser user = (SysUser) Shiro.getSessionAttribute("user");
			//Constants.CURRENT_WEBSOCKET_USER也是常量，用来存储WebsocketSession的key值
			attributes.put(PUBConstants.CURRENT_WEBSOCKET_USER,user1.getId());
		}
		return true;
	}
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {		
	}
}