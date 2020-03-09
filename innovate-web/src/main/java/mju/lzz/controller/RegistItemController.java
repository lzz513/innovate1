package mju.lzz.controller;

import mju.lzz.beans.RegistItem;
import mju.lzz.beans.RegistItemVO;
import mju.lzz.manager.RegistItemManager;
import mju.lzz.model.CommonResult;
import mju.lzz.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-28 19:12
 **/
@RestController
@RequestMapping("registItem")
public class RegistItemController {

	@Autowired
	private RegistItemManager registItemManager;

	@Autowired
	private HostHolder holder;

	@RequestMapping(value = "agree", method = RequestMethod.POST)
	public CommonResult<Boolean> agree(Long riid, Long gid, Long rid) {
		long uid = holder.get().getId();
		int res = registItemManager.agree(riid, uid, gid, rid);
		if (res == 1) {
			return CommonResult.successResult(true);
		} else {
			return CommonResult.errorResult("", false);
		}
	}

	@RequestMapping(value = "not", method = RequestMethod.POST)
	public CommonResult<Boolean> notAgree(Long riid, Long gid, Long rid) {
		long myId = holder.get().getId();
		int res = registItemManager.notAgree(riid, rid);
		if (res != 1) {
			return CommonResult.errorResult("not agree other", false);
		}
		return CommonResult.successResult(true);
	}

	@RequestMapping(value = "findByRid", method = RequestMethod.GET)
	public CommonResult<List<RegistItemVO>> findByRid(Long rid) {
		List<RegistItemVO> registItemVOS = registItemManager.queryVOByRid(rid);
		if (registItemVOS == null) {
			return CommonResult.errorResult("查询失败", null);
		}
		return CommonResult.successResult(registItemVOS);
	}
}
