package com.st.manager.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	/**
	 * 返回值 yyyyMMddHHmmssSSS
	 * @return
	 */
	public static String currentDateToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	
	public static void main(String[] args) {
		/**
		String str = "hellowo.rld";
		System.out.println(str.indexOf("."));
		
		System.out.println(str.substring(str.indexOf(".")));
		*/
		
		Date d = new Date();
		System.out.println(d.getTime());
		
	}
}
