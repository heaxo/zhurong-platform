package com.zhurong.platform.core.clientimport.target;

public interface ClientTargetResolver {

    /**
     * 解析最终执行任务的客户端，返回值不能为空。
     */
    String resolve(ClientTargetResolveContext context);
}
