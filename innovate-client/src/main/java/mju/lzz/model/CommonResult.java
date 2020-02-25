package mju.lzz.model;

import lombok.Data;
import mju.lzz.exception.InnovateCommonException;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-11 16:14
 **/
@Data
public class CommonResult<T> {

	private T data;
	private int errorCode;
	private boolean success;
	private String errorMessage;

	private CommonResult(T data) {
		this.data = data;
		this.success = true;
	}

	public CommonResult(T data, String errorMessage) {
		this.data = data;
		this.errorMessage = errorMessage;
	}

	private CommonResult(T data, int errorCode, String errorMessage) {
		this.data = data;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public CommonResult(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public static <T>CommonResult<T> successResult(T data) {
		return new CommonResult<T>(data);
	}

	public static <T>CommonResult<T> errorResult(String errorMessage, T data) {
		return new CommonResult<T>(data, errorMessage);
	}

	public static <T>CommonResult<T> errorResult(int code, String errorMessage, T data) {
		return new CommonResult<T>(data, code, errorMessage);
	}

	public static <T>CommonResult<T> errorResult(InnovateCommonException e) {
		return  new CommonResult<T>(e.getCode(), e.getMessage());
	}

}
