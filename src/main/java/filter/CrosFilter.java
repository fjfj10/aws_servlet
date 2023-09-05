package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;


@WebFilter("*")
public class CrosFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//톰캣에서 ServletResponse로 업캐스팅된 httpServletResponse를 다시 다운캐스팅 해서 사용(ServletResponse은 setHeader가 없음)
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		// Origin: 요청을 보낸쪽 ("Access-Control-Allow-Origin", "허용할 사이트");
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
		// 허용할 메소드 종류
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
		// cross-origin에서 이 Header를 유지할 기간(초단위)
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		
		chain.doFilter(request, response);     //servlet 호출 dofilter이전: 전처리, 후: 후처리(기준이 된다)
	}

	

}
