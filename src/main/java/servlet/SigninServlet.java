package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import security.Authentication;
import security.SecurityContextHolder;
import utils.JsonParseUtil;
import utils.ResponseUtil;

/**
 * 인증은 get메소드를 사용해야 하지만 보안상의 문제로 url에 정보가 표시되는 get을 쓰지 않고 post를 사용하여 body에 담아 보낸다(get의 예외)
 * 응답으로 토큰 발급 이후 발급된 토큰으로 인증
 */
@WebServlet("/auth/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> signinUser = JsonParseUtil.toMap(request.getInputStream());
		Map<String, String> responseData = new HashMap<>();
		
		for(User user: UserData.userList) {
			if(Objects.equals(user.getUsername(), signinUser.get("username")) && Objects.equals(user.getPassword(), signinUser.get("password"))) {
				//token 발급
				String token = UUID.randomUUID().toString();
				SecurityContextHolder.addAuth(new Authentication(user, token));
				responseData.put("token", token);
				break;
			}
		}

		ResponseUtil.response(response).of(200).body(JsonParseUtil.toJson(responseData));
	}

}
