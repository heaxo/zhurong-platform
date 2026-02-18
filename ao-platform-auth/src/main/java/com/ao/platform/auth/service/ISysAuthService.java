package com.ao.platform.auth.service;

import com.ao.platform.auth.dto.LoginRequest;
import com.ao.platform.auth.vo.LoginResponse;

public interface ISysAuthService {

    LoginResponse login(LoginRequest request);

}
