package mju.lzz.controller;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.Regist;
import mju.lzz.domain.RegistInfo;
import mju.lzz.exception.InnovateCommonException;
import mju.lzz.manager.RegistManager;
import mju.lzz.mapper.RegistItemDOMapper;
import mju.lzz.model.CommonResult;
import mju.lzz.response.RegistListResponse;
import mju.lzz.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-23 20:11
 **/
@Slf4j
@RestController
@RequestMapping("regist")
public class RegistController {

	@Autowired
	RegistManager registManager;

	@Autowired
	HostHolder holder;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public CommonResult<String> addRegist(Regist regist, @RequestParam("ids") List<Long> ids) {
		if (ids == null || ids.size() == 0 || regist == null) {
			return CommonResult.errorResult("add fail", "");
		}
		try {
			int res = registManager.saveRegist(regist, ids);
			if (res != 1) {
				return CommonResult.errorResult("add fail", "");
			}
		} catch (InnovateCommonException e) {
			return CommonResult.errorResult(e);
		} catch (Exception e) {
			log.info("e={}", e);
			return CommonResult.errorResult("add fail", "");
		}
		return CommonResult.successResult("");
	}



}
