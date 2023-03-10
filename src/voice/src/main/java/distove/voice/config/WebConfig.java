package distove.voice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:3000", "https://distove-app.onstove.com", "http://community-bucket.object-storage.idc-sginfra.net:8080")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
