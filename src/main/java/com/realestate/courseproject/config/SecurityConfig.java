package com.realestate.courseproject.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration //without annotation Java will ignore this class
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Override
    protected void configure(HttpSecurity http) throws Exception{

        //Permit all requests inside web application
        //  anyRequest() any webpage or any RestAPI to be constructed by Spring
        //  permitAll() - self-explanatory, if changed to denyAll() will deny access to specific or all paths regardless of authentification - will throw 403 access denied
        //  formLogin() for supporting login as in html form
        //  httpBasic() for supporting login as http header at time of request to server
        //With this condition there will be no security measures
        http.authorizeRequests().anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic();
    }*/

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
                .mvcMatchers("/displayMessages").hasRole("ADMIN")
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user").password("1234").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
        //passwordEncoder needed because you should never store passwords as they are in plain text. Need to hash or encode them.
        //NoOpPasswordEncoder is crossed because it is not recommended
    }
}
