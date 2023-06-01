package common;

import javax.servlet.http.HttpServletRequest;

public class commonToday implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		String todayTime = CommonUtil.getTodayTime();
		
		request.setAttribute("t_todayTime", todayTime);
	}

}
