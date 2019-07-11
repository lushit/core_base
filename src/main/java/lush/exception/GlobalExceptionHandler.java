package lush.exception;

import lush.http.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BaseException.class)
	public Response handlerException(BaseException e) {
		Response  response = new Response();
		response.setCode(e.getCode());
		response.setData(e.getMessage());
		return response;
	}

}
