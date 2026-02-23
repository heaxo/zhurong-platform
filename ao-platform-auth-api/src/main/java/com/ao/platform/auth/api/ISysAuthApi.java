package com.ao.platform.auth.api;

import com.ao.platform.auth.dto.LoginRequest;
import com.ao.platform.auth.vo.LoginResponse;
import com.ao.platform.base.api.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 对外契约接口
 * <p>
 * 说明：仅定义接口契约
 */
@RequestMapping("/auth")
public interface ISysAuthApi {
    /**
     * 登录
     */
    @PostMapping("login")
    ApiResponse<LoginResponse> login(@RequestBody LoginRequest request);
    /**
     * 权限码
     */
    @GetMapping("codes")
    ApiResponse<List<String>> codes();

    @PostMapping("logout")
    ApiResponse<String> logout();
}