package mju.lzz.utils;

import mju.lzz.beans.User;
import org.springframework.stereotype.Component;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-19 13:32
 **/
@Component
public class HostHolder {

	private static ThreadLocal<User> users = new ThreadLocal<User>();

	public User get() {
		return users.get();
	}

	public void set(User user) {
		users.set(user);
	}

	public void clear() {
		users.remove();
	}

}
