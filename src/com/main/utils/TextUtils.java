package com.main.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

	/**
	 * 过滤html标签
	 * 
	 ***/
	public static String delHTMLTag(String htmlStr) {
		if (htmlStr == null || "".equals(htmlStr)) {
			return "";
		}
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	public static boolean isEmpty(String string) {
		if (null == string) {
			return true;
		} else if ("".equals(string)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 把类似于 73773,1308,135,1720,2087 的id字符串截取为指定id个数的字符串，比如输入5个ID，其实只需要3个
	 * 
	 * @param string
	 *            需要截取的字符串
	 * @param c
	 *            用于分割的字符
	 * @param length
	 *            期待的id个数
	 */
	public static String sbuByLength(String string, String c, int length) {
		if (string == null || "".equals(string)) {
			return null;
		}
		String[] strArray = string.split(c);
		String str = "";
		if (strArray.length > length) {
			for (int i = 0; i < length; i++) {
				str = str + strArray[i] + ",";
			}
			return str.substring(0, str.length() - 1);
		}
		return string;
	}
}
