package com.ao.platform.auth.controller;

import com.ao.platform.auth.api.ISysAuthApi;
import com.ao.platform.auth.dto.LoginRequest;
import com.ao.platform.auth.service.ISysAuthService;
import com.ao.platform.auth.vo.LoginResponse;
import com.ao.platform.auth.web.BaseController;
import com.ao.platform.base.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SysAuthController extends BaseController implements ISysAuthApi {

    private final ISysAuthService authService;

    @Override
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        try{
            LoginResponse response = authService.login(request);
            HttpServletResponse httpResponse = getResponse();
            String cookie = ResponseCookie.from("jwt", response.getAccessToken())
                    .httpOnly(true)
                    .path("/")
                    .maxAge(Duration.ofDays(1))
                    .sameSite("None")
                    .secure(false)
                    .build()
                    .toString();
            httpResponse.addHeader("Set-Cookie", cookie);
            return ApiResponse.success(response);
        }catch (Exception e){
            return ApiResponse.fail(e.getMessage());
        }
    }

    @Override
    public ApiResponse<List<String>> codes() {
        List<String> codes = authService.codes(getCurrentUsername());
        return ApiResponse.success(codes);
    }
    @Override
    public ApiResponse<String> logout() {
        HttpServletRequest req = getRequest();
        HttpServletResponse resp = getResponse();
        resp.addHeader("Set-Cookie", ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(req.isSecure())
                .path("/")
                .maxAge(0)
                .sameSite("None")
                .build().toString());
        resp.addHeader("Set-Cookie", ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(req.isSecure())
                .path("/auth")
                .maxAge(0)
                .sameSite("None")
                .build().toString());
        return ApiResponse.success("");
    }
}
