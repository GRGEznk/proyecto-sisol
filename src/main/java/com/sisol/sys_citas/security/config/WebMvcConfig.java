package com.sisol.sys_citas.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

import com.sisol.sys_citas.security.AutorizacionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final AutorizacionInterceptor autorizacionInterceptor;

    public WebMvcConfig(AutorizacionInterceptor autorizacionInterceptor){
        this.autorizacionInterceptor = autorizacionInterceptor;
    }

    // Registrar el interceptor
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(autorizacionInterceptor)
        .addPathPatterns("/**")
        // No aplicar el interceptor en estas rutas
        .excludePathPatterns(
            "/auth/**",
            "/css/**",
            "/js/**", 
            "/img/**",
            "/paciente/registrar",
            "/paciente/consultar/*",
            "/",
            "/inicio"
        );
    }
    
    // Configurar recursos estáticos
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
    }
}
