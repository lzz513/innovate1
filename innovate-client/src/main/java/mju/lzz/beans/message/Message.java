package mju.lzz.beans.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-27 16:41
 **/
@Data
public class Message implements Comparable<Date> {
	private int type;
	private String content;
	private Date createTime;

	@Override
	public int compareTo(Date o) {
		return this.createTime.before(o) ? 1 : 0;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime(){
		return createTime;
	}

}
