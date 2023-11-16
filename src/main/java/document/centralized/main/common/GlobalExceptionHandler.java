package document.centralized.main.common;

import document.centralized.main.exception.TinNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRuntimeException(final TinNotFoundException e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        List<String> errors = new ArrayList<>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            errors.add(stackTraceElement + "\n");
        }

        log.debug(errors + "MESSAGE: " + e.getMessage());

        return new ApiError("It is recommended to check the correctness of the entered data and try the search again.",
                e.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now().format(FORMATTER));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        List<String> errors = new ArrayList<>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            errors.add(stackTraceElement + "\n");
        }

        String field = Objects.requireNonNull(e.getFieldError()).getField();

        log.debug(errors + "MESSAGE: " + e.getMessage());

        return new ApiError("Field \"" + field + "\" is incorrect",
                e.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now().format(FORMATTER));
    }

}