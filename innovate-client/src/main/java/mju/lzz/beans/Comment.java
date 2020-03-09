package mju.lzz.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-26 14:12
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private Long id;
	private Long uid;
	private Long gid;
	private Long toId;
	@NotBlank(message = "回复内容不能为空")
	private String content;
	private String username;
	private Date createTime;
	@JsonFormat(pattern="yyyy-MM-dd hh:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
}
