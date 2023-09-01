package servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ResponseUtil;

@WebServlet("/auth/signup/duplicate/username")
public class DuplicateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] usernames = { "aaa", "bbb", "ccc" };
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Origin: 요청을 보낸쪽 ("Access-Control-Allow-Origin", "허용할 사이트");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
		// 허용할 메소드 종류
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
		// cross-origin에서 이 Header를 유지할 기간(초단위)
		response.setHeader("Access-Control-Max-Age", "3600");
		
		
		String username = request.getParameter("username");
		
		for(int i = 0; i < usernames.length; i++) {
			// 문자열 비교할때 Objects.equals 사용 => username.eqals를 사용하면 null체크를 해줘야함 
			// Objects.equals를 사용하면 Objects는 null일수없고 username에 바로 참조를 하지 않기 때문에 nullpointE를 피할 수 있다
			if(Objects.equals(usernames[i], username)) {
				// true = 중복이 됐다 
				ResponseUtil.response(response).of(400).body(true);
				return;
			}
		}
		
		ResponseUtil.response(response).of(200).body(false);
	}

}
