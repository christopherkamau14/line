package com.high.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SystemUserDetails systemUserDetails;

    @Autowired
    ReloadUser reloadUser;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(systemUserDetails);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return daoAuthenticationProvider;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf()
                .disable()
                .securityContext()
                .securityContextRepository(reloadUser)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).sessionFixation().newSession().
                maximumSessions(1).and().and()
                .headers()
                .disable()
                .authorizeRequests()
                .antMatchers("/libs/**", "/js/**","/images/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/protected/home",true)
                // .successForwardUrl("/protected/home")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?error=true")
                .invalidateHttpSession(true);



    }
}