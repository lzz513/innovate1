package mju.lzz.controller;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.annoation.LoginFilter;
import mju.lzz.beans.User;
import mju.lzz.exception.InnovateCommonException;
import mju.lzz.manager.UserManager;
import mju.lzz.model.CommonResult;
import mju.lzz.request.UserDataRequest;
import mju.lzz.utils.FileUtils;
import mju.lzz.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: innovate
 * @description: user
 * @author: linzhizhu
 * @create: 2020-02-01 21:39
 **/
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private HostHolder holder;

	@LoginFilter(LoginFilter.NOT_LOGIN)
	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public CommonResult<String> regist(@Validated @RequestBody User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errMessage = null;
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());  //输出具体的错误信息
				errMessage = error.getDefaultMessage();
			}
			return CommonResult.errorResult(errMessage, errMessage);
		}
		try {
			if (user.getPassword().equals(user.getPassword1()) ) {
				userManager.regist(user);
			} else {
				return CommonResult.errorResult("信息错误，注册失败", "");
			}
		} catch (InnovateCommonException e) {
			return CommonResult.errorResult(e);
		} catch (Exception e) {
			log.info("e={}", e);
			return CommonResult.errorResult("信息错误，注册失败", "");
		}
		return CommonResult.successResult("");
	}

	@ResponseBody
	@LoginFilter(LoginFilter.NOT_LOGIN)
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public CommonResult<User> login(@RequestBody Map<String, Object> requestMap, HttpServletRequest request) {
		User daoUser = null;
		try {
			User user = User.builder()
					.username(requestMap.get("username").toString())
					.password(requestMap.get("password").toString())
					.build();
			daoUser = userManager.login(user);
			request.getSession().setAttribute("user", daoUser);
		} catch (InnovateCommonException e) {
			return CommonResult.errorResult(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CommonResult.successResult(daoUser);
	}

	@RequestMapping(value = "findByName", method = RequestMethod.GET)
	public CommonResult<List<User>> findByName(String username) {
		List<User> userList = null;
		try {
			userList = userManager.queryNameLike(username);
		} catch (Exception e) {
			log.info("e={}", e);
			return CommonResult.errorResult("查找异常", null);
		}
		return CommonResult.successResult(userList);
	}

	@RequestMapping(value = "findById", method = RequestMethod.GET)
	public CommonResult<User> findById(Long id) {
		User user = null;
		try {
			if (id == null) {
				id = holder.get().getId();
			}
			User query = User.builder().id(id).build();
			List<User> query1 = userManager.query(query);
			if (query1 != null) {
				user = query1.get(0);
			}
		} catch (Exception e) {
			log.info("e={}", e);
			return CommonResult.errorResult("查找异常", null);
		}
		return CommonResult.successResult(user);
	}

	@RequestMapping(value = "getMyself", method = RequestMethod.GET)
	public CommonResult<User> getMyself() {
		User user = holder.get();
		return CommonResult.successResult(user);
	}

	@PostMapping("updateHeadPictrue")
	public CommonResult<Boolean> updateHeadPictrue(@RequestParam("file") MultipartFile file) {
		String path = FileUtils.saveFile(file);
		if (path != null) {
			User user = holder.get();
			User updateUser = User.builder().id(user.getId()).headPath(path).build();
			try {
				int res = userManager.updateInfo(updateUser);
				if (res == 1) {
					user.setHeadPath(path);
					return CommonResult.successResult(true);
				}
			} catch (Exception e) {
				log.info("e={}", e);
			}
		}
		return CommonResult.errorResult(0, "fail", false);
	}

	@LoginFilter(LoginFilter.NOT_LOGIN)
	@PostMapping("adminLogin")
	public CommonResult<Boolean> adminLogin(@RequestBody Map<String, Object> map,HttpServletRequest request){
		String username = map.get("username").toString();
		String password = map.get("password").toString();
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
			try {
				User admin = userManager.query(User.builder().username("admin").build()).get(0);
				if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
					request.getSession().setAttribute("user", admin);
					return CommonResult.successResult(true);
				}
			} catch (Exception e) {
				log.info("e={}", e);
			}
		}
		return CommonResult.successResult(false);
	}

}
