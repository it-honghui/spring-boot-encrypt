package com.hcr.config.filter;

import com.hcr.config.core.EncryptionConfig;
import com.hcr.config.core.EncryptionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class FilterConfig {

    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public FilterRegistrationBean filterRegistration() {
    	EncryptionConfig config = new EncryptionConfig();
    	config.setKey("abcdef0123456780");
    	config.setRequestDecyptUriList(Arrays.asList("/save", "/decryptEntityXml"));
    	config.setResponseEncryptUriList(Arrays.asList("/encryptStr", "/encryptEntity", "/save", "/encryptEntityXml", "/decryptEntityXml"));
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new EncryptionFilter(config));
        registration.addUrlPatterns("/*");
        registration.setName("EncryptionFilter");
        registration.setOrder(1);
        return registration;
    }

}