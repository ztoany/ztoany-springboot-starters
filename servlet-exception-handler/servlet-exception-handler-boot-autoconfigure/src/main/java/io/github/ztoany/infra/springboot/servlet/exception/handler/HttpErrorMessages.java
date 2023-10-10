package io.github.ztoany.infra.springboot.servlet.exception.handler;

import io.github.ztoany.infra.exception.ErrorMessage;

/**
 *
 * E1100001 - E1100999 is reserved for http error
 *
 */
public enum HttpErrorMessages implements ErrorMessage {

    METHOD_NOT_SUPPORTED("E1100001", "Http method is not supported"),
    MEDIA_TYPE_NOT_SUPPORTED("E1100002", "Http media type is not supported"),
    MEDIA_TYPE_NOT_ACCEPTABLE("E1100003", "Http media type is not acceptable"),
    MISSING_PATH_VARIABLE("E1100004", "Http path variable was missing"),
    MISSING_REQUEST_PARAM("E1100005", "Http request parameter was missing"),
    MISSING_REQUEST_PART("E1100006", "Http request part was missing"),
    REQUEST_BINDING_ISSUE("E1100007", "Http request had binding issue"),
    ARGUMENT_NOT_VALID("E1100008", "Http argument was not valid"),
    NO_HANDLER_FOUND("E1100009", "No handler is found"),

    ERROR_RESPONSE("E1100010", "Error response"),

    ASYNC_REQUEST_TIMEOUT("E1100011", "Async request was timeout"),

    RESOURCE_NOT_FOUND("E1100012", "Resource can not be found"),

    HTTP_MESSAGE_NOT_READABLE("E1100013", "Http message is not readable"),

    HTTP_MESSAGE_NOT_WRITABLE("E1100014", "Failed to write request"),

    CONVERSATION_NOT_SUPPORTED("E1100015", "Conversion is not supported"),

    TYPE_MISMATCH("E1100016", "Type mismatch"),
    ;

    private String code;
    private String message;

    HttpErrorMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
