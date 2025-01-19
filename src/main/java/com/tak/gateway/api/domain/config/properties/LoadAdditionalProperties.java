package com.tak.gateway.api.domain.config.properties;

import com.tak.gateway.api.common.exception.ApplicationException;
import com.tak.gateway.api.common.utile.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Profiles;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LoadAdditionalProperties implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    private ResourceLoader loader = new DefaultResourceLoader();

    private List<PropertySource<?>> loadProperties(String name, String resourcePath) throws IOException {
        Resource resource = loader.getResource(resourcePath);

        List<PropertySource<?>> yamlPropertiesList = new ArrayList<>();
        if (resource.exists()) {
            log.info("Enviroment Properties Loaded : {}", resource.getFilename());
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
            yamlPropertiesList = sourceLoader.load(name, resource);
        }

        return yamlPropertiesList;
    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        try {
            final boolean isDevProfileActive   = event.getEnvironment().acceptsProfiles(Profiles.of("dev"));
            final boolean isPrdProfileActive   = event.getEnvironment().acceptsProfiles(Profiles.of("prd"));

            final String applicationName = System.getProperty("app.name");
            if (StringUtil.isBlank(applicationName)) {
                throw new ApplicationException("[app.name] is Empty. Set [app.name] in Environment");
            }
            log.info("SpringBoot Application Service Name : {}", applicationName);

            String resourcePath = "classpath:";
            if (isPrdProfileActive) {
                log.info("SpringBoot Application Enviroment Profile : prd");
                System.setProperty("spring.current.profile", "prd");
                resourcePath = resourcePath + applicationName + "-prd.yml";
            }  else if (isDevProfileActive) {
                log.info("SpringBoot Application Enviroment Profile : dev");
                System.setProperty("spring.current.profile", "dev");
                resourcePath = resourcePath + applicationName + "-dev.yml";
            } else {
                log.info("SpringBoot Application Enviroment Profile : local");
                System.setProperty("spring.current.profile", "local");
                resourcePath = resourcePath + applicationName + "-local.yml";
            }
            List<PropertySource<?>> propertySourceList = loadProperties("application", resourcePath);

            for (PropertySource<?> currentYmlEnv : propertySourceList) {
                event.getEnvironment().getPropertySources().addLast(currentYmlEnv);
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
