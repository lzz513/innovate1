package mju.lzz.mapper;

import mju.lzz.beans.RegistItem;

import java.util.List;

public interface RegistItemDOMapper {
	List<RegistItem> query(RegistItem item);
	List<RegistItem> queryByGid(Long gid);
	int saveItem(RegistItem items);
}
