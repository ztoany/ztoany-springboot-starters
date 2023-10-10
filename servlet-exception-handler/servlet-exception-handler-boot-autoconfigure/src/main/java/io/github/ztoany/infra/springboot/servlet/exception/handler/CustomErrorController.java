package io.github.ztoany.infra.springboot.servlet.exception.handler;

import io.github.ztoany.infra.exception.SystemErrorMessages;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 *
 * TODO errorHtml should use the same error attributes as error
 *
 */
@Controller
public class CustomErrorController extends BasicErrorController {
    private static final Logger SLF4J_LOGGER = LoggerFactory.getLogger(CustomErrorController.class);


    private static final String CODE_ATTR = "code";
    private static final String MESSAGE_ATTR = "message";
    private static final String INSTANCE_ATTR = "instance";

    private static final String PATH_ATTR = "path";

    private static final String STATUS_ATTR = "status";

    private static final String TIMESTAMP_ATTR = "timestamp";

    public CustomErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties,
                                 List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(status);
        }
        Map<String, Object> body = handleErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        return new ResponseEntity<>(body, status);
    }

    private Map<String, Object> handleErrorAttributes(HttpServletRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = getErrorAttributes(request, options);
        errorAttributes.remove("exception");
        errorAttributes.remove("trace");
        errorAttributes.remove("errors");
        errorAttributes.remove("error");

        String path = (String)errorAttributes.get(PATH_ATTR);
        errorAttributes.put(INSTANCE_ATTR, path);
        errorAttributes.remove(PATH_ATTR);

        boolean errLog = false;
        ApiError apiError = null;
        Integer status = (Integer)errorAttributes.get(STATUS_ATTR);
        if(HttpStatus.NOT_FOUND.value() == status) {
            apiError = new ApiError(HttpErrorMessages.RESOURCE_NOT_FOUND);
        }

        if(status >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            apiError = new ApiError(SystemErrorMessages.INTERNAL_ERROR);
            errLog = true;
        }
        errorAttributes.remove(STATUS_ATTR);

        if(apiError != null) {
            errorAttributes.put(CODE_ATTR, apiError.getCode());
            errorAttributes.put(MESSAGE_ATTR, apiError.getMessage());
            if(errLog) {
                SLF4J_LOGGER.error(apiError.getCodeMessage());
            } else {
                SLF4J_LOGGER.warn(apiError.getCodeMessage());
            }
        }

        return errorAttributes;

    }
}
