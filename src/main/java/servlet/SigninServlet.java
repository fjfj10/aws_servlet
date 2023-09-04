package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import utils.JsonParseUtil;
import utils.ResponseUtil;

/**
 * 인증은 get메소드를 사용해야 하지만 보안상의 문제로 url에 정보가 표시되는 get을 쓰지 않고 post를 사용하여 body에 담아 보낸다(get의 예외)
 */
@WebServlet("/auth/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> signinUser = JsonParseUtil.toMap(request.getInputStream());
		Boolean responseData = false;
		
		for(User user: UserData.userList) {
			if(Objects.equals(user.getUsername(), signinUser.get("username")) && Objects.equals(user.getPassword(), signinUser.get("password"))) {
				responseData = true;
				break;
			}
		}
		
		ResponseUtil.response(response).of(200).body(responseData);
	}

}
