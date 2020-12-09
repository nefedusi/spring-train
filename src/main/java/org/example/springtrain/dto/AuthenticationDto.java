package org.example.springtrain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//todo add validation
public class AuthenticationDto {

    private String login;
    private String password;
}
