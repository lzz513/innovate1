package mju.lzz.mapper;

import mju.lzz.beans.Regist;
import mju.lzz.beans.RegistItem;

import java.util.List;

public interface RegistItemDOMapper {
	List<RegistItem> query(RegistItem item);
	List<RegistItem> queryByGid(Long gid);
	List<RegistItem> queryByUid(Long uid);
	int saveItem(RegistItem items);
	int agree(Long riid);
	int not(Long riid);
	int notAgree(RegistItem registItem);
	int countByRid(RegistItem registItem);
}
