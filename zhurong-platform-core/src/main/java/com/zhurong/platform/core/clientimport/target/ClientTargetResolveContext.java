package com.zhurong.platform.core.clientimport.target;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientTargetResolveContext {

    private final String requestId;

    private final String requestedTargetClientId;

    private final String businessType;

    private final Object businessData;
}
