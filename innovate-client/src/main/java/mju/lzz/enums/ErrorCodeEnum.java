package mju.lzz.enums;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-10 17:10
 **/
public enum ErrorCodeEnum {
	ACOUNT_IS_PRESENDCE(1, "用户名已经存在"),
	LOGIN_INFO_ERROR(2, "用户名或密码错误"),
	DATE_INFO_ERROR(3, "时间信息错误"),
	DATE_ORDER_ERROR(4, "报名时间已过且结束时间必须晚于开始"),
	INSERT_ERROR(5, "添加数据失败"),
	FILE_EMPTY(6, "上传文件为空"),
	ADD_FAIL(7, "添加失败！"),
	USERNAME_EXSTS(8, "用户名已被注册");


	private int errorCode;
	private String message;

	ErrorCodeEnum(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
