package miu.edu.lab.service;

import miu.edu.lab.dto.LoginRequest;
import miu.edu.lab.dto.LoginResponse;
import miu.edu.lab.dto.RefreshTokenRequest;

public interface AuthService {
  LoginResponse login(LoginRequest loginRequest);
  LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
