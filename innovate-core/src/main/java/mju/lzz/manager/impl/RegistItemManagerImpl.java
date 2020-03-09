package mju.lzz.manager.impl;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.Regist;
import mju.lzz.beans.RegistItem;
import mju.lzz.beans.RegistItemVO;
import mju.lzz.beans.User;
import mju.lzz.enums.AgreeTypeEnum;
import mju.lzz.enums.ErrorCodeEnum;
import mju.lzz.exception.InnovateCommonException;
import mju.lzz.manager.RegistItemManager;
import mju.lzz.mapper.RegistDOMapper;
import mju.lzz.mapper.RegistItemDOMapper;
import mju.lzz.mapper.UserDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-27 16:50
 **/
@Service
@Slf4j
public class RegistItemManagerImpl implements RegistItemManager {

	@Autowired
	private RegistDOMapper registDOMapper;

	@Autowired
	private RegistItemDOMapper registItemDOMapper;

	@Autowired
	private UserDOMapper userDOMapper;

	@Override
	public List<RegistItemVO> queryVOByRid(Long rid) {
		List<RegistItemVO> registItemVOS = new ArrayList<>();
		List<RegistItem> registItems = registItemDOMapper.query(RegistItem.builder().rid(rid).build());
		for (RegistItem item:registItems) {
			RegistItemVO vo = new RegistItemVO();
			vo.setStatus(AgreeTypeEnum.getDescById(item.getIsAgree()));
			User user = null;
			try {
				user = userDOMapper.query(User.builder().id(item.getUid()).build()).get(0);
			} catch (Exception e) {
				log.info("e={}", e);
				return null;
			}
			vo.setName(user.getUsername());
			vo.setEmail(user.getAccount());
			registItemVOS.add(vo);
		}
		return registItemVOS;
	}

	@Override
	public List<RegistItem> queryByUid(Long uid) {
		try {
			return registItemDOMapper.query(RegistItem.builder().uid(uid).build());
		}
		catch (Exception e) {
			log.info("e = {}", e);
			return  null;
		}
	}

	@Override
	public List<RegistItem> queryByGid(Long gid) {
		try {
			return registItemDOMapper.query(RegistItem.builder().gid(gid).build());
		}
		catch (Exception e) {
			log.info("e = {}", e);
			return  null;
		}
	}

	@Override
	@Transactional
	public int agree(Long riid, Long uid, Long gid, Long rid) {
		synchronized (RegistItemManagerImpl.class) {
			if (registItemDOMapper.agree(riid) != 1) {
				return 0;
			} else {
				registItemDOMapper.notAgree(RegistItem.builder().uid(uid).gid(gid).build());
				int cnt = registItemDOMapper.countByRid(RegistItem.builder().rid(rid).build());
				int acAnt = registItemDOMapper.countByRid(RegistItem.builder().rid(rid).isAgree(AgreeTypeEnum.AGREE.getId()).build());
				if (cnt == acAnt) {
					int res = registDOMapper.updateStatus(Regist.builder().id(rid).status(AgreeTypeEnum.AGREE.getId()).build());
				}
			}
		}
		return 1;
	}

	@Override
	@Transactional
	public int notAgree(Long riid, Long rid) {
		try {
			int res1 = registItemDOMapper.not(riid);
			int res2 = registDOMapper.updateStatus(Regist.builder().id(rid).status(AgreeTypeEnum.NOT_AGREE.getId()).build());
			if (res1 == res2 && res1 == 1) {
				return 1;
			} else {
				throw new InnovateCommonException(ErrorCodeEnum.INSERT_ERROR);
			}
		}
		catch (Exception e) {
			log.info("e={}", e);
		}
		return 0;
	}
}
