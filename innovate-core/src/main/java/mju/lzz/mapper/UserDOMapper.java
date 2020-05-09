package mju.lzz.mapper;

import mju.lzz.beans.User;

import java.util.List;

public interface UserDOMapper {
	List<User> query(User queryDO);
	List<User> queryByName(String name);
	int saveUser(User user);

	int updateInfo(User path);
}
