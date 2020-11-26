package org.example.springtrain.controller;

import org.example.springtrain.config.IsAdmin;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

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

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/safe/secured/user-and-admin")
    public String securedUserAndAdminAllowed() {
        return "Endpoint allowed for user and admin roles. Secured annotation";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/safe/secured/only-admin")
    public String securedAdminAllowed() {
        return "Endpoint allowed only for admin. Secured annotation";
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/safe/roles-allowed/user-and-admin")
    public String rolesAllowedUserAndAdminAllowed() {
        return "Endpoint allowed for user and admin roles. RolesAllowed annotation";
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/safe/roles-allowed/only-admin")
    public String rolesAllowedAdminAllowed() {
        return "Endpoint allowed only for admin. RolesAllowed annotation";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/safe/pre-authorize/user-and-admin")
    public String preAuthorizeUserAndAdminAllowed() {
        return "Endpoint allowed for user and admin roles. PreAuthorize annotation";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/safe/pre-authorize/only-admin")
    public String preAuthorizeAdminAllowed() {
        return "Endpoint allowed only for admin. PreAuthorize annotation";
    }

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/safe/post-authorize/user-and-admin")
    public String postAuthorizeUserAndAdminAllowed() {
        System.out.println("postAuthorizeUserAndAdminAllowed method called");
        return "Endpoint allowed for user and admin roles. PostAuthorize annotation";
    }

    //Если вызвать метод от лица user с НЕправильным паролем, то метод не вызовется.
    //А если вызвать от лица user с правильным - то вызовется, но результат не вернётся клиенту.
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/safe/post-authorize/only-admin")
    public String postAuthorizeAdminAllowed() {
        System.out.println("postAuthorizeUserAndAdminAllowed method called");
        return "Endpoint allowed only for admin. PostAuthorize annotation";
    }

    @IsAdmin
    @GetMapping("/safe/custom-annotation/only-admin")
    public String customAnnotaionAdminAllowed() {
        return "Endpoint allowed only for admin. IsAdmin custom annotation";
    }
}
