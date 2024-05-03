package in.ashokit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandller {

	@ExceptionHandler(value = AppException.class)
	public ResponseEntity<ExceptionInfo> handleAppException(AppException ex) {

		String exmsg = ex.getMessage();

		ExceptionInfo info = new ExceptionInfo();
		info.setExCode("EX0000784");
		info.setExMsg(exmsg);

		return new ResponseEntity<>(info, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
