package com.seguridad.ProSec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadBasicaConfig {

    @Bean
    public SecurityFilterChain FiltroSeguridad(HttpSecurity http) throws Exception {
        http // Depreciado! Ya no se usa  .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/productos/**").permitAll()
                .anyRequest().authenticated()
            );
        //Depreciado! ya no se usa
//            .headers(headers -> headers.frameOptions().disable())
//            .httpBasic();

        return http.build();
    }

}
