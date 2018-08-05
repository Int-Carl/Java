package com.example.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/**").permitAll()

//                .formLogin().loginPage("/login")//.failureUrl("/login?error=true").permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/readingList").access("hasRole('READER')")
//                //.antMatchers("/**").permitAll()


        ;
    }


    @Override
    protected void configure(
            AuthenticationManagerBuilder auth) throws Exception {
        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("READER").and()
//                .withUser("admin").password("password").roles("READER","ADMIN");

                .userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username)
                            throws UsernameNotFoundException {
                        UserDetails userDetails = readerRepository.findById(username).get();
                        if (userDetails != null) {
                            return userDetails;
                        }
                        throw new UsernameNotFoundException("User '" + username + "' not found.");
                    }
                });
    }

}
