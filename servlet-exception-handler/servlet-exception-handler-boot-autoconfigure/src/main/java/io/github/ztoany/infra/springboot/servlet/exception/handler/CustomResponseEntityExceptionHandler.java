package io.github.ztoany.infra.springboot.servlet.exception.handler;

import io.github.ztoany.infra.exception.BusinessException;
import io.github.ztoany.infra.exception.SystemErrorMessages;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger SLF4J_LOGGER = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.METHOD_NOT_SUPPORTED, ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.MEDIA_TYPE_NOT_SUPPORTED, ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.MEDIA_TYPE_NOT_ACCEPTABLE, ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.MISSING_PATH_VARIABLE, ex, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.MISSING_REQUEST_PARAM, ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.MISSING_REQUEST_PART, ex, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.REQUEST_BINDING_ISSUE, ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        SLF4J_LOGGER.warn(ApiError.buildCodeMessageStr(HttpErrorMessages.ARGUMENT_NOT_VALID.getCode(), ex.getMessage()));
        ApiError apiError = new ApiError(HttpErrorMessages.ARGUMENT_NOT_VALID.getCode(), HttpErrorMessages.ARGUMENT_NOT_VALID.getMessage(), getRequestURI(request));
        fillBindingResultError(apiError, ex.getBindingResult());
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.NO_HANDLER_FOUND, ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
            AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.ASYNC_REQUEST_TIMEOUT, ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleErrorResponseException(
            ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleApiError(HttpErrorMessages.ERROR_RESPONSE, ex, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
            ConversionNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Object[] args = {ex.getPropertyName(), ex.getValue()};
        String defaultDetail = "Failed to convert '" + args[0] + "' with value: '" + args[1] + "'";
        SLF4J_LOGGER.warn(ApiError.buildCodeMessageStr(HttpErrorMessages.CONVERSATION_NOT_SUPPORTED.getCode(), defaultDetail));
        ApiError apiError = new ApiError(HttpErrorMessages.CONVERSATION_NOT_SUPPORTED, getRequestURI(request));
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Object[] args = {ex.getPropertyName(), ex.getValue()};
        String defaultDetail = "Failed to convert '" + args[0] + "' with value: '" + args[1] + "'";
        SLF4J_LOGGER.warn(ApiError.buildCodeMessageStr(HttpErrorMessages.TYPE_MISMATCH.getCode(), defaultDetail));
        ApiError apiError = new ApiError(HttpErrorMessages.TYPE_MISMATCH, getRequestURI(request));
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        SLF4J_LOGGER.warn(ApiError.buildCodeMessageStr(HttpErrorMessages.HTTP_MESSAGE_NOT_READABLE.getCode(), ex.getMessage()));
        ApiError apiError = new ApiError(HttpErrorMessages.HTTP_MESSAGE_NOT_READABLE, getRequestURI(request));
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        SLF4J_LOGGER.warn(ApiError.buildCodeMessageStr(HttpErrorMessages.HTTP_MESSAGE_NOT_WRITABLE.getCode(), ex.getMessage()));
        ApiError apiError = new ApiError(HttpErrorMessages.HTTP_MESSAGE_NOT_WRITABLE, getRequestURI(request));
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getErrorCode(), ex.getErrorMessage(), getRequestURI(request));
        SLF4J_LOGGER.warn(apiError.getCodeMessage());
        return handleExceptionInternal(ex, apiError, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUnhandledException(Exception ex, WebRequest request) {
        SLF4J_LOGGER.error(ApiError.buildCodeMessageStr(SystemErrorMessages.INTERNAL_ERROR.getCode(), ex.getMessage()), ex);
        ApiError apiError = new ApiError(SystemErrorMessages.INTERNAL_ERROR, getRequestURI(request));
        return handleExceptionInternal(ex, apiError, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> handleApiError(HttpErrorMessages errorMsg, Exception ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ApiError apiError = new ApiError(errorMsg.getCode(), ex.getMessage(), getRequestURI(request));
        SLF4J_LOGGER.warn(apiError.getCodeMessage());
        return handleExceptionInternal(ex, apiError, headers, statusCode, request);
    }

    private URI getRequestURI(WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        HttpServletRequest httpServletRequest = servletWebRequest.getNativeRequest(HttpServletRequest.class);
        return URI.create(httpServletRequest.getRequestURI());
    }

    private void fillBindingResultError(ApiError apiError, BindingResult bindingResult) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String field = fieldError.getField();
            String rejectedValue = fieldError.getRejectedValue() == null ?
                    null :
                    fieldError.getRejectedValue().toString();
            String message = fieldError.getDefaultMessage();
            ApiErrorDetail detail = new ApiValidationErrorDetail(field, rejectedValue, message);
            apiError.addDetail(detail);
        }
    }
}
