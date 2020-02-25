package mju.lzz.manager.impl;

import mju.lzz.beans.Game;
import mju.lzz.manager.GameManager;
import mju.lzz.mapper.GameDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-16 16:53
 **/
@Service
public class GameManagerImpl implements GameManager {

	@Autowired
	private GameDOMapper gameDOMapper;

	public int saveGame(Game game) {
		if (game == null) {
			return 0;
		}
		return gameDOMapper.saveGame(game);
	}

	public List<Game> queryAll() {
		return gameDOMapper.queryAll();
	}


	public List<Game> query(Game game) {
		return null;
	}

	@Override
	public Game queryById(long id) {
		try {
			return gameDOMapper.queryById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
