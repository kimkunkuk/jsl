package command_etc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.EtcDao;
import dto.EtcDto;

public class EtcList implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		EtcDao dao = new EtcDao();
		
		ArrayList<EtcDto> arr = dao.getEtcList();
		
		request.setAttribute("t_arr", arr);
	}

}
