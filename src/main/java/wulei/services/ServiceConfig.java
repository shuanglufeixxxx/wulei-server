package wulei.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ServiceConfig {

    private ImageService imageService = null;

    @Bean
    ImageService imageServiceInstance() {
        if (this.imageService == null) {
            this.imageService = new ImageService();
        }

        return this.imageService;
    }
}