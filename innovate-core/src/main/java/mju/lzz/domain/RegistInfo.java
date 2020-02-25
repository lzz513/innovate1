package mju.lzz.domain;

import lombok.Data;
import mju.lzz.beans.Regist;
import mju.lzz.beans.RegistItem;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-23 17:21
 **/
@Data
public class RegistInfo {
	Regist regist;
	List<RegistItem> registItemList;
}
