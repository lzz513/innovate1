package mju.lzz.response;

import lombok.Data;
import mju.lzz.beans.Regist;
import mju.lzz.beans.User;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-25 11:51
 **/
@Data
public class RegistListResponse {
	private User user;
	private List<Regist> registList;
}
