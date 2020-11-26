package org.example.springtrain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("user")
                //Prior to Spring Security 5.0 the default PasswordEncoder was NoOpPasswordEncoder which required plain text passwords.
                // In Spring Security 5, the default is DelegatingPasswordEncoder, which required Password Storage Format.
                .password("{noop}userpass")
                .roles("USER"); //or .authorities("ROLE_USER")
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}adminpass")
                .authorities("ROLE_ADMIN"); //or .roles("ADMIN")
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("universal")
                .password("{noop}universalpass")
                .roles("USER", "ADMIN");
        //https://www.journaldev.com/8748/spring-security-role-based-access-authorization-example
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/unsafe/*").permitAll()
                .antMatchers("/safe/user-and-admin")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/safe/only-user")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/safe/only-admin")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
