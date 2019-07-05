package lush.exception;

import lush.eunm.ExceptionType;
import org.springframework.stereotype.Component;

@Component
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final Integer code;
	
	private final String message;
	
	public BaseException(){
		this.code = 500;
		this.message = "fail";
	}
	
	public BaseException(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public BaseException setExceptionType(ExceptionType type) {
		BaseException baseException =  new BaseException(type.getCode(), type.getMessage());
		return baseException;
	}

	/**
	 * Get code
	 */
	public Integer getCode() {
		return code;
	}
	
}
