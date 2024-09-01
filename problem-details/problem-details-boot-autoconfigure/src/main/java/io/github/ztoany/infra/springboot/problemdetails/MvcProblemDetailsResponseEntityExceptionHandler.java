package io.github.ztoany.infra.springboot.problemdetails;

import io.github.ztoany.infra.springboot.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MvcProblemDetailsResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger SLF4J_LOGGER = LoggerFactory.getLogger(MvcProblemDetailsResponseEntityExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ProblemDetailsBuilder.violations(ex.getBody(), ex.getBindingResult());
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        HttpStatusCode status = HttpStatus.CONFLICT;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getErrorMessage());
        ProblemDetailsBuilder.code(problemDetail, ex.getErrorCode());
        ProblemDetailsBuilder.timestamp(problemDetail);
        return handleExceptionInternal(ex, problemDetail, null, status, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUnhandledException(Exception ex, WebRequest request) {
        SLF4J_LOGGER.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        var problemDetail = ProblemDetail.forStatusAndDetail(status, status.getReasonPhrase());
        ProblemDetailsBuilder.timestamp(problemDetail);
        return super.handleExceptionInternal(ex, problemDetail, null, status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request) {
        var logLevel = getLogLevel(ex);
        if(logLevel == LogLevel.WARN) {
            SLF4J_LOGGER.warn(ex.getMessage());
        }

        if(ex instanceof ErrorResponse errorResponse) {
            ProblemDetailsBuilder.timestamp(errorResponse.getBody());
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private LogLevel getLogLevel(Exception ex) {
        if(ex instanceof HttpRequestMethodNotSupportedException) {
            return LogLevel.IGNORE;
        } else {
            return LogLevel.WARN;
        }
    }
}
