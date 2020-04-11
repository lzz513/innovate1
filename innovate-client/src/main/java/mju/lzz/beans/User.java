package mju.lzz.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mju.lzz.enums.UserTypeEnum;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @program: innovate
 * @description: user
 * @author: linzhizhu
 * @create: 2020-01-31 19:11
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long id;
	@NotBlank(message = "用户名不能为null,长度必须大于0")
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
	@NotBlank(message = "确认密码不能为空")
	private String password1;
	private Integer status;
	@Email(message = "邮箱格式错误")
	@NotBlank(message = "邮箱不能为空")
	private String account;
	private Integer sid;
	private String headPath;
	@NotBlank(message = "城市不能为空")
	private String city;
	@NotBlank(message = "生日不能为空")
	private String birthday;
}

