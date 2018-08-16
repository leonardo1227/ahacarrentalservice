package edu.mum.cs.cs425.ahacarrentalservice.config;

import edu.mum.cs.cs425.ahacarrentalservice.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/system/car_owner/**",
                "/system/car_profile_analysis").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/system/car_offer/**",
                "/system/car_profile/**").hasRole("CAR_OWNER");

        http.authorizeRequests().antMatchers("/javax.faces.resources/**",
                "/",
                "/images/**",
                "/style/**",
                "/template/**",
                "/login.xhtml",
                "/system/home.xhtml",
                "/system/new_car_owner.xhtml",
                "/system/rent/**",
                "/system/static/**").permitAll().anyRequest().authenticated();

        http.formLogin().loginPage("/login.xhtml").failureUrl("/login.xhtml?error=true");
        http.logout().logoutSuccessUrl("/system/home.xhtml");
        http.exceptionHandling().accessDeniedPage("/access_denied.xhtml");


        http.csrf().disable();
        super.configure(http);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
