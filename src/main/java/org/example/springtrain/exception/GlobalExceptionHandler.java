package org.example.springtrain.exception;

import static org.example.springtrain.util.Constants.REQUEST_ID_HEADER;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) //здесь это не нужно, будет нужно, если есть другой ControllerAdvice, который должен отрабатывать в последнюю очередь
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<MyException> myException(MyException e, HttpServletRequest request) {
        String requestId = request.getHeader(REQUEST_ID_HEADER);
        e.setRequestId(requestId);
        ResponseEntity<MyException> responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(e);
        return responseEntity;
    }

    /*
    https://stackoverflow.com/questions/25151264/intercept-requestheader-exception-for-missing-header
    .m.m.a.ExceptionHandlerExceptionResolver : Failure in @ExceptionHandler
    org.example.springtrain.exception.GlobalExceptionHandler#myAnotherException(MyAnotherException, String)

    java.lang.IllegalStateException: Could not resolve parameter [0] in public org.springframework.http.ResponseEntity<
    org.example.springtrain.exception.MyAnotherException>
    org.example.springtrain.exception.GlobalExceptionHandler.myAnotherException(org.example.springtrain.exception.MyAnotherException,java.lang.String):
    No suitable resolver
     */
    @ExceptionHandler(MyAnotherException.class)
    public ResponseEntity<MyAnotherException> myAnotherException(@RequestHeader(value = REQUEST_ID_HEADER, required = false) String requestId,
            MyAnotherException e) {

        e.setRequestId(requestId);
        ResponseEntity<MyAnotherException> responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(e);
        return responseEntity;
    }
}
