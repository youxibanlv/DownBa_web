package com.main.utils;

public class NumberUtil {

	public static int parseToInt(String str) {
		int i = -1;
		try {
			i = Integer.parseInt(str);
		} catch (Exception e) {
			return i;
		}
		return i;
	}
}
