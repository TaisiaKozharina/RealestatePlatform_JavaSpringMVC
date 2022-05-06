package com.realestate.courseproject.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //without annotation Java will ignore this class
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception{

        //http.csrf().disable() will disable 403 error - DO NOT USE
        //http.csrf().ignoringAntMatchers("/saveMsg") will disable csrf only on that action
        //.ignoringAntMatchers("/h2-console/**") for H2 console
        //.and().authorizeRequests().antMatchers("/h2-console/**").permitAll()  for H2 console
        //Ignoring csrf for all public pages and saving msg, because no data to steal

        http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/public/**").and()
                .authorizeRequests()
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/displayProfile").authenticated()
                .mvcMatchers("/updateProfile").authenticated()
                .mvcMatchers("/displayMessages").hasRole("ADMIN")
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/home").permitAll()
                .mvcMatchers("/about").authenticated()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/saveMsg").permitAll()
                .mvcMatchers("/gallery/**").permitAll()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/public/**").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();

        //http.headers().frameOptions().disable(); //H2 console uses frameOptions, which is blocked by Spring. Disabling for testing. Do not ise in production (you will not use console in prod)
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

/*   IN-MEMORY AUTHENTICATION v
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user").password("1234").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
        //passwordEncoder needed because you should never store passwords as they are in plain text. Need to hash or encode them.
        //NoOpPasswordEncoder is crossed because it is not recommended
    }*/
}
