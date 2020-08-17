package com.schedulepiloto.core.exception;

import com.schedulepiloto.core.dto.model.ErrorAuditDto;
import com.schedulepiloto.core.service.ErrorAuditService;
import com.schedulepiloto.core.util.CommonUtil;
import com.schedulepiloto.core.util.SecurityUtil;
import com.schedulepiloto.core.util.dto.ErrorResponseDto;
import com.schedulepiloto.core.util.dto.ResponseDto;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class SchedulePilotoExceptionHandler extends ResponseEntityExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(SchedulePilotoExceptionHandler.class);

    @Autowired
    private ErrorAuditService errorAuditService;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, ExceptionCode.ERROR_UNKNOWN.getCode(), ExceptionUtils.getStackTrace(ex));
        List<String> details = new ArrayList<>();
        details.add(request.getDescription(false));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ExceptionCode.ERROR_UNKNOWN.getCode(), LocalDateTime.now(), ExceptionCode.ERROR_UNKNOWN.getDescription(), details);
        ResponseDto<ErrorResponseDto> error = new ResponseDto(ResponseDto.ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorResponseDto);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public final ResponseEntity<Object> handleAllFailuresAuthentications(InsufficientAuthenticationException ex, WebRequest request) {
        LOGGER.error(SecurityUtil.USER_AUTHENTICATION_ERROR_MESSAGE);
        LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, HttpStatus.UNAUTHORIZED.value(), ExceptionUtils.getStackTrace(ex));
        List<String> details = new ArrayList<>();
        details.add(request.getDescription(false));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(String.valueOf(HttpStatus.UNAUTHORIZED.value()), LocalDateTime.now(), ExceptionCode.ERROR_AUTHENTICATION.getDescription(), details);
        ResponseDto<ErrorResponseDto> error = new ResponseDto(ResponseDto.ERROR_CODE, HttpStatus.UNAUTHORIZED.getReasonPhrase(), errorResponseDto);
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(SchedulePilotoException.class)
    public final ResponseEntity<Object> handleMyventoryExceptions(SchedulePilotoException ex, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(webRequest.getDescription(false));
        if (ex.getError() == null) {
            LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, ExceptionCode.ERROR_CLIENT.getCode(), ExceptionUtils.getStackTrace(ex));
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(ExceptionCode.ERROR_CLIENT.getCode(), LocalDateTime.now(), ex.getMessage(), details);
            ResponseDto<ErrorResponseDto> error = new ResponseDto(ResponseDto.ERROR_CODE, ExceptionCode.ERROR_CLIENT.getDescription(), errorResponseDto);
            saveError(error, webRequest, HttpStatus.BAD_REQUEST);
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        } else {
            LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, ex.getError().getCode(), ExceptionUtils.getStackTrace(ex));
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getError().getCode(), LocalDateTime.now(), ex.getError().getDescription(), details);
            ResponseDto<ErrorResponseDto> error = new ResponseDto(ResponseDto.ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorResponseDto);
            saveError(error, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(TokenException.class)
    public final ResponseEntity<Object> handleTokenInvalidExceptions(TokenException ex, WebRequest request) {
        LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, ex.getError().getCode(), ExceptionUtils.getStackTrace(ex));
        List<String> details = new ArrayList<>();
        details.add(request.getDescription(false));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getError().getCode(), LocalDateTime.now(), ex.getError().getDescription(), details);
        ResponseDto<ErrorResponseDto> error = new ResponseDto(ResponseDto.ERROR_CODE, HttpStatus.BAD_REQUEST.getReasonPhrase(), errorResponseDto);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<Object> handleDataAccessExceptions(DataAccessException ex, WebRequest request) {
        LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, ExceptionCode.ERROR_DATA_ACCESS.getCode(), ExceptionUtils.getStackTrace(ex));
        List<String> details = new ArrayList<>();
        details.add(request.getDescription(false));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ExceptionCode.ERROR_DATA_ACCESS.getCode(), LocalDateTime.now(), ExceptionCode.ERROR_DATA_ACCESS.getDescription(), details);
        ResponseDto<ErrorResponseDto> error = new ResponseDto(ResponseDto.ERROR_CODE, HttpStatus.BAD_REQUEST.getReasonPhrase(), errorResponseDto);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedRollbackException.class)
    public final ResponseEntity<Object> handleUnexpectedRollbackExceptions(UnexpectedRollbackException ex, WebRequest request) {
        LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, ExceptionCode.ERROR_DATA_ACCESS_ROLl_BACK.getCode(), ExceptionUtils.getStackTrace(ex));
        List<String> details = new ArrayList<>();
        details.add(request.getDescription(false));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ExceptionCode.ERROR_DATA_ACCESS_ROLl_BACK.getCode(), LocalDateTime.now(), ex.getMessage(), details);
        ResponseDto<ErrorResponseDto> error = new ResponseDto(ResponseDto.ERROR_CODE, HttpStatus.BAD_REQUEST.getReasonPhrase(), errorResponseDto);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolationExceptions(DataIntegrityViolationException ex, WebRequest request) {
        LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, ExceptionCode.ERROR_DATA_ACCESS_INTEGRATION_VIOLATION.getCode(), ExceptionUtils.getStackTrace(ex));
        List<String> details = new ArrayList<>();
        details.add(request.getDescription(false));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ExceptionCode.ERROR_DATA_ACCESS_INTEGRATION_VIOLATION.getCode(), LocalDateTime.now(), ExceptionCode.ERROR_DATA_ACCESS_INTEGRATION_VIOLATION.getDescription(), details);
        ResponseDto<ErrorResponseDto> error = new ResponseDto(ResponseDto.ERROR_CODE, HttpStatus.BAD_REQUEST.getReasonPhrase(), errorResponseDto);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest webRequest) {
//        LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, ExceptionCode.ERROR_INVALID_DATA.getCode(), ExceptionUtils.getStackTrace(ex));
        LOGGER.error(CommonUtil.LOG_ERROR_DEFAULT, ExceptionCode.ERROR_INVALID_DATA.getCode(), ex.getMessage());
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        details.add(webRequest.getDescription(false));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ExceptionCode.ERROR_INVALID_DATA.getCode(), LocalDateTime.now(), ExceptionCode.ERROR_INVALID_DATA.getDescription(), details);
        ResponseDto<ErrorResponseDto> error = new ResponseDto(ResponseDto.ERROR_CODE, HttpStatus.BAD_REQUEST.getReasonPhrase(), errorResponseDto);
        saveError(error, webRequest, HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    public void saveError(ResponseDto<ErrorResponseDto> errorResponseDtoResponseDto, WebRequest request, HttpStatus httpStatus) {
        ErrorAuditDto errorAuditDto = ErrorAuditDto.builder()
                .code(errorResponseDtoResponseDto.getResult().getCode())
                .message(errorResponseDtoResponseDto.getResult().getMessage())
                .details(errorResponseDtoResponseDto.getResult().getDetails().stream().toArray(String[]::new))
                .httpSessionId(request == null ? "" : request.getSessionId())
                .uri(((ServletWebRequest) request).getRequest().getRequestURI())
                .httpStatus(httpStatus.value() + " - " + httpStatus.getReasonPhrase())
                .build();
        this.errorAuditService.saveCommonError(errorAuditDto);
    }
}
