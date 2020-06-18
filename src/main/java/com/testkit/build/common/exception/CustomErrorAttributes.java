package com.testkit.build.common.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> errorAttribute = super.getErrorAttributes(webRequest, includeStackTrace);

		errorAttribute.put("timestamp", LocalDateTime.now());
		Throwable error = getError(webRequest);
		if (error instanceof AbstractException) {
			AbstractException cause = (AbstractException) error;
			errorAttribute.put("code", cause.getErrorMessage().getCode());
			errorAttribute.put("message", cause.getErrorMessage().getMessage());
			errorAttribute.put("developerMessage", cause.getErrorMessage().getDeveloperMessage());

		}

		return errorAttribute;
	}
}
