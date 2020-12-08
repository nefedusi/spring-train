package org.example.springtrain.controller;

import static org.example.springtrain.util.Constants.REQUEST_ID_HEADER;

import org.example.springtrain.exception.MyAnotherException;
import org.example.springtrain.exception.MyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/unsafe/my-exception")
    public String throwMyException() throws MyException {
        throw new MyException("MyException request accepted - MyException is thrown");
    }

    @GetMapping("/unsafe/my-another-exception")
    public String throwMyAnotherException(@RequestHeader(value = REQUEST_ID_HEADER, required = false) String requestId) throws MyAnotherException {
        throw new MyAnotherException("MyAnotherException request accepted - MyAnotherException is thrown, requestId = \"" + requestId + "\"");
    }
}
