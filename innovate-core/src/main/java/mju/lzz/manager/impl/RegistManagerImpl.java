package mju.lzz.manager.impl;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.Regist;
import mju.lzz.beans.RegistItem;
import mju.lzz.beans.User;
import mju.lzz.domain.RegistInfo;
import mju.lzz.enums.AgreeTypeEnum;
import mju.lzz.enums.ErrorCodeEnum;
import mju.lzz.enums.IsCaptianEnum;
import mju.lzz.exception.InnovateCommonException;
import mju.lzz.manager.RegistManager;
import mju.lzz.mapper.RegistDOMapper;
import mju.lzz.mapper.RegistItemDOMapper;
import mju.lzz.mapper.UserDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-23 17:24
 **/
@Service
@Slf4j
public class RegistManagerImpl implements RegistManager {

	@Autowired
	private RegistDOMapper registDOMapper;

	@Autowired
	private RegistItemDOMapper registItemDOMapper;

	@Autowired
	private UserDOMapper userDOMapper;

	@Transactional
	@Override
	public int saveRegist(Regist regist, List<Long> ids, User user) {
		int sum = 0;
		if (!CollectionUtils.isEmpty(ids) && registDOMapper.saveRegist(regist) == 1) {
			List<RegistItem> query = registItemDOMapper.query(RegistItem.builder().gid(regist.getGid()).uid(ids.get(0)).build());
			if (!verifyCaptian(query)) {
				throw new InnovateCommonException(ErrorCodeEnum.ALREADLY_REGIST);
			}
			for (int i = 0; i < ids.size(); i++) {
				Long id = ids.get(i);
				String username = verifyUser(id);
				if (StringUtils.isEmpty(username)) {
					return 0;
				}

				List<RegistItem> verifyList
						= registItemDOMapper.query(RegistItem.builder().uid(id).gid(regist.getId()).isAgree(1).build());
				if (!CollectionUtils.isEmpty(verifyList)) {
					throw new InnovateCommonException(ErrorCodeEnum.INSERT_ERROR);
				}

				RegistItem item = RegistItem.builder()
						.uid(id)
						.gid(regist.getGid())
						.isCaptain(i == 0 ? 1 : 0)
						.isAgree(i == 0 ? 1 : 0)
						.username(username)
						.createTime(new Date())
						.fromId(user.getId())
						.fromName(user.getUsername())
						.gameName(regist.getGameName())
						.rid(regist.getId())
						.build();
				sum += registItemDOMapper.saveItem(item);
			}
		} else {
			throw new InnovateCommonException(ErrorCodeEnum.INSERT_ERROR);
		}
		return sum == ids.size() ? 1 : 0;
	}

	private boolean verifyCaptian(List<RegistItem> query) {
		for (RegistItem item:query) {
			if (item.getIsCaptain() == IsCaptianEnum.CAPTIAN.getId()
			|| item.getIsAgree() == AgreeTypeEnum.AGREE.getId()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Regist> queryByGid(Long gid) {
		List<RegistInfo> list = new ArrayList<RegistInfo>();
		List<Regist> regists = registDOMapper.query(Regist.builder().gid(gid).build());
		return regists;
	}


	private void fillItems(RegistInfo info, Long id) {
		List<RegistItem> registItems = registItemDOMapper.queryByGid(id);
		info.setRegistItemList(registItems);
	}

	@Override
	public List<Regist> queryByUid(Long uid) {
		try {
			List<Regist> query = registDOMapper.query(Regist.builder().uid(uid).build());
			return query;
		} catch (Exception e) {
			log.info("e={}", e);
			return null;
		}
	}

	public String verifyUser(Long id) {
		List<User> userList = userDOMapper.query(User.builder().id(id).build());
		if (userList != null && userList.size() == 1) {
			return userList.get(0).getUsername();
		} else {
			return null;
		}
	}

}
