package com.chenxing.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 字符串转util Date
	 * 
	 * @param _in
	 *            字符串
	 * @param _format
	 *            想要转换成的样式
	 */
	public static Date str2Date(String _in, String _format) throws ParseException {
		/* yyyy-MM-dd格式一定要与stringDate的格式一致 */
		SimpleDateFormat sdf = new SimpleDateFormat(_format);
		return sdf.parse(_in);
	}

	/**
	 * util Date 转 字符串转
	 * 
	 */
	public static String date2Str(Date _date, String _format) {
		SimpleDateFormat sdf = new SimpleDateFormat(_format);
		return sdf.format(_date);
	}
}
