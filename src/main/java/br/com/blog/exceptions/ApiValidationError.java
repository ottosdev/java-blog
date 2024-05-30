package br.com.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ApiValidationError extends ApiError {
    private List<String> validationErrors;

    public ApiValidationError(int status, String message, String code, List<String> validationErrors) {
        super(status, message, code);
        this.validationErrors = validationErrors;
    }

}
