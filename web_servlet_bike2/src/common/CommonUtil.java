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
	 
	 //페이징
	 public static String pageListPost(int current_page,int total_page, int pageNumber_count){
	 		int pagenumber;    //화면에 보여질 페이지 인덱스수
	 		int startpage;     //화면에 보여질 시작 페이지 번호
	 		int endpage;       //화면에 보여질 마지막 페이지 번호
	 		int curpage;       //이동하고자 하는 페이지 번호
	 		
	 		String strList=""; //리턴될 페이지 인덱스 리스트

	 		pagenumber = pageNumber_count;   //한 화면의 페이지 인덱스수
	 		
	 		//시작 페이지 번호 구하기
	 		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
	 		//마지막 페이지 번호 구하기
	 		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
	 		//총페이지수가 계산된 마지막 페이지 번호보다 작을 경우
	 		//총페이지수가 마지막 페이지 번호가 됨
	 		
	 		if(total_page <= endpage)  endpage = total_page;
	 					
	 		//첫번째 페이지 인덱스 화면이 아닌경우
	 		if(current_page > pagenumber){
	 			curpage = startpage -1;  //시작페이지 번호보다 1적은 페이지로 이동
	 			strList = strList +"<a href=javascript:goPage('"+curpage+"') ><i class='fa fa-angle-double-left'></i></a>";
	 		}
	 						
	 		//시작페이지 번호부터 마지막 페이지 번호까지 화면에 표시
	 		curpage = startpage;
	 		while(curpage <= endpage){
	 			if(curpage == current_page){
	 				strList = strList +"<a class='active'>"+current_page+"</a>";
	 			} else {
	 				strList = strList +"<a href=javascript:goPage('"+curpage+"')>"+curpage+"</a>";
	 			}
	 			curpage++;
	 		}
	 		//뒤에 페이지가 더 있는 경우
	 		if(total_page > endpage){
	 			curpage = endpage+1;
	 			strList = strList + "<a href=javascript:goPage('"+curpage+"') ><i class='fa fa-angle-double-right'></i></a>";
	 		}
	 		return strList;
	 	}
	 
		//공지사항 첨부 경로 
	 
	 /*
	  * 
	  *
	  * */
		public static String getFile_dir_notice() {
			String noticeDir = "C:/java/jsl/web_servlet_bike2/WebContent/attach/notice";
			return noticeDir;
		}
		
		public static String getFile_dir_news() {
			String newsDir = "C:/java/jsl/web_servlet_bike2/WebContent/attach/news"; 
			return newsDir;
		}
		public static String getFild_dir_product() {
			//String productDir = "C:/Users/admin/git/JSL/web_servlet_bike2/WebContent/attach/product";
			String productDir = "C:/track11_LJH/work_project/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/web_servlet_bike2/attach/product";
			
			//네비게이터 에 있는 폴더에는 안보임 경로 저기로 하면 사진 등록시 바로 목록에서 나올수있음 새로고침 필요없이
			return productDir;
		}
		public static String getFild_dir_freeboard() {
			//String freeboardDir = "C:/Users/admin/git/JSL/web_servlet_bike2/WebContent/attach/freeboard";
			String freeboardDir = "C:/track11_LJH/work_project/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/web_servlet_bike2/attach/freeboard";
			
			//네비게이터 에 있는 폴더에는 안보임 경로 저기로 하면 사진 등록시 바로 목록에서 나올수있음 새로고침 필요없이
			return freeboardDir;
		}
}
