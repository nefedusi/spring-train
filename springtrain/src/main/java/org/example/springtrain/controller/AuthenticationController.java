package org.example.springtrain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtrain.dto.AuthenticationDto;
import org.example.springtrain.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthenticationDto authenticationDto) {
        log.info("Authentication attempt; login={}, password={}", authenticationDto.getLogin(), authenticationDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.getLogin(), authenticationDto.getPassword()));
        return jwtTokenProvider.generateToken(authentication);
    }

    //GET метод по умолчанию не защищён CSRF
    @GetMapping(value = "/unsafe/check")
    public String checkGet() {
        return "GET works!";
    }

    //POST метод по умолчанию защищён CSRF
    @PostMapping(value = "/unsafe/check")
    public String checkPost() {
        return "POST works!";
    }
}
