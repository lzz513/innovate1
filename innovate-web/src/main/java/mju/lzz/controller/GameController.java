package mju.lzz.controller;

import lombok.extern.slf4j.Slf4j;
import mju.lzz.beans.Game;
import mju.lzz.beans.User;
import mju.lzz.enums.ErrorCodeEnum;
import mju.lzz.exception.InnovateCommonException;
import mju.lzz.manager.GameManager;
import mju.lzz.model.CommonResult;
import mju.lzz.response.GameListResponse;
import mju.lzz.response.GameResponse;
import mju.lzz.utils.DateUtils;
import mju.lzz.utils.FileUtils;
import mju.lzz.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-10 15:11
 **/
@Slf4j
@RestController
@RequestMapping("game")
public class GameController {

	@Autowired
	GameManager gameManager;

	@Autowired
	HostHolder holder;

	@RequestMapping(value = "addGame", method = RequestMethod.POST)
	public CommonResult<String> addGame(String name, String startTime, String endTime
			, String description, @RequestParam("file") MultipartFile file) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = DateUtils.stringToDate(startTime);
			endDate = DateUtils.stringToDate(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String path = FileUtils.saveFile(file);
		if (StringUtils.isEmpty(path)) {
			return CommonResult.errorResult("add fail", "");
		}
		Game game = Game.builder()
				.name(name)
				.startTime(startDate)
				.endTime(endDate)
				.description(description)
				.createrTime(new Date())
				.photo(path)
				.creatorId(1)
				.build();
		try {
			if (gameManager.saveGame(game) == 1) {
				return CommonResult.successResult("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CommonResult.errorResult("add fail", "");
	}

	@RequestMapping(value = "gameList", method = RequestMethod.GET)
	public CommonResult<GameListResponse>  gameList() {
		GameListResponse response = new GameListResponse();
		response.setUser(holder.get());
		response.setGameList(gameManager.queryAll());
		return CommonResult.successResult(response);
	}

	@RequestMapping(value = "queryGame", method = RequestMethod.GET)
	public CommonResult<GameResponse> game(long gid) {
		Game game = Game.builder()
				.id(gid)
				.build();
		Game daoGame = gameManager.queryById(gid);
		if (daoGame == null) {
			return CommonResult.errorResult("该活动不存在", null);
		}
		GameResponse response = new GameResponse();
		response.setGame(daoGame);
		response.setUser(holder.get());
		return CommonResult.successResult(response);
	}

}
