package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;

public class JsonParseUtil {

	public static Map<String, Object> toMap(InputStream inputStream) {
//		서버에서 client의 요청을 받을 때 문자열을 받아 -> Json으로 변경 후 -> Map형태로 변경
		StringBuilder jsonData = new StringBuilder("");

//		InputStream으로 값을 읽어올 수 있다, BufferedReader는 부분부분 오는 값을 기다리다 한번에 가져온다
		BufferedReader bufferedReader = null;
		
		if(inputStream == null) {
			return null;
		}
		
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		while (true) {
			try {
				String data = bufferedReader.readLine();
				if(data == null) {
					break;
				}
				jsonData.append(data);
				
			} catch (IOException e) {
				return null;
			}
			
		}
		
		Gson gson = new Gson();
		
		return gson.fromJson(jsonData.toString(), Map.class); //requestBody.toString()을 Map형태(key-value)로 변경
		
	}
	
//	응답 시 Object -> Json
	public static String toJson(Object object) {
		Gson gson = new Gson();
		
		return gson.toJson(object);
	}
	
}
