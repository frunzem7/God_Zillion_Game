package student.examples.ggengine.domain.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleValidationException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();

		String errorParams = buildErrorParams(result);

		return errorParams;
	}

	private String buildErrorParams(BindingResult result) {
		List<FieldError> fieldErrors = result.getFieldErrors();
		Map<String, String> errMap = new HashMap();

		for (FieldError error : fieldErrors) {

			if (error.getField().equalsIgnoreCase("username")) {
				errMap.put("username", error.getDefaultMessage());
			}

			if (error.getField().equalsIgnoreCase("email")) {
				errMap.put("email", error.getDefaultMessage());
			}

			if (error.getField().equalsIgnoreCase("password")) {
				errMap.put("password", error.getDefaultMessage());
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String jacksonData = "";
		try {
			jacksonData = objectMapper.writeValueAsString(errMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jacksonData;
	}
}
