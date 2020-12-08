package org.example.springtrain.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"stackTrace", "localizedMessage"})
@Getter
@Setter
public class MyException extends RuntimeException {

    private String requestId;

    public MyException(String message) {
        super(message);
    }
}
