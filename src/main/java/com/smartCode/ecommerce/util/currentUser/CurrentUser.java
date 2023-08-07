package com.smartCode.ecommerce.util.currentUser;

import com.smartCode.ecommerce.model.dto.UserDetailsImpl;
import com.smartCode.ecommerce.util.constants.Role;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class CurrentUser {

    public static Integer getId() {
        var principal = (UserDetailsImpl)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getId();
    }

    public static String getRole() {
        var principal = (UserDetailsImpl)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
         return principal.getAuthorities()
                .stream()
                .map(Object::toString)
                .toList().get(0);
    }

    public static Boolean isUser(){
        return getRole().equals(Role.ROLE_USER.getName());
    }

    public static Boolean isAdmin(){
        return getRole().equals(Role.ROLE_ADMIN.getName());
    }
}
