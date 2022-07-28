package com.example.demo.api.auth;

import com.example.demo.Service.impl.ConfirmationTokenService;
import com.example.demo.dto.request.LoginDto;
import com.example.demo.dto.request.SignUpDto;
import com.example.demo.Service.impl.UserService;
import com.example.demo.auth.JwtManager;
import com.example.demo.dto.entity.UserDto;
import com.example.demo.dto.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class AuthController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto signUpRequest) {
        log.info("Signup controller");
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        log.info("Login controller");
        JwtAuthenticationResponse result = userService.login(loginDto);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/signUp/confirm")
    public String confirm(@RequestParam("token") String token){
        return userService.confirmToken(token);
    }



}