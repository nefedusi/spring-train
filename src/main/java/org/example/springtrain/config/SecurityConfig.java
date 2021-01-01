package org.example.springtrain.config;

import lombok.RequiredArgsConstructor;
import org.example.springtrain.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder
            /*,
                                CustomUserDetailsService userDetailsService*/) throws Exception {
        //Если нужна in-memory authentication.
        //configureInMemoryAuthentication(authenticationManagerBuilder);

        //Конфигурирование AuthenticationManager - аутентификация с помощью данных в БД.
        //Или можно просто навесить @Service над CustomUserDetailsService, и будет работать.
        //Ещё можно set it as a property in a custom authenticationProvider bean, and then inject that using
        // the AuthenticationManagerBuilder# authenticationProvider function.
        //authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    private void configureInMemoryAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("user")
                //Prior to Spring Security 5.0 the default PasswordEncoder was NoOpPasswordEncoder which required plain text passwords.
                // In Spring Security 5, the default is DelegatingPasswordEncoder, which required Password Storage Format.
                .password("{noop}userpass")
                .roles("USER"); //or .authorities("ROLE_USER")
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}adminpass")
                .roles("USER", "ADMIN");
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("cleanadmin")
                .password("{noop}cleanadminpass")
                .authorities("ROLE_ADMIN"); //or .roles("ADMIN")
        //https://www.journaldev.com/8748/spring-security-role-based-access-authorization-example
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // .antMatchers("/").permitAll() doesn't allow thing/all
                // .antMatchers("/**").permitAll() allow thing/all
                // .antMatchers("/*").permitAll() doesn't allow thing/all
                .antMatchers("/unsafe/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/safe/user-and-admin")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/safe/only-user")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/safe/only-admin")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/h2-console/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
