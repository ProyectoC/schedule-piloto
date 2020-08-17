package com.schedulepiloto.core.security;

import com.schedulepiloto.core.constants.AccountUserConstants;
import com.schedulepiloto.core.service.UserAccountService;
import com.schedulepiloto.core.util.SecurityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(SecurityConfig.class);

    public final static String ALL_RESOURCES = "/*";

    private final List<String> allowedHeaders = new ArrayList<String>() {{
        add("*");
    }};

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private AuthenticationEventPublisher authenticationEventPublisher;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userAccountService);
        return provider;
    }

    @Override
    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userAccountService).passwordEncoder(passwordEncoder())
                .and().authenticationEventPublisher(authenticationEventPublisher);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @EventListener
    public void auditEventHappened(AuditApplicationEvent auditApplicationEvent) {
        AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();
        LOGGER.info("### AUTH: Authentication for user: {}, with status: {} - {} ###", auditEvent.getPrincipal(),
                auditEvent.getType(), auditEvent.getTimestamp());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .cors().configurationSource(corsConfigurationSource()).and()
                .authorizeRequests()
                .antMatchers(SecurityUtil.SING_HOST_REST_DEFAULT).permitAll()
                .antMatchers(AccountUserConstants.REST_PATH_DEFAULT_V1 + AccountUserConstants.CREATE_USER_ACCOUNT_REST).permitAll()
                .antMatchers(AccountUserConstants.REST_PATH_DEFAULT_V1 + AccountUserConstants.ACTIVATE_USER_ACCOUNT_REST).permitAll()
                .antMatchers(AccountUserConstants.REST_PATH_DEFAULT_V1 + AccountUserConstants.AUTH_AUTHORIZE_USER_ACCOUNT_REST).permitAll()
                .antMatchers(SecurityUtil.VERIFICATION_EMAIL_REST_DEFAULT + ALL_RESOURCES, "/oauth2/authorize-client").permitAll()
                .antMatchers(SecurityUtil.AUTH_WHITELIST_APIS_DEFAULT()).permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/auth/**", "/oauth2/**")
                .permitAll()
                .anyRequest().authenticated();
//                .and()
//                .oauth2Login()
//                .authorizationEndpoint()
//                .baseUri("/oauth2/authorize")
//                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
//                .and()
//                .redirectionEndpoint()
//                .baseUri("/oauth2/callback/*")
//                .and()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService)
//                .and()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .failureHandler(oAuth2AuthenticationFailureHandler);

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        List<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("*");

        LOGGER.info("Allowed origins, angular app domain {}", allowedOrigins);
        List<String> headers = buildAllowedHeaders();

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(allowedOrigins);
        corsConfig.setAllowedMethods(
                Arrays.asList(HttpMethod.OPTIONS.name(), HttpMethod.GET.name(), HttpMethod.POST.name(),
                        HttpMethod.PUT.name(), HttpMethod.DELETE.name()));
        corsConfig.setAllowedHeaders(headers);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(ALL_RESOURCES, corsConfig);

        return source;
    }

    private List<String> buildAllowedHeaders() {
        List<String> headers = new ArrayList<>();
        headers.addAll(
                Arrays.asList(HttpHeaders.ACCEPT, HttpHeaders.ACCEPT_ENCODING, HttpHeaders.ACCEPT_LANGUAGE,
                        HttpHeaders.CACHE_CONTROL, HttpHeaders.CONNECTION, HttpHeaders.CONTENT_LENGTH,
                        HttpHeaders.CONTENT_TYPE, HttpHeaders.HOST, HttpHeaders.ORIGIN, HttpHeaders.PRAGMA,
                        HttpHeaders.REFERER, HttpHeaders.USER_AGENT, HttpHeaders.AUTHORIZATION));

        if (!StringUtils.isEmpty(this.allowedHeaders)) {
            headers.addAll(this.allowedHeaders);
        }
        LOGGER.info("Allowed headers, angular app domain {}", headers);
        return headers;
    }
}
