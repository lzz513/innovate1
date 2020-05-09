package mju.lzz.manager;

import mju.lzz.beans.Game;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-16 16:51
 **/
public interface GameManager {
	int saveGame(Game game);
	List<Game> queryAll();
	List<Game> query(Game game);
	Game queryById(long id);
	List<Game> queryOver();
	List<Game> queryWait();
	List<Game> queryNow();
	boolean updateGame(Game game);
//	List<Game> queryNameLike(String name);
}
