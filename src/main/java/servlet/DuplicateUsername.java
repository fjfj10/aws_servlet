package servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import utils.ResponseUtil;

@WebServlet("/auth/signup/duplicate/username")
public class DuplicateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String username = request.getParameter("username");
		Boolean responseData = false;
		
		for(User user: UserData.userList) {
			if(Objects.equals(user.getUsername(), username)) {
				// 요청에 문제가 없기 때문에 400 X, ResponseUtil.response(response).of(200).body(false);코드와 겹치기 때문에 responseData를 만들어 false/true 결정 -> return대신 break를 걸어준다
				// ResponseUtil.response(response).of(200).body(true);
				responseData = true;
				break;
			}
		}
		
//		for(int i = 0; i < usernames.length; i++) {
//			// 문자열 비교할때 Objects.equals 사용 => username.eqals를 사용하면 null체크를 해줘야함 
//			// Objects.equals를 사용하면 Objects는 null일수없고 username에 바로 참조를 하지 않기 때문에 nullpointE를 피할 수 있다
//			if(Objects.equals(usernames[i], username)) {
//				// true = 중복이 됐다 
//				ResponseUtil.response(response).of(400).body(true);
//				return;
//			}
//		}
		
		ResponseUtil.response(response).of(200).body(responseData);
	}

}
