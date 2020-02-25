package mju.lzz.exception;

import lombok.Data;
import mju.lzz.enums.ErrorCodeEnum;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-10 17:08
 **/
@Data
public class InnovateCommonException extends RuntimeException {

	private int code;
	private String message;

	public InnovateCommonException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum.getMessage());
		this.code = errorCodeEnum.getErrorCode();
		this.message = errorCodeEnum.getMessage();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
