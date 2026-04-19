<script lang="ts" setup>
import type { ${entity}VO } from '#/api';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { useVbenForm } from '#/adapter/form';
import { requestCreate${entity}, requestUpdate${entity} } from '#/api';
import { $t } from '#/locales';

import { useFormSchema } from '../data';

const emits = defineEmits(['success']);

const formData = ref<${entity}VO>();

const [Form, formApi] = useVbenForm({
    schema: useFormSchema(),
    showDefaultActions: false,
});

const id = ref();
const [Drawer, drawerApi] = useVbenDrawer({
    async onConfirm() {
        const { valid } = await formApi.validate();
        if (!valid) return;
        const values = await formApi.getValues();
        drawerApi.lock();
        (id.value ? requestUpdate${entity}({ id: id.value, ...values }) : requestCreate${entity}(values))
            .then(() => {
                emits('success');
                drawerApi.close();
            })
            .catch(() => {
                drawerApi.unlock();
            });
    },
    onOpenChange(isOpen) {
        if (isOpen) {
            const data = drawerApi.getData<${entity}VO>();
            formApi.resetForm();
            if (data) {
                formData.value = data;
                id.value = data.id;
                formApi.setValues(data);
            } else {
                id.value = undefined;
            }

        }
    },
});


const getDrawerTitle = computed(() => {
    return formData.value?.id
        ? $t('common.edit')
        : $t('common.create');
});
</script>
<template>
    <Drawer :title="getDrawerTitle">
        <Form>
        </Form>
    </Drawer>
</template>
<style lang="css" scoped>
</style>
