package com.zhurong.platform.core.clientimport.configuration;

import com.zhurong.platform.core.clientimport.target.ClientTargetResolver;
import com.zhurong.platform.core.clientimport.target.DefaultClientTargetResolver;
import com.zhurong.platform.core.clientimport.validation.ImportReferenceValidator;
import com.zhurong.platform.core.clientimport.validation.NoOpImportReferenceValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClientCommunicationEnabled
public class ClientImportExtensionConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ClientTargetResolver clientTargetResolver(ClientCommunicationProperties properties) {
        return new DefaultClientTargetResolver(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public ImportReferenceValidator importReferenceValidator() {
        return new NoOpImportReferenceValidator();
    }
}
