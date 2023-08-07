package com.smartCode.ecommerce.util;

import com.smartCode.ecommerce.model.dto.UserDetailsImpl;
import com.smartCode.ecommerce.util.constants.Role;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class CurrentUser {

    public static Integer getId() {
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getId();
    }

    public static String getRole() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(Object::toString)
                .toList().get(0);
    }

    public static boolean isAdmin() {
        return getRole().equals(Role.ROLE_ADMIN.getName());
    }

    public static boolean isUser() {
        return getRole().equals(Role.ROLE_USER.getName());
    }
}
