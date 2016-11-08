package com.main.utils;

public class NumberUtil {

	public static Integer parseToInt(String str) {
		Integer i = null;
		if (str != null && !"".equals(str)) {
			try {
				i = Integer.parseInt(str);
			} catch (Exception e) {
				return i;
			}
		}
		return i;

	}
}
