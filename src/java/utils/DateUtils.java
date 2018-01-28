package java.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getTimeNow() {
		//规定时间显示格式
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
	   Date currentTime = new Date();
	   String dateString = formatter.format(currentTime);
	   return dateString;
	}
}
