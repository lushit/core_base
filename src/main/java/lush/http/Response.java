package lush.http;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 추후 Base로 이동 
 * @author jelly
 *
 */
@Component
public class Response {

	/**
	 * Response code.
	 * use HttpStatus value
	 */
	private Integer code;

	/**
	 * Response message.
	 */
	private String message;

	/**
	 * Response data.
	 */
	private Object data;

	public Response() {
		this.code = HttpStatus.OK.value();
		this.message = "success";
		this.data = "";
	}
	/**
	 * Constructor.
	 *
	 * @param code
	 * @param message
	 */
	public Response( Integer code, String message) {
		this.code = code;
		this.message = message;
		this.data = "";
	}

	/**
	 * Constructor.
	 *
	 * @param code
	 * @param message
	 * @param data
	 */
	public Response(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * Get code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Set code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Get message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Get Object Data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Set Objcet Data
	 */
	public void setData(Object data) {
		this.data = data;
	}
}