package vn.iotstar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ví dụ: file:///C:/Users/PC/Documents/storage/
        String path = "file:///" + storageProperties.getLocation() + "/";
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(path);
    }
}
