package com.smartCode.ecommerce.util.jwt.enteryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error(request.getMethod() + " / " + request.getRequestURI() + " - " + authException.getMessage());
        if (request.getRequestURI().equals("/api/v1/admin/auth/renew") || request.getRequestURI().equals("/api/v1/auth/renew"))
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error: Forbidden");
        else
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }

}