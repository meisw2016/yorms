package cn.com.yusys.file.util;


public class StringUtil {

	public static boolean isBlank(String input) {
		
		if (input == null) {
			return true;
		}
		else {
			if (input.equals("")) {
				return true;
			}
			else {
				return false;
			}
		}

	}
}
