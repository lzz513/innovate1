package mju.lzz.manager;

import mju.lzz.beans.Regist;
import mju.lzz.domain.RegistInfo;

import java.util.List;

public interface RegistManager {
	int saveRegist(Regist regist, List<Long> ids);
	List<RegistInfo> queryByRid(Long rid);
	List<RegistInfo> queryByUid(Long uid);
}
