package mju.lzz.response;

import lombok.Data;
import mju.lzz.beans.Game;
import mju.lzz.beans.User;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-22 20:21
 **/
@Data
public class GameResponse {
	private Game game;
	private User user;
}
