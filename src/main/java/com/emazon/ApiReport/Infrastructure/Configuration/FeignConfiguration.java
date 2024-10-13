package com.emazon.ApiReport.Infrastructure.Configuration;

import com.emazon.ApiReport.Infrastructure.Adapters.SecurityConfig.jwtconfiguration.JwtService;
import com.emazon.ApiReport.Infrastructure.ExceptionHandler.ControllerErrorDecoder;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class FeignConfiguration {
    private final JwtService userExtractor;
    private final ObjectMapper objectMapper;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String token = InfraConstants.BEARER + userExtractor.getTokenFromRequest();
                template.header(InfraConstants.AUTHORIZATION, token);
            }
        };
    }
    @Bean
    public ErrorDecoder errorDecoder() {
        return new ControllerErrorDecoder(objectMapper);
    }
}
