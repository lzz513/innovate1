package mju.lzz.manager.impl;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.Comment;
import mju.lzz.beans.CommentVO;
import mju.lzz.manager.CommentManager;
import mju.lzz.mapper.CommentDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-26 14:21
 **/
@Slf4j
@Service
public class CommentManagerImpl implements CommentManager {

	@Autowired
	private CommentDOMapper commentDOMapper;

	@Override
	public List<Comment> queryByGid(Long gid) {
		if (gid != null) {
			Comment comment = Comment.builder().gid(gid).build();
			try {
				return commentDOMapper.query(comment);
			} catch (Exception e) {
				log.info("{}", e);
			}
		}
		return null;
	}

	@Override
	public CommentVO saveComment(Comment comment) {
		if (comment != null) {
			int res = commentDOMapper.saveComment(comment);
			if (res != 0) {
				return CommentVO.builder().comment(comment).build();
			}
		}
		return null;
	}
}
