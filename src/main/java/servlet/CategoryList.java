package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;


@WebServlet("/category")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private String[] categoryArray = {
			"한식",
			"체험관광",
			"카페",
			"자연명소",
			"양식",
			"문화예술"
	};
	
	
	private class Feed {
		private String feedName;
		private String categoryName;
	
		public Feed(String feedName, String categoryName) {
			this.feedName = feedName;
			this.categoryName = categoryName;
		}
		
		public String getCategoryName() {
			return categoryName;
		}
		
		public String getFeedInfo() {
			return "feedName: " + feedName + ", categoryName: " + categoryName + "\n";
		}
	}
	
	private Feed[] feedArray = {
		 new Feed("1번피드", "한식"),
		 new Feed("2번피드", "한식"),
		 new Feed("3번피드", "한식"),
		 new Feed("4번피드", "체험관광"),
		 new Feed("5번피드", "체험관광"),
		 new Feed("6번피드", "카페"),
		 new Feed("7번피드", "자연명소"),
		 new Feed("8번피드", "카페"),
		 new Feed("9번피드", "자연명소"),
		 new Feed("10번피드", "양식"),
		 new Feed("11번피드", "문화예술"),
		 new Feed("12번피드", "문화예술"),
		 new Feed("13번피드", "카페"),
		 
	};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		System.out.println(request.getMethod());
		String categoryName = request.getParameter("categoryName");
		System.out.println(categoryName);
		
		if(!checkCategory(categoryName)) {
//			한글은 UTF-8로 안잡아주면 깨짐
			response.setCharacterEncoding("UTF-8");    
			response.setStatus(400);
			response.getWriter().println("해당 카테고리는 존재하지 않는 카테고리입니다.");
			return;
		}
		
//		AtomicReference<String> responseData = new AtomicReference<String>("");
		
//		findFeedCategoryName(categoryName).forEach(feed -> {
//			responseData.set(responseData.get() + feed.getFeedInfo());
//		});
		
		Gson gson = new Gson();
		
		response.setCharacterEncoding("UTF-8");
//		response.getWriter().println(responseData.getPlain());
//		"application/json" = http프로토콜에서 정해둔 것 외워 = 객체로 변환됨 => 프론트앤드 작업이 쉬워짐
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(findFeedCategoryName(categoryName)).toString());
//		getWriter의 printWriter = PrintStream 응답시 사용 
		
	}
	
	private boolean checkCategory(String categoryName) {
		for(int i = 0; i < categoryArray.length; i++) {
			if(categoryArray[i].equals(categoryName)) {
				return true;
			}
		}
		return false;
	}
	
	private List<Feed> findFeedCategoryName(String categoryName) {
		List<Feed> feeds = new ArrayList<>();
		
		for(int i = 0; i < categoryArray.length; i++) {
			if(feedArray[i].getCategoryName().equals(categoryName)) {
				feeds.add(feedArray[i]);
			}
		}
		
		return feeds;
	}

}
