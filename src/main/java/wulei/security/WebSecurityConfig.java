package wulei.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import wulei.domain.Account;
import wulei.repository.AccountRepository;
import wulei.services.TokenService;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    SignInWithFormSubmissionUrlProvider urlProvider;

    @Autowired
    public void initialize(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService( userDetailsService() );
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Account account = accountRepository.findByUsername(username);

            if(account == null) {
                throw new UsernameNotFoundException("");
            }

            return new AccountDetails(account.getId(), account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER") );
        };
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf()
                .csrfTokenRepository( CookieCsrfTokenRepository.withHttpOnlyFalse() )
                .and()
            .formLogin()
                .loginPage(urlProvider.getUrl())
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/account/signInSucceed")
                .failureForwardUrl("/account/signInFailed")
                .permitAll()
                .and()
            .rememberMe()
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("remember-me")
                .tokenRepository(tokenService)
                .and()
            .logout()
                .logoutUrl("/account/signOut")
                .logoutSuccessUrl("/account/signOutSucceed")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me")
                .and()
            .authorizeRequests()
                .anyRequest().permitAll();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.httpFirewall( allowSemicolonHttpFireWall() );
    }

    @Bean
    public HttpFirewall allowSemicolonHttpFireWall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        return firewall;
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:4200/");
//            }
//        };
//    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("http://localhost:4200/");
//
//        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return corsConfigurationSource;
//    }
}