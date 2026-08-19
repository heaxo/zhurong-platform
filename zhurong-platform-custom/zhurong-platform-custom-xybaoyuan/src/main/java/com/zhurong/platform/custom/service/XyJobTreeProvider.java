package com.zhurong.platform.custom.service;

import com.zhurong.platform.core.lantek.vo.JobBrowserTreeVO;

import java.util.List;

/**
 * 作业目录数据源。平台 API 和 Windows 客户端使用不同实现，避免客户端后台任务依赖登录令牌。
 */
public interface XyJobTreeProvider {

    List<JobBrowserTreeVO> getTree();
}
