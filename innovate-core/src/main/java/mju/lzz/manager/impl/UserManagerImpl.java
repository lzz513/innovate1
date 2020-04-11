package mju.lzz.manager.impl;

import mju.lzz.beans.User;
import mju.lzz.enums.ErrorCodeEnum;
import mju.lzz.manager.UserManager;
import mju.lzz.mapper.UserDOMapper;
import mju.lzz.exception.InnovateCommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-04 14:14
 **/
@Service
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserDOMapper userMapper;

	public User login(User user) {
		if (user != null) {
			User daoUser = null;
			List<User> userList = userMapper.query(user);
			if (userList != null && userList.size() > 0) {
				daoUser = userList.get(0);
			}
			if (daoUser != null && !StringUtils.isEmpty(user.getPassword()) && daoUser.getPassword().equals(user.getPassword())) {
				return daoUser;
			}
		}
		throw new InnovateCommonException(ErrorCodeEnum.LOGIN_INFO_ERROR);
	}

	public boolean regist(User user) {
		if (user != null) {
			User query = User.builder().username(user.getUsername()).build();
			List<User> userList = userMapper.query(query);
			if (CollectionUtils.isEmpty(userList)) {
				userMapper.saveUser(user);
				return true;
			} else {
				throw new InnovateCommonException(ErrorCodeEnum.USERNAME_EXSTS);
			}
		}
		throw new InnovateCommonException(ErrorCodeEnum.LOGIN_INFO_ERROR);
	}

	public boolean updateInfo(User user) {
		return true;
	}

	@Override
	public List<User> query(User query) {
		return userMapper.query(query);
	}

	@Override
	public List<User> queryNameLike(String name) {
		String nameQuery = "%"+name+"%";
		List<User> users = userMapper.queryByName(nameQuery);
		return users;
	}


}
