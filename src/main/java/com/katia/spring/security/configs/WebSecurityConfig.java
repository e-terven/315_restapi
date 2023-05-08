package com.katia.spring.security.configs;

import com.katia.spring.security.model.JwtTokenUtil;
import com.katia.spring.security.services.CustomUserDetailsService;
import com.katia.spring.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private CustomUserDetailsService customUserDetailsService;
    private final SuccessUserHandler successUserHandler;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;


    @Autowired
    public void setUserService(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;

    }
    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.successUserHandler = successUserHandler;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
//                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/index","/api/login").permitAll()
                .antMatchers("/api/admin/**").hasAuthority(ADMIN)
                .antMatchers("/api/user/**").hasAnyAuthority(ADMIN, USER)
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
//                .httpBasic()
                .formLogin()
                .successHandler(successUserHandler)
//                .successHandler((request, response, user) -> {
//
//                    // заполните остальные поля userDTO значениями из объекта user
//                    String token = jwtTokenUtil.generateToken(user);
//                    response.addHeader("Authorization", "Bearer " + token);
//                })
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/api/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        // сказать по логину и паролю существует ли такой пользователь. если существует, положить в Security Context
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    public void authenticate(LoginRequest loginRequest) {
//        try {
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
//            if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {   //passwordEncoder.matches(loginRequest.getPassword(),
//                // пароль совпадает, пользователь аутентифицирован
//                Authentication authentication = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                // пароль не совпадает
//                throw new BadCredentialsException("Invalid password");
//            }
//        } catch (UsernameNotFoundException ex) {
//            // пользователь не найден
//            throw new UsernameNotFoundException("User not found");
//        }
//    }




//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}