package lush.enm;

public enum ExceptionType {

	BAD_REQUEST(400, "잘못된 인자값입니다."),
		
	NOT_FOUND_DATA(404, "데이터가 없습니다."),
		
	NOT_FOUND_FILE(404, "파일이 없습니다."),
	
	NOT_FOUND_SESSION(404, "세션정보가 없습니다."),

	DUPLICATED_DATA(412, "중복된 데이터입니다."),
	
	INVALID_ID_VALUE(412, "ID는 0보다 커야합니다."),
	
	LOCKED(423, "요청한 리소는 잠겨있는 상태입니다."),
	
	FILE_TYPE_EXCEPTION(415, "유효하지 않은 파일형식 입니다."),
	
	FILE_UPLOAD_FAILED_EXCEPTION(502, "파일 업로드에 실패하였습니다.");
	
	
	private final Integer code;
		
	private final String message;
		
	public Integer getCode() {
		return code;
	}
		
	public String getMessage() {
		return message;
	}
		
	ExceptionType(Integer code, String message){
		this.code = code;
		this.message = message;
	}
		
}
