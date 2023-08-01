package com.smartCode.ecommerce.configuration;

import com.smartCode.ecommerce.util.constants.Path;
import com.smartCode.ecommerce.util.constants.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(
                        Path.USERS + Path.REGISTER,
                        Path.USERS + Path.FILTER,
                        Path.USERS + Path.SEARCH,
                        Path.CARDS + Path.CREATE,
                        Path.PRODUCTS + Path.FIND,
                        Path.PRODUCTS + Path.FIND_ALL,
                        Path.PRODUCTS + Path.FILTER,
                        Path.PRODUCTS + Path.SEARCH)
                .permitAll()
                .and()
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails ramesh = User.builder()
                .username("narek")
                .password(passwordEncoder().encode("password"))
                .roles(Role.USER_ROLE)
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles(Role.ADMIN_ROLE)
                .build();

        return new InMemoryUserDetailsManager(ramesh, admin);
    }
}