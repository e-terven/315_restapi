package com.katia.spring.security.configs;

import com.katia.spring.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    private final UserService userService;
    @Autowired
    public SuccessUserHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils
                .authorityListToSet(authentication.getAuthorities());
        System.out.print(roles);

        if (roles.contains("ADMIN")) {
            httpServletResponse.sendRedirect("/api/admin");
        } else if (roles.contains("USER")){
            httpServletResponse.sendRedirect("/api/user");
        } else {
            httpServletResponse.sendRedirect("/api/login");
        }
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.sendRedirect("/api/login?error");
    }
}

