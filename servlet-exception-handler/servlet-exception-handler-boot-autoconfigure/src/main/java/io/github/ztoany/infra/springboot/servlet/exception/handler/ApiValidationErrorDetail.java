package io.github.ztoany.infra.springboot.servlet.exception.handler;

public class ApiValidationErrorDetail extends ApiErrorDetail {
    private String field;
    private String rejectedValue;
    private String message;

    public ApiValidationErrorDetail(String field, String rejectedValue, String message) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public String getMessage() {
        return message;
    }
}
