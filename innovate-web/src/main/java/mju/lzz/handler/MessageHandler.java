package mju.lzz.handler;

import mju.lzz.beans.message.Message;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-27 19:41
 **/
public interface MessageHandler {
	void handler(Message message);
	boolean isHandler(Message message);
}
