package com.smartCode.ecommerce.util.jwt;

import com.smartCode.ecommerce.model.dto.UserDetailsImpl;
import com.smartCode.ecommerce.repository.AccessTokenRepository;
import com.smartCode.ecommerce.service.token.AccessTokenService;
import com.smartCode.ecommerce.util.currentUser.CurrentUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AccessTokenRepository tokenRepository;
    private final AccessTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final var token = header.split(" ")[1].trim();
        if (!jwtTokenProvider.validateJwtTokenSignature(token)) {
            chain.doFilter(request, response);
            return;
        }

        /*if(!tokenRepository.existsTokenByToken(token.split("\\.")[2])){
            chain.doFilter(request,response);
            return;
        }*/

        if(tokenService.getToken(token.split("\\.")[2]) == null){
            chain.doFilter(request,response);
            return;
        }

        var userDetails = UserDetailsImpl.build(
                jwtTokenProvider.getId(token),
                jwtTokenProvider.getSubject(token),
                jwtTokenProvider.getRole(token)
        );

        var authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}