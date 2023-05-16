package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	// 현재 날짜 시분초
	 public static String getTodayTime(){
	       Date time = new Date();
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       String today = format.format(time);
	       return today;
	    }
}
