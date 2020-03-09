package mju.lzz.manager;

import mju.lzz.beans.Comment;
import mju.lzz.beans.CommentVO;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-26 14:19
 **/
public interface CommentManager {
	List<Comment> queryByGid(Long gid);
	CommentVO saveComment(Comment comment);
}
