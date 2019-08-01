package com.hcr.config.autoconfigure;

import com.hcr.config.algorithm.EncryptAlgorithm;
import com.hcr.config.core.EncryptionConfig;
import com.hcr.config.core.EncryptionFilter;
import com.hcr.config.init.ApiEncryptDataInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 加解密自动配置
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(EncryptionConfig.class)
public class EncryptAutoConfiguration {

    @Autowired
    private EncryptionConfig encryptionConfig;

    @Autowired(required = false)
    private EncryptAlgorithm encryptAlgorithm;

    /**
     * 不要用泛型注册Filter,泛型在Spring Boot 2.x版本中才有
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        if (encryptAlgorithm != null) {
            registration.setFilter(new EncryptionFilter(encryptionConfig, encryptAlgorithm));
        } else {
            registration.setFilter(new EncryptionFilter(encryptionConfig));
        }
        registration.addUrlPatterns(encryptionConfig.getUrlPatterns());
        registration.setName("EncryptionFilter");
        registration.setOrder(encryptionConfig.getOrder());
        return registration;
    }

    @Bean
    public ApiEncryptDataInit apiEncryptDataInit() {
        return new ApiEncryptDataInit();
    }
}
