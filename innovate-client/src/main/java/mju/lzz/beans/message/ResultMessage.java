package mju.lzz.beans.message;

import lombok.Data;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-03-02 21:51
 **/
@Data
public class ResultMessage extends Message{
	private String who;
	private String gameName;
	private Boolean isAgree;
}
