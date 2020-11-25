package org.example.springtrain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityCheckController {

    //curl -i http://localhost:8080/thing/all
    //curl -i -u user:userpass http://localhost:8080/thing/all
    //Помнить, что при повторном запросе от того же пользователя, но с другим паролем, доступ к эндпоинту будет! Т.к. отправляется cookie JSESSIONID
    @GetMapping("/safe")
    public String safe() {
        return "Safe endpoint";
    }

    @GetMapping("/unsafe/")
    public String unsafe() {
        return "Unsafe endpoint";
    }

    @GetMapping("/unsafe")
    public String unsafeWithoutSlash() {
        return "This endpoint is safe because it doesn't have '/' in the end";
    }

    @GetMapping("/unsafe/another")
    public String unsafeAnother() {
        return "Another unsafe endpoint";
    }

    @GetMapping("/safe/user-and-admin")
    public String userAndAdminAllowed() {
        return "Endpoint allowed for user and admin roles";
    }

    @GetMapping("/safe/only-user")
    public String userAllowed() {
        return "Endpoint allowed only for user. It's a stange case, usually everything allowed for user is " +
                "also allowed for admin";
    }

    @GetMapping("/safe/only-admin")
    public String adminAllowed() {
        return "Endpoint allowed only for admin";
    }
}
