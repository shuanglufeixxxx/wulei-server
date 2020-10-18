package wulei.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import wulei.domain.Account;
import wulei.repository.AccountRepository;
import wulei.services.TokenService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    public void initialize(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService( userDetailsService() );
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Account account = accountRepository.findByUsername(username);

            if(account == null) {
                throw new UsernameNotFoundException("username not found");
            }
            
            if (account.getUsername().compareTo("Admin") == 0) {
                return new AccountDetails(account.getId(), account.getUsername(), "{noop}" + account.getPassword(),
                        AuthorityUtils.createAuthorityList("ROLE_ACTUATOR", "ROLE_USER"));
            }

            return new AccountDetails(account.getId(), account.getUsername(), "{noop}" + account.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_USER"));
        };
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
 
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        
        httpSecurity
            .cors()
                .configurationSource(corsConfigurationSource)
                .and()
            .csrf()
                .csrfTokenRepository( CookieCsrfTokenRepository.withHttpOnlyFalse() )
                .and()
            .formLogin()
                .loginPage("/account/toSignInPage")
                .loginProcessingUrl("/account/signIn")
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
                .antMatchers("/actuator/**").hasAnyAuthority("ROLE_ACTUATOR")
                .anyRequest().permitAll();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        webSecurity.httpFirewall( firewall );
    }

    @Bean
    public InMemoryHttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }
}