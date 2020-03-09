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
 * @create: 2020-02-26 21:21
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
	private String headPath;
	private Comment comment;
}
