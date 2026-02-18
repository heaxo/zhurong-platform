package com.ao.platform.auth.controller;

import com.ao.platform.auth.api.ISysAuthApi;
import com.ao.platform.auth.dto.LoginRequest;
import com.ao.platform.auth.service.ISysAuthService;
import com.ao.platform.auth.vo.LoginResponse;
import com.ao.platform.base.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SysAuthController implements ISysAuthApi {

    private final ISysAuthService authService;

    @Override
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ApiResponse.success(response);
    }
}
