package mju.lzz.beans;

import lombok.Data;

import java.util.Date;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-05-04 20:04
 **/
@Data
public class Sign {
	Long id;
	Date createTime;
	String content;
}
