package mju.lzz.controller;

import mju.lzz.model.CommonResult;
import mju.lzz.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-25 12:07
 **/
@RestController
@RequestMapping("message")
public class MessageController {

	@Autowired
	HostHolder holder;

	public CommonResult<String> findByUid() {
		return null;
	}

}
