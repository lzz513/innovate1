package mju.lzz.manager.impl;

import mju.lzz.beans.Regist;
import mju.lzz.beans.RegistItem;
import mju.lzz.beans.User;
import mju.lzz.domain.RegistInfo;
import mju.lzz.enums.ErrorCodeEnum;
import mju.lzz.exception.InnovateCommonException;
import mju.lzz.manager.RegistManager;
import mju.lzz.mapper.RegistDOMapper;
import mju.lzz.mapper.RegistItemDOMapper;
import mju.lzz.mapper.UserDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-23 17:24
 **/
@Service
public class RegistManagerImpl implements RegistManager {

	@Autowired
	private RegistDOMapper registDOMapper;

	@Autowired
	private RegistItemDOMapper registItemDOMapper;

	@Autowired
	private UserDOMapper userDOMapper;

	@Override
	public int saveRegist(Regist regist, List<Long> ids) {
		int sum = 0;
		if (!CollectionUtils.isEmpty(ids) && registDOMapper.saveRegist(regist) == 1) {
			for (int i = 0; i < ids.size(); i++) {
				Long id = ids.get(i);
				String username = verifyUser(id);
				if (StringUtils.isEmpty(username)) {
					return 0;
				}

				List<RegistItem> verifyList
						= registItemDOMapper.query(RegistItem.builder().uid(id).gid(regist.getId()).isAgree(1).build());
				if (!CollectionUtils.isEmpty(verifyList)) {
					return 0;
				}

				RegistItem item = RegistItem.builder()
						.uid(id)
						.gid(regist.getId())
						.isCaptain(i == 0 ? 1 : 0)
						.isAgree(i == 0 ? 1 : 0)
						.username(username)
						.build();
				sum += registItemDOMapper.saveItem(item);
			}
		} else {
			return 0;
		}
		return sum == ids.size() ? 1 : 0;
	}

	@Override
	public List<RegistInfo> queryByRid(Long gid) {
		List<RegistInfo> list = new ArrayList<RegistInfo>();
		List<Regist> regists = registDOMapper.query(Regist.builder().gid(gid).build());
		for (Regist regist:regists) {
			RegistInfo info = new RegistInfo();
			info.setRegist(regist);
			fillItems(info, regist.getId());
			list.add(info);
		}
		return list;
	}

	private void fillItems(RegistInfo info, Long id) {
		List<RegistItem> registItems = registItemDOMapper.queryByGid(id);
		info.setRegistItemList(registItems);
	}

	@Override
	public List<RegistInfo> queryByUid(Long uid) {
		return null;
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
