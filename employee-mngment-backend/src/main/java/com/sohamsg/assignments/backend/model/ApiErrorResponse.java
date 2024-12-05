package com.sohamsg.assignments.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorResponse {

	private String httpCode;
	private String applicationErrorCode;
	private String message;
	private String detailedMessage;
}
