package com.zhurong.platform.auth.api;

import com.zhurong.platform.auth.dto.CreateUserRequest;
import com.zhurong.platform.auth.dto.SysUserDTO;
import com.zhurong.platform.auth.dto.SysUserClientBindingDTO;
import com.zhurong.platform.auth.dto.SysUserPageQuery;
import com.zhurong.platform.auth.vo.SysUserVO;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对外契约接口
 * <p>
 * 说明：仅定义接口契约
 */
@RequestMapping("/sysUser")
public interface ISysUserApi {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    ApiResponse
            <PageResponse
                    <SysUserVO>> page(@SpringQueryMap SysUserPageQuery pageQuery);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <SysUserVO> getById(@PathVariable Long id);

    /**
     * 新增
     */
    @PostMapping
    ApiResponse
            <Long> save(@Valid @RequestBody SysUserDTO dto);

    /**
     * 创建可登录账号并绑定角色、部门及可选的 Windows 客户端主机名。
     */
    @PostMapping("/create")
    ApiResponse<Boolean> create(@Valid @RequestBody CreateUserRequest request);

    /**
     * 更新
     */
    @PutMapping("/{id}")
    ApiResponse
            <Boolean> update(
            @PathVariable Long id,
            @Valid @RequestBody SysUserDTO dto
    );

    /** 绑定或解除账号对应的 Windows 客户端。 */
    @PutMapping("/{id}/client-binding")
    ApiResponse<Boolean> updateClientBinding(
            @PathVariable Long id,
            @Valid @RequestBody SysUserClientBindingDTO dto
    );

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    ApiResponse
            <Boolean> remove(@PathVariable Long id);

    /**
     * 批量删除
     */
    @DeleteMapping
    ApiResponse
            <Boolean> batchRemove(@RequestBody List
            <Long> ids);
}
