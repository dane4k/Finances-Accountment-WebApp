//package org.example.financesaccountmentwebapp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        return http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/").permitAll()
////                        .requestMatchers("/register").permitAll()
////                        .requestMatchers("/login").permitAll()
////                        .requestMatchers("/home").authenticated()
////                        .anyRequest().permitAll()
////                )
////                .formLogin(form -> form.defaultSuccessUrl("/home", true)
////                )
////                .logout(config -> config.logoutSuccessUrl("/"))
////                .build();
////    }
//
//
//}