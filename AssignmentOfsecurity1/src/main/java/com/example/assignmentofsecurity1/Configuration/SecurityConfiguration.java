package com.example.assignmentofsecurity1.Configuration;

import com.example.assignmentofsecurity1.Service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
          DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
          daoAuthenticationProvider.setUserDetailsService(userDetailsService);
          daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
          return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception{
        http.csrf().disable()
                .sessionManagement()

                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/blog/get-all").permitAll()
                .requestMatchers("/api/v1/blog/get","/api/v1/blog/get-by-title/{title}","/api/v1/blog/get-by-id/{blog_id}","/api/v1/blog/add","/api/v1/blog/update/{blog_id}","/api/v1/blog/delete/{blog_id}").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}

