package com.zhurong.platform.core.lantek.vo;

import lombok.Data;

import java.util.List;

/*
 * @Author zhurong
 * @Description JobBrowserTreeVO
 * @Date 2026/4/4 17:11
 **/
@Data
public class JobBrowserTreeVO {
    private String id;
    private String parentId;
    private String label;
    private Boolean isFolder;
    private List<JobBrowserTreeVO> children;
}
