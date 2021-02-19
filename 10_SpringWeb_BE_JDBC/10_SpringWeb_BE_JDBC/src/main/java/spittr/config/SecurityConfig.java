package spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
            .and()
            .httpBasic()
            .realmName("Spittr")
            .and()
            .rememberMe()
                .tokenValiditySeconds(2419200)
                .key("spittrKey")
            .and()
            .logout()
                .logoutSuccessUrl("/")
            .and()
            .authorizeRequests()
                .antMatchers("/spitter/me").hasRole("SPITTER")
                .antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
            .anyRequest().permitAll()
            .and()
            .requiresChannel()
            .antMatchers("/spitter/form").requiresSecure();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("USER", "ADMIN")
                .and()
                .withUser("spitter").password("password").roles("SPITTER");
    }
}