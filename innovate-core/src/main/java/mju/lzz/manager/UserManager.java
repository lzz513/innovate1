package mju.lzz.manager;

import mju.lzz.beans.User;

import java.util.List;

/**
 * @program: innovate
 * @description: user service
 * @author: linzhizhu
 * @create: 2020-01-31 16:35
 **/

public interface UserManager {
	User login(User user);
	boolean regist(User user);
	boolean updateInfo(User user);
	List<User> query(User query);
	List<User> queryNameLike(String name);
}