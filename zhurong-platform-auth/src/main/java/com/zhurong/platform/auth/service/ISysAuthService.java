package com.zhurong.platform.auth.service;

import com.zhurong.platform.auth.dto.LoginRequest;
import com.zhurong.platform.auth.vo.LoginResponse;

import java.util.List;

public interface ISysAuthService {

    LoginResponse login(LoginRequest request);
    List<String> codes(String username);

}
