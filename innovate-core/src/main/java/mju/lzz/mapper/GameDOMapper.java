package mju.lzz.mapper;

import mju.lzz.beans.Game;

import java.util.Date;
import java.util.List;

public interface GameDOMapper {
	int saveGame(Game game);
	List<Game> queryAll();
	List<Game> query(Game game);
	Game queryById(Long id);
	int updateGame(Game game);

	List<Game> queryOver();
	List<Game> queryWait();
	List<Game> queryNow();
}
