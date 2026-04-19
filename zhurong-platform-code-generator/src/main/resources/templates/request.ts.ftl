<#assign basePrefixName = "CUSTOM_BASE_PREFIX">
<#if moduleName == "core">
    <#assign basePrefixName = "CORE_BASE_PREFIX">
<#elseif moduleName == "auth">
    <#assign basePrefixName = "AUTH_BASE_PREFIX">
<#elseif moduleName == "custom">
    <#assign basePrefixName = "CUSTOM_BASE_PREFIX">
</#if>

import { ${basePrefixName}, requestClient } from '#/api/request';
import type { ${entity}VO, ${entity}DTO, ${entity}PageQuery } from '#/api';

async function requestGet${entity}Page(params: ${entity}PageQuery) {
return requestClient.get<any>(`${'$'}{${basePrefixName}}/${entity?uncap_first}/page`, {
    params,
    });
    }

    async function request${entity}GetById(id: number | string) {
    return requestClient.get<${entity}VO>(`${'$'}{${basePrefixName}}/${entity?uncap_first}/getById`, {
        params: { id },
        });
        }

        async function requestCreate${entity}(data: ${entity}DTO) {
        return requestClient.post<string>(`${'$'}{${basePrefixName}}/${entity?uncap_first}/save`, data);
            }

            async function requestUpdate${entity}(id: number | string, data: ${entity}DTO) {
            return requestClient.put<boolean>(`${'$'}{${basePrefixName}}/${entity?uncap_first}/update`, data, {
                params: { id },
                });
                }

                async function requestRemove${entity}(id: number | string) {
                return requestClient.delete<boolean>(`${'$'}{${basePrefixName}}/${entity?uncap_first}/remove`, {
                    params: { id },
                    });
                    }

                    async function requestBatchRemove${entity}(data: number[]) {
                    return requestClient.delete<boolean>(`${'$'}{${basePrefixName}}/${entity?uncap_first}/batchRemove`, {
                        data,
                        });
                        }

                        export {
                        requestGet${entity}Page,
                        request${entity}GetById,
                        requestCreate${entity},
                        requestUpdate${entity},
                        requestRemove${entity},
                        requestBatchRemove${entity},
                        };