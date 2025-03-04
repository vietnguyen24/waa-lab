package miu.edu.lab.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.lab.dto.LoginRequest;
import miu.edu.lab.dto.LoginResponse;
import miu.edu.lab.dto.RefreshTokenRequest;
import miu.edu.lab.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin
@RequiredArgsConstructor
public class AuthContoller {
  private final AuthService authService;

  @PostMapping
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    var loginResponse = authService.login(loginRequest);
    return ResponseEntity.ok().body(loginResponse);
  }

  @PostMapping("/refreshToken")
  public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
    return authService.refreshToken(refreshTokenRequest);
  }
}
