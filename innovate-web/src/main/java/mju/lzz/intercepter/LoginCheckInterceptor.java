package mju.lzz.intercepter;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.annoation.LoginFilter;
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

	public static final int NOT_LOGIN = 0;
	public static final int NEED_LOGIN = 1;
	public static final int ADMIN_LOGIN = 2;

	@Autowired
	private HostHolder holder;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = null;
		if (request.getSession().getAttribute("user") != null) {
			user =  (User) request.getSession().getAttribute("user");
			holder.set(user);
		}
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			LoginFilter loginFilter = hm.getMethodAnnotation(LoginFilter.class);
			if (loginFilter == null && user == null) {
				response.sendRedirect("/pages/login.html");
				return false;
			}
		}
		return true;
	}

}
