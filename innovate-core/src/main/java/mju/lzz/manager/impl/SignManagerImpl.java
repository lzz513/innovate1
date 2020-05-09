package mju.lzz.manager.impl;

import mju.lzz.beans.Sign;
import mju.lzz.manager.SignManager;
import mju.lzz.mapper.SignDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-05-04 20:09
 **/
@Service
public class SignManagerImpl implements SignManager {

	@Autowired
	SignDOMapper signDOMapper;

	@Override
	public int saveSign(Sign sign) {
		return signDOMapper.saveSign(sign);
	}

	@Override
	public Sign query() {
		return signDOMapper.query();
	}
}
