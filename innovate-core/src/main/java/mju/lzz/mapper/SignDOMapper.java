package mju.lzz.mapper;

import mju.lzz.beans.Sign;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-05-04 20:05
 **/
public interface SignDOMapper {
	int saveSign(Sign sign);
	Sign query();
}
