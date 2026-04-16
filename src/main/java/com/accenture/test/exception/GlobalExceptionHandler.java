package com.accenture.test.exception;

import com.accenture.test.dto.ResponseDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseDTO<Void> handleValidationErrors(MethodArgumentNotValidException ex) {

        String error = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(e -> e.getDefaultMessage())
                .orElse("Error de validación");

        return new ResponseDTO<>(400, error, null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseDTO<Void> handleRuntime(RuntimeException ex) {
        return new ResponseDTO<>(404, ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseDTO<Void> handleGeneral(Exception ex) {
        return new ResponseDTO<>(500, "Error interno del servidor", null);
    }
}