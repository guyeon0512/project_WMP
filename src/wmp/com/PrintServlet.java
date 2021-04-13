package wmp.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Servlet implementation class PrintServlet
 * URL을 입력하여 전달 받은 HTML 문자열을 몫과 나머지로 출력
 * @author  : park gu yeon (pgy6664@gmail.com)
 */
@WebServlet("/PrintServlet")
public class PrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String url ="";
		String type ="";
		String unitStr ="";
		int unit = 0;
		url = request.getParameter("url") == null ? "" : (String)request.getParameter("url");
		type = request.getParameter("type") == null ? "" :(String)request.getParameter("type");
		unitStr = request.getParameter("unit") == null ? "" :(String)request.getParameter("unit");	
		if(!"".equals(unitStr)){
			unit = Integer.parseInt(unitStr);
		}
		try{
	        URL tempUrl  = new URL(url);
	        HttpURLConnection connection = (HttpURLConnection)tempUrl.openConnection();
	        connection.setRequestMethod("GET");
	        connection.connect();
	         
	        if(200 == connection.getResponseCode()) { //연결가능한 URL 유효성 검증
	        	Document doc = Jsoup.connect(url).get();
	    		String content = "";
	    		if (type.equals("0")) {
	    			//content = doc.text();//html 요소 제외
	    			content = doc.html();
	    			content = content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");                        
	    		}else {
	    			content = doc.html();
	    		}
	    		content.trim();
	    		String result = "";
	    		result = selectEngNum(content);
	    		
	    		int resultLen = result.length();
	    		int quotient = 0;
	    		int remainder = 0;
	    		if(resultLen > 0 && unit > 0) {
	    			quotient = resultLen / unit;
	    			remainder = resultLen % unit;
	    		}
	    		request.setAttribute("type", type);
	    		request.setAttribute("content", content);
	    		request.setAttribute("result", result);
	    		request.setAttribute("quotient", quotient);		
	    		request.setAttribute("remainder", remainder);
	    		
	    		ServletContext context = getServletContext();
	    		RequestDispatcher dispatcher = context.getRequestDispatcher("/print.jsp");
	    		dispatcher.forward(request, response);
	        }
	    }catch(IOException e){
	    	e.printStackTrace();    	 
	    	PrintWriter out = response.getWriter();    	 
	    	out.println("<script>alert('유효하지 않은 url입니다.');");
	    	out.println("history.back();</script>");
	    	out.flush();
	    }
	}
	/*영어, 숫자 추출*/
	private String selectEngNum(String content) {
		String eng = content.replaceAll("[^a-zA-Z]", "");
		String num = content.replaceAll("[^0-9]", "");
		
		PriorityQueue<String> NumQueue = new PriorityQueue<>();
		for (char c : num.toCharArray()) {
			NumQueue.add(Character.toString(c));
		}
		ArrayList<String> engList = new ArrayList<>();		
		for (char c : eng.toCharArray()) {
			engList.add(Character.toString(c));
		}
		engList.sort(Comp);
		
		return mixString(NumQueue,engList);
		
	}
	/*영어,숫자 순서로 교차 출력*/
	private String mixString(PriorityQueue<String> numQueue, ArrayList<String> engList) {
		StringBuffer br = new StringBuffer();
		int index = 0;
		while(!numQueue.isEmpty() || (!engList.isEmpty() && index < engList.size())) {
			if(!engList.isEmpty()) {
				br.append(engList.get(index));index++;
			}
			if(!numQueue.isEmpty()) {
				br.append(numQueue.poll());
			}
		}
		return br.toString();
	}
	/*영어 AaBbCc...YyZz로 정렬하기 위한 비교*/
	static Comparator<String> Comp = new Comparator<String>() {
		@Override
		public int compare(String s1, String s2) {
			if (!s1.equals(s2)) {
				if (s1.toUpperCase().equals(s2.toUpperCase())) { //a,A인경우
	                if (s1.equals(s1.toLowerCase()) && s2.equals(s2.toUpperCase())) {
	                    return 1;
	                } else {
	                    return -1;
	                }
	            } else {
	            	return s1.toUpperCase().compareTo(s2.toUpperCase());
	            }
			}
			return 0;
		}
	};
}
