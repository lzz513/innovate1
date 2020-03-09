package mju.lzz.handler.impl;


import mju.lzz.beans.message.InviteMessage;
import mju.lzz.beans.message.Message;
import mju.lzz.enums.MessageTypeEnum;
import mju.lzz.handler.MessageHandler;
import org.springframework.stereotype.Component;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-27 19:44
 **/
@Component
public class InviteMessageHandler implements MessageHandler {

	private static final String template = "%s邀请您参加%s";

	@Override
	public void handler(Message message) {
		InviteMessage inviteMessage = (InviteMessage)message;
		String content = String.format(template, inviteMessage.getRegistItem().getFromName(),
				inviteMessage.getRegistItem().getGameName());
		message.setContent(content);
		message.setCreateTime(inviteMessage.getRegistItem().getCreateTime());
	}

	@Override
	public boolean isHandler(Message message) {
		return message.getType() == MessageTypeEnum.INVITE_MESSAGE.getId();
	}
}
