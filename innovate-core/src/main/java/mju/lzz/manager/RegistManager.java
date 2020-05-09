package mju.lzz.manager;

import mju.lzz.beans.Regist;
import mju.lzz.beans.User;
import mju.lzz.domain.RegistInfo;

import java.util.List;

public interface RegistManager {
	int saveRegist(Regist regist, List<Long> ids, User user);
	List<Regist> queryByGid(Long rid);
	List<Regist> queryByUid(Long uid);
}
