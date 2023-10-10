package io.github.ztoany.infra.springboot.servlet.exception.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.ztoany.infra.exception.ErrorMessage;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiError {
    private String code;
    private String message;
    private Date timestamp;
    private URI instance;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ApiErrorDetail> details;

    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = new Date();
    }

    public ApiError(String code, String message, URI instance) {
        this(code, message);
        this.instance = instance;
    }

    public ApiError(ErrorMessage message) {
        this(message.getCode(), message.getMessage(), null);
    }

    public ApiError(ErrorMessage message, URI instance) {
        this(message.getCode(), message.getMessage(), instance);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public URI getInstance() {
        return instance;
    }

    public List<ApiErrorDetail> getDetails() {
        return details;
    }

    public void addDetail(ApiErrorDetail detail) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(detail);
    }

    public static String buildCodeMessageStr(String code, String message) {
        return String.format("%s - %s", code, message);
    }

    @JsonIgnore
    public String getCodeMessage() {
        return buildCodeMessageStr(this.code, this.message);
    }
}
