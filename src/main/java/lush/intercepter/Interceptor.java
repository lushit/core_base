package lush.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lush.enm.ExceptionType;
import lush.exception.BaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Interceptor extends HandlerInterceptorAdapter {

	protected Log log = LogFactory.getLog(Interceptor.class);

	/**
	 * 컨트롤러 실행 직전에 수행
	 *  true값 반환시 계속 진행, false값 반환시 인터셉터, 컨트롤러 실행중지.
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(
                HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		log.debug("===================       START       ===================");
		
		//세션 체크한다.
		HttpSession session = request.getSession();
		log.debug(" Request URI \t:  " + request.getRequestURI());
		log.debug(" Request session \t:  " + session.getAttribute("M_SESSION"));
		// 로그인 / 아웃 관련
		// 세션체크
		if (session.getAttribute("M_SESSION") == null) {
			log.debug("  Request Session Not Found   ");
			// 세션이 없습니다.
			throw new BaseException().setExceptionType(ExceptionType.NOT_FOUND_SESSION);
		}
		
		return true;
	}

	/**
	 * 컨트롤러의 메서드의 처리가 끝나 return 후 화면 실행 전 호출
	 *  -그 전 interceptor 수행 또는 컨트롤러 수행중에 예외 발생시 수행 안함
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(
                HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.debug("===================	END	===================\n");
	}

	/**
	 * 컨트롤러가 수행되고 화면처리 후 호출
	 * - 뷰를 통해서 클라이언트에게 응답을 전송한 뒤 수행
	 * - 예외와 상관없이 무조건 수행
	 * @param request
	 * @param response
	 * @param object
	 * @param arg3
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(
                HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {

	}

}
