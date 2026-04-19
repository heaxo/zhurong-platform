<script lang="ts" setup>
import {
    type OnActionClickParams,
    useVbenVxeGrid,
    type VxeTableGridOptions
} from "#/adapter/vxe-table";
import {useColumns, useGridFormSchema} from "./data";
import {requestGet${entity}Page} from "#/api";
import type {${entity}VO} from "#/api";

import {Page, useVbenDrawer} from '@vben/common-ui';
import {Button,Input, message} from "ant-design-vue";
import {isEmpty} from 'lodash-es';
import {ExportOutlined} from "@ant-design/icons-vue";
import {Plus} from '@vben/icons';
import Form from './modules/form.vue';
import {ref} from "vue";
const selectedRows = ref([]);



const [FormDrawer, formDrawerApi] = useVbenDrawer({
    connectedComponent: Form,
    destroyOnClose: true,
});

function handleSelectionChange({records}) {
    selectedRows.value = records;
}
const [Grid, gridApi] = useVbenVxeGrid({
    formOptions: {
      fieldMappingTime: [['createTime', ['startTime', 'endTime']]],
      schema: useGridFormSchema(),
      submitOnChange: true,
      collapsed: true,
    },
    gridEvents: {
        checkboxChange: handleSelectionChange,
        checkboxAll: handleSelectionChange,
    },
    gridOptions: {
        virtualYConfig: {
            enabled: true,   // 开启纵向虚拟滚动
            gt: 50,
        },
        loading: false,
        columns: useColumns(),
        height: 'auto',
        keepSource: true,
        pagerConfig: {
        },
        proxyConfig: {
            ajax: {
                query: async ({page}, formValues) => {
                    const data = await requestGet${entity}Page({
                        page: page.currentPage,
                        pageSize: page.pageSize,
                        ...formValues,
                    });
                    return data;
                },
            },
        },
        rowConfig: {
            keyField: 'id',
        },

        toolbarConfig: {
            custom: true,
            export: false,
            refresh: {code: 'query'},
            search: true,
            zoom: true,
        },
    } as VxeTableGridOptions<${entity}VO>,
});


function onActionClick(e: OnActionClickParams<${entity}VO>) {
    switch (e.code) {
        case 'delete': {
            break;
        }
        case 'edit': {
            break;
        }
    }
}
function successHandler() {
    message.success('操作成功');
    onRefresh();
}
function onRefresh() {
    gridApi.query();
}
function onCreate() {
    formDrawerApi.setData({}).open();
}
</script>

<template>
    <Page auto-content-height>
        <FormDrawer @success="successHandler" />
        <Grid>
            <template #toolbar-tools>
                <Button type="primary" @click="onCreate">
                    <Plus class="size-5" />
                    {{ $t('ui.actionTitle.create') }}
                </Button>
            </template>
            <template #toolbar-actions>

            </template>

        </Grid>
    </Page>
</template>

<style scoped>

</style>
