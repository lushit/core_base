package lush.controller;

import lush.http.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Controller 에서 공통으로 또는 지속적으로 사용되는 요소들을 관리
 */
@ControllerAdvice
public class BaseController {
	
	/**
	 * Get http headers.
	 *
	 * @return HttpHeaders
	 */
	public HttpHeaders getResponseHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		return responseHeaders;
	}
	
	/**
	 * Response Return
	 * Front에 넘겨줄 데이터가 없을 경우
	 * @return
	 */
	public ResponseEntity<Object> responseEntity() {
		// Data와 상태값 Return
		// 에러가 발생했을 경우에는 사전에 Exception이 발생했을 것
		return new ResponseEntity<>(new Response(), getResponseHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Response Return
	 * Front에 넘겨줄 데이터가 있을 경우
	 * @param returnData
	 * @return
	 */
	public ResponseEntity<Object> responseEntity(Object returnData) {
		//String typeName = returnData.getClass().getTypeName();
		
		// Return response 생성
		Response response = new Response();
		
		// Null이 아니면
		if(returnData != null) {
			// Return Data 셋팅
			response.setData(returnData);
		}
		
		// Data와 상태값 Return
		// 에러가 발생했을 경우에는 사전에 Exception이 발생했을 것
		return new ResponseEntity<>(response, getResponseHeaders(), HttpStatus.OK);
	}
	
}
