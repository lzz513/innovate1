package mju.lzz.mapper;

import mju.lzz.beans.Comment;

import java.util.List;

public interface CommentDOMapper {
	List<Comment> query(Comment comment);
	int saveComment(Comment comment);
}
