package mju.lzz.controller;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.Comment;
import mju.lzz.beans.CommentVO;
import mju.lzz.beans.User;
import mju.lzz.manager.CommentManager;
import mju.lzz.manager.UserManager;
import mju.lzz.model.CommonResult;
import mju.lzz.utils.BindingResultUtils;
import mju.lzz.utils.HostHolder;
import mju.lzz.utils.Tire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-26 14:14
 **/
@Slf4j
@RestController
@RequestMapping("comment")
public class CommentController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private CommentManager commentManager;

	@Autowired
	private HostHolder holder;

	@Autowired
	Tire commentFilter;

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public CommonResult<CommentVO> save(@Validated Comment comment, BindingResult bindingResult) {
		String errorMessage = BindingResultUtils.getDefaultMessage(bindingResult);
		if (errorMessage == null) {
			comment.setCreateTime(new Date());
			comment.setToId(20L);
			comment.setUid(holder.get().getId());
			comment.setUsername(holder.get().getUsername());
			comment.setToId(0L);
			comment.setContent(commentFilter.replace(comment.getContent()));
			CommentVO vo = commentManager.saveComment(comment);
			if (vo != null) {
				vo.setHeadPath(holder.get().getHeadPath());
				return CommonResult.successResult(vo);
			}
		} else {
			return CommonResult.errorResult(errorMessage, null);
		}
		return CommonResult.errorResult("add fail", null);
	}

	@RequestMapping(value = "queryByGid", method = RequestMethod.GET)
	public CommonResult<List<CommentVO>> queryByGid(Long gid) {
		if (gid != null) {
			List<Comment> comments = commentManager.queryByGid(gid);
			List<CommentVO> vos = new ArrayList<>();
			if (!comments.isEmpty()) {
				for (Comment comment : comments) {
					User user = userManager.query(User.builder().id(comment.getUid()).build()).get(0);
					CommentVO vo = CommentVO.builder().headPath(user.getHeadPath()).comment(comment).build();
					vos.add(vo);
				}
			}
			return CommonResult.successResult(vos);
		}
		return CommonResult.errorResult("查询id不存在", null);
	}

}
