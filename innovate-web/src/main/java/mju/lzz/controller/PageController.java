package mju.lzz.controller;

import mju.lzz.annoation.LoginFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-04-21 22:46
 **/
@Controller
public class PageController {


	@GetMapping("/index")
	public void index(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/pages/index.html").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
