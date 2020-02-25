package mju.lzz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-17 15:03
 **/
public class DateUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static Date stringToDate(String s) throws ParseException {
		return sdf.parse(s);
	}

}
