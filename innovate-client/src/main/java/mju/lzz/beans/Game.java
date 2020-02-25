package mju.lzz.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-10 15:12
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
	private Long id;
	private String name;
	private Date startTime;
	private Date endTime;
	private Integer creatorId;
	private String description;
	private Date createrTime;
	private String photo;

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreaterTime() {
		return createrTime;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getStartTime() {
		return startTime;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getEndTime() {
		return endTime;
	}
}
