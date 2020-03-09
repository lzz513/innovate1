package mju.lzz.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-23 13:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Regist {
	private Long id;
	private Long uid;
	private String name;
	private String captain;
	private Long gid;
	private String doc;
	private String gameName;
	private Integer status;
}
