package mju.lzz.controller;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.message.InviteMessage;
import mju.lzz.beans.message.Message;
import mju.lzz.beans.RegistItem;
import mju.lzz.enums.MessageTypeEnum;
import mju.lzz.handler.MessageHandler;
import mju.lzz.manager.RegistItemManager;
import mju.lzz.model.CommonResult;
import mju.lzz.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-25 12:07
 **/
@Slf4j
@RestController
@RequestMapping("message")
public class MessageController{

	@Autowired
	private HostHolder holder;

	@Autowired
	private RegistItemManager registItemManager;

	@Autowired
	private List<MessageHandler> messageHandlerList;

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public CommonResult<List<Message>> findByUid() {
		Long uid = holder.get().getId();
		List<RegistItem> registItems = registItemManager.queryByUid(uid);
		List<Message> messageList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(registItems)) {
			try {
				for (RegistItem registItem : registItems) {
					InviteMessage inviteMessage = new InviteMessage();
					inviteMessage.setType(MessageTypeEnum.INVITE_MESSAGE.getId());
					inviteMessage.setRegistItem(registItem);
					fillMessageContent(inviteMessage);
					messageList.add(inviteMessage);
				}
			} catch (Exception e) {
				log.info("e={}", e);
				return CommonResult.errorResult("", null);
			}
		}
		return CommonResult.successResult(messageList);
	}

	private void fillMessageContent(InviteMessage inviteMessage) {
		messageHandlerList.stream().forEach(messageHandler -> {
			if (messageHandler.isHandler(inviteMessage)) {
				messageHandler.handler(inviteMessage);
				return;
			}
		});
	}


}
