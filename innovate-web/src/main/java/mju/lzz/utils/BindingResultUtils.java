package mju.lzz.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-26 14:33
 **/
public class BindingResultUtils {

	public static String getDefaultMessage(BindingResult bindingResult) {
		String errMessage = null;
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				System.out.println(error.getDefaultMessage());  //输出具体的错误信息
				errMessage = error.getDefaultMessage();
			}
		}
		return errMessage;
	}

}
