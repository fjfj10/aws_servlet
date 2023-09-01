package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
//	private int statusCode;
//	private String body;
	
//	호출될때 ResponseUtilBilder 객체 생성
	public static ResponseUtilBilder response(HttpServletResponse response) {
		return new ResponseUtilBilder(response);
	}
	
	public static class ResponseUtilBilder {
		private static HttpServletResponse response;
		
		public ResponseUtilBilder(HttpServletResponse response) {
			this.response = response;
		}
		
//		statusCode 지정 가능
		public ResponseUtilBilder of(int statusCode) {
			response.setStatus(statusCode);
			return this;
		}
		
//		전송하고자 하는 데이터 작성
		public void body(Object body) throws IOException {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(body);
		}
	}
}
