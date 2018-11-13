package ru.rtk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(value = "ru.rtk.config")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/setaccount").permitAll()
                .antMatchers("/users/add/adm").permitAll()
                .antMatchers("/users/add/usr").access("hasRole('ADMIN')")

                .antMatchers("/cdr").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/cdr/**").access("hasRole('USER') or hasRole('ADMIN')")
//                .antMatchers("/statistika/**").access("hasRole('USER') or hasRole('ADMIN')")
                .and()
                .csrf().disable()
                .formLogin()
                .defaultSuccessUrl("/", false);
//                .failureForwardUrl("/failurepg")
//                .successForwardUrl("/pet")
                /*.loginPage("/customlgnpg")*//*.defaultSuccessUrl("/", false)*/
                /*.failureHandler(authFailureHandler)
                .successHandler(authSuccessHandler);*/
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
