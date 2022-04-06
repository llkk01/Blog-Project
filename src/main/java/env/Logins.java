package env;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//로그인 확인 관리
public class Logins extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			                 HttpServletResponse response, 
			                 Object handler)throws Exception {
		//요청이  컨트롤러 닿기전,로그인 여부를 확인
		HttpSession session = request.getSession(false);
		boolean loginIn = false;
		
		//로그인되어있을 경우
		if(session!=null&& session.getAttribute("id")!=null) {
			loginIn = true;
		}
		//로그인이 안되어 있었다면
		if(loginIn!=true) {
			//리다이렉트>로그인
			response.sendRedirect("/login");
			
			return false;
		}
		//true 종료면  요청이  컨트롤러에 도달한다.
		return true;
	}
	
}
