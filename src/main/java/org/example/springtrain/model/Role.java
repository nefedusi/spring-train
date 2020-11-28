package org.example.springtrain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_USER(0),
    ROLE_ADMIN(1);

    private final Integer code;
}
