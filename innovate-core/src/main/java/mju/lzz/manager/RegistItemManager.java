package mju.lzz.manager;

import mju.lzz.beans.RegistItem;
import mju.lzz.beans.RegistItemVO;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-27 16:50
 **/
public interface RegistItemManager {
	List<RegistItemVO> queryVOByRid(Long rid);
	List<RegistItem> queryByUid(Long uid);
	List<RegistItem> queryByGid(Long gid);
	int agree(Long riid, Long uid, Long gid, Long rid);
	int notAgree(Long riid, Long rid);
}
