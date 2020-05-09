package mju.lzz.manager.impl;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.Game;
import mju.lzz.manager.GameManager;
import mju.lzz.mapper.GameDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-16 16:53
 **/
@Slf4j
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

	@Override
	public List<Game> queryOver() {
		try{
			return gameDOMapper.queryOver();
		} catch (Exception e) {
			log.info("e={}", e);
			return null;
		}
	}

	@Override
	public List<Game> queryWait() {
		try{
			return gameDOMapper.queryWait();
		} catch (Exception e) {
			log.info("e={}", e);
			return null;
		}
	}

	@Override
	public List<Game> queryNow() {
		try{
			return gameDOMapper.queryNow();
		} catch (Exception e) {
			log.info("e={}", e);
			return null;
		}
	}

	@Override
	public boolean updateGame(Game game) {
		Game daoGame = gameDOMapper.queryById(game.getId());
		if (daoGame != null) {
			Date now = new Date();
			if (!now.after(now)){
				return false;
			}
			if (gameDOMapper.updateGame(game) == 1) {
				return true;
			}
		}
		return false;
	}


}
