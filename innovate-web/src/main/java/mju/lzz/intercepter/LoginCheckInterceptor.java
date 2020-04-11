package mju.lzz.intercepter;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.User;
import mju.lzz.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: innovate
 * @description: login check intercepter
 * @author: linzhizhu
 * @create: 2020-02-01 17:19
 **/
@Slf4j
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private HostHolder holder;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getSession().getAttribute("user") != null) {
			User user = (User) request.getSession().getAttribute("user");
			holder.set(user);
		}
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
		}
		return true;
	}

}
