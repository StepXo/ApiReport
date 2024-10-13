package com.emazon.ApiReport.Infrastructure.Utils;

import com.emazon.ApiReport.Application.Response.UserResponse;
import com.emazon.ApiReport.Domain.Spi.UserJwtPort;
import com.emazon.ApiReport.Infrastructure.Adapters.Feign.IUserFeign;
import com.emazon.ApiReport.Infrastructure.Adapters.SecurityConfig.jwtconfiguration.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@AllArgsConstructor
public class UserExtractor implements UserJwtPort {
    private final JwtService jwtService;
    private final IUserFeign userFeign;

    @Override
    public String extractUserId() {
        String token = getTokenFromRequest();  // Este es el método que falla
        return token != null ? jwtService.extractUserId(token) : null;
    }

    @Override
    public String extractEmail(long id) {
        UserResponse userResponse = userFeign.getUserById(String.valueOf(id));
        return userResponse != null ? userResponse.getEmail() : null;
    }

    // Asegúrate de que el método sea público
    public String getTokenFromRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String authHeader = request.getHeader(InfraConstants.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith(InfraConstants.BEARER)) {
                return authHeader.substring(7);
            }
        }
        return null;
    }
}

