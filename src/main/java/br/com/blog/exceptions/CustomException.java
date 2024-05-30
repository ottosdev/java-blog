package br.com.blog.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    public CustomException(String message, HttpStatus status, String code) {
        super(message);
        this.status = status;
        this.code = code;
    }

}
