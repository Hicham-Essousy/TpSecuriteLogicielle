package com.example.tpSecurity;

import com.example.tpSecurity.beans.AppUserPermission;
import com.example.tpSecurity.beans.AppUserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
/*
    @Value("${spring.security.user.name}")
    private String name;
    @Value("${spring.security.user.password}")
    private String pass;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication()
                .withUser("hicham").password(passwordEncoder().encode("ASEFT")).roles("ADMIN")
                .and().withUser(name).password(passwordEncoder().encode(pass)).roles("USER").and()
                .withUser("samira").password(passwordEncoder().encode("lghool")).roles("USER");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/api/v1/clients").hasRole(AppUserRole.USER.name())
                    .antMatchers("/api/api/v1/clients").hasRole(AppUserRole.ADMIN.name())
                    .antMatchers("/api/api/v1/add").hasRole(AppUserRole.ADMIN.name())
                    .antMatchers("/api/api/v1/delet/**").hasRole(AppUserRole.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails hicham = User.builder()
                .username("hicham")
                .password(passwordEncoder().encode("password"))
                .roles(AppUserRole.USER.name()) //user role
                .build();

        UserDetails maryam = User.builder()
                .username("maryam")
                .password(passwordEncoder().encode("password"))
                .roles(AppUserRole.ADMIN.name()) //user role
                .build();

        return new InMemoryUserDetailsManager(
                hicham,
                maryam
        );

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
             //.and()