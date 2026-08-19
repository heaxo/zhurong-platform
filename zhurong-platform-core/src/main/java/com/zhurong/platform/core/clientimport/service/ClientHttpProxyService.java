package com.zhurong.platform.core.clientimport.service;

import com.zhurong.platform.core.clientimport.dto.ClientHttpProxyRequest;
import com.zhurong.platform.core.clientimport.dto.ClientHttpProxyResponse;

public interface ClientHttpProxyService {

    boolean available(Long userId);

    ClientHttpProxyResponse execute(Long userId, ClientHttpProxyRequest request);
}
