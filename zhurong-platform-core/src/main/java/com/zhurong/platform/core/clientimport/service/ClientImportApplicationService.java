package com.zhurong.platform.core.clientimport.service;

import com.zhurong.platform.core.clientimport.dto.ClientImportRequest;
import com.zhurong.platform.base.clientimport.dto.PartDrawingArchiveRequest;
import com.zhurong.platform.core.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.core.clientimport.dto.RawMaterialRequest;

import java.util.List;

public interface ClientImportApplicationService {

    boolean importPartDrawing(ClientImportRequest<List<PartDrawingArchiveRequest>> request);

    boolean importProductionOrder(ClientImportRequest<List<ProductionOrderRequest>> request);

    boolean importRawMaterial(ClientImportRequest<List<RawMaterialRequest>> request);

    boolean retryPublish(String taskId);

    boolean ping(String targetClientId);
}
