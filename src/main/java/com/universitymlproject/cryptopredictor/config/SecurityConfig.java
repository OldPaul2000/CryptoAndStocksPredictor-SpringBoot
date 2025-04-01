package com.universitymlproject.cryptopredictor.config;

import com.universitymlproject.cryptopredictor.filter.CsrfCookieFilter;
import com.universitymlproject.cryptopredictor.filter.JwtValidatorFilter;
import com.universitymlproject.cryptopredictor.repository.JwtRepository;
import com.universitymlproject.cryptopredictor.securityexceptionhandling.CustomAccessDeniedHandler;
import com.universitymlproject.cryptopredictor.service.JwtService;
import com.universitymlproject.cryptopredictor.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfig {

    private JwtRepository jwtRepository;
    private JwtService jwtService;
    private Constants constants;

    @Autowired
    public SecurityConfig(JwtRepository jwtRepository,
                          JwtService jwtService,
                          Constants constants) {
        this.jwtRepository = jwtRepository;
        this.jwtService = jwtService;
        this.constants = constants;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        httpSecurity.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.setAllowedOrigins(List.of("http://localhost:1234", "http://192.168.0.103:1234"));
                corsConfig.setAllowCredentials(true);
                corsConfig.setAllowedMethods(Collections.singletonList("*"));
                corsConfig.setAllowedHeaders(Collections.singletonList("*"));
                corsConfig.setMaxAge(3600L);

                return corsConfig;
            }
        }));

        httpSecurity.csrf(config -> {config
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("/api/v1/users/login");
                })
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtValidatorFilter(jwtRepository, constants), BasicAuthenticationFilter.class)
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/users/login").permitAll()
                        .requestMatchers("/api/v1/users/{userId}").hasRole("USER")
                        .requestMatchers("/api/v1/users/hello").hasRole("USER")
                        .requestMatchers("/api/v1/users/goodbye").hasRole("ADMIN")

                        .requestMatchers("/api/v1/jwt/{userId}").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/api/v1/csrf").hasAnyRole("USER", "ADMIN")

                        .requestMatchers("/api/v1/bitcoin/**").permitAll()
                );


        httpSecurity.formLogin(config -> config.disable());
        httpSecurity.logout(config ->
                config.logoutSuccessHandler((success, response, authentication) -> SecurityContextHolder.clearContext())
                        .logoutUrl("/api/v1/users/logout")
                        .addLogoutHandler(new LogoutService(jwtService)));

        httpSecurity.httpBasic(config -> config.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        httpSecurity.exceptionHandling(config -> config.accessDeniedHandler(new CustomAccessDeniedHandler()));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder){
        CustomUsernamePwdAuthentication authenticationProvider =
                new CustomUsernamePwdAuthentication(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }

}
