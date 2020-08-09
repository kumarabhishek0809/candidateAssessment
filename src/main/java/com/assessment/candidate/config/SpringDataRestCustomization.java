package com.assessment.candidate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class SpringDataRestCustomization extends
        RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config) {

        config.getCorsRegistry().addMapping("/**");
    }
}
