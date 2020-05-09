package mju.lzz.response;

import lombok.Data;
import mju.lzz.beans.Game;
import mju.lzz.beans.Sign;
import mju.lzz.beans.User;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-21 14:23
 **/
@Data
public class GameListResponse {

	List<Game> gameList;
	User user;
	String signContent;
}
