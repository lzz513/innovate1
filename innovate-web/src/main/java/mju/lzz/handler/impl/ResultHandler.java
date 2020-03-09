package mju.lzz.handler.impl;


import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.message.Message;
import mju.lzz.beans.message.ResultMessage;
import mju.lzz.enums.ErrorCodeEnum;
import mju.lzz.enums.MessageTypeEnum;
import mju.lzz.exception.InnovateCommonException;
import mju.lzz.handler.MessageHandler;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-03-02 21:52
 **/
@Slf4j
public class ResultHandler implements MessageHandler{

	private static String agreeTemplate = "%s已经同意加入%s";
	private static String notAgreeTemplate = "%s拒绝加入%s";

	@Override
	public void handler(Message message) {
		if (isHandler(message)) {
			ResultMessage resultMessage = (ResultMessage) message;
			String template = resultMessage.getIsAgree() ?
					agreeTemplate : notAgreeTemplate;
			String content = "";
			try {
				content = String.format(template, resultMessage.getWho(), resultMessage.getGameName());
			} catch (Exception e) {
				log.info("e={}", e);
				throw new InnovateCommonException(ErrorCodeEnum.TRANSFOR_ERR);
			}
			message.setContent(content);
		}
		return;
	}

	@Override
	public boolean isHandler(Message message) {
		return message.getType() == MessageTypeEnum.RESULT_MESSAGE.getId();
	}
}
