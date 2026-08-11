package com.zhurong.platform.custom.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XyImportTaskServiceTest {
    @Test
    void compositeIdentityPreservesHyphensInPlanNumber() {
        String composite = XyImportTaskService.combineErpIdentity("12345", "PLAN-2026-08");
        XyImportTaskService.ErpIdentity identity = XyImportTaskService.splitErpIdentity(composite);
        assertThat(identity.erpInternalCode()).isEqualTo("12345");
        assertThat(identity.planNumber()).isEqualTo("PLAN-2026-08");
    }

    @Test
    void legacyCusRefWithoutSeparatorRemainsReadable() {
        XyImportTaskService.ErpIdentity identity = XyImportTaskService.splitErpIdentity("12345");
        assertThat(identity.erpInternalCode()).isEqualTo("12345");
        assertThat(identity.planNumber()).isEmpty();
    }

    @Test
    void importedOrderIdentityUsesErpCodeInsteadOfRepeatedOrderNumber() {
        String first = XyImportTaskService.importedOrderKey("10001-PLAN-001", "JOB-01");
        String second = XyImportTaskService.importedOrderKey("10002-PLAN-001", "JOB-01");

        assertThat(first).isNotEqualTo(second);
    }
}
