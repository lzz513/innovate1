package mju.lzz.controller;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.Sign;
import mju.lzz.manager.SignManager;
import mju.lzz.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-05-09 11:31
 **/

@Slf4j
@RestController
@RequestMapping("sign")
public class SignController {

	@Autowired
	private SignManager signManager;

	@PostMapping("addSign")
	public CommonResult<Boolean> addSign(String content) {
		if (StringUtils.isEmpty(content)) {
			return CommonResult.successResult(false);
		}
		Sign sign = new Sign();
		sign.setContent(content);
		sign.setCreateTime(new Date());
		System.out.println(sign);
		try {
			int res = signManager.saveSign(sign);
			System.out.println(res);
			if (res == 1) {
				return CommonResult.successResult(true);
			}
		} catch (Exception e) {
			log.info("e={}", e);
		}
		return CommonResult.successResult(false);
	}

}
