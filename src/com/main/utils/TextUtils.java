package com.main.utils;

public class TextUtils {

	public static boolean isEmpty(String string){
		if (null == string) {
			return false;
		}else if ("".equals(string)) {
			return false;
		}else {
			return true;
		}
	}
}
