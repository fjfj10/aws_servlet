package security;

import java.util.List;
import java.util.Objects;

import entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SecurityContextHolder {

	private static List<Authentication> authentications;
	
	public static void addAuth(Authentication authentication) {
		authentications.add(authentication);
	}
	
	public static Boolean isAuthenticated(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				return true;
			}
		}
		return false;
	}
	
	public static void removeAuth(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				authentications.remove(authentication);
				break;
			}
		}
	}
	
	
}
