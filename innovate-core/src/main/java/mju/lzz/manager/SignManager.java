package mju.lzz.manager;

import mju.lzz.beans.Sign;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-05-04 20:08
 **/
public interface SignManager {
	int saveSign(Sign sign);
	Sign query();
}
