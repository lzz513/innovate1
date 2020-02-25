package mju.lzz.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-23 17:12
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistItem {
	private Long id;
	private Long uid;
	private Long gid;
	private String username;
	private Integer isCaptain;
	private Integer isAgree;
	private LocalDateTime createrTime;
}
