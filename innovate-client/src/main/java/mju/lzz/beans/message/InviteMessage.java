package mju.lzz.beans.message;

import lombok.Data;
import mju.lzz.beans.RegistItem;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-27 19:40
 **/
@Data
public class InviteMessage extends Message {
	RegistItem registItem;
}
