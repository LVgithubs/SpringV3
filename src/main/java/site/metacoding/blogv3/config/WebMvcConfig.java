package site.metacoding.blogv3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * WebMvcConfig
 */
public class WebMvcConfig implements WebMvcConfigurer { // web.xml 설정파일 = POJO
    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + uploadFolder) // file 프로토콜은(시스템 경로 찾을때는) :/// 사용
                .setCachePeriod(60 * 60) // 초 단위 = 한시간
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

    }

}