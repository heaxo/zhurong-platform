package com.zhurong.platform.core.clientimport.service;

import com.zhurong.platform.core.clientimport.dto.ClientCommandRequest;
import com.zhurong.platform.core.clientimport.dto.ClientCommandResponse;
import com.zhurong.platform.core.clientimport.dto.ClientCommandResultReport;

public interface ClientCommandService {

    ClientCommandResponse execute(ClientCommandRequest request);

    boolean complete(String commandId, ClientCommandResultReport report);
}
