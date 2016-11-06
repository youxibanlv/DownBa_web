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
	/**
	 * 把类似于 73773,1308,135,1720,2087 的id字符串截取为指定id个数的字符串，比如输入5个ID，其实只需要3个
	 * @param string 需要截取的字符串
	 * @param c 用于分割的字符
	 *  @param length 期待的id个数
	 * */
	public static String sbuByLength(String string,String c,int length){
		if (string == null || "".equals(string)) {
			return null;
		}
		String[]strArray= string.split(c);
		String str ="";
		if (strArray.length>length) {
			for(int i=0;i<length ;i++){
				str=str+strArray[i]+",";
			}
			return str.substring(0, str.length()-1);
		}
		return string;
	}
}
