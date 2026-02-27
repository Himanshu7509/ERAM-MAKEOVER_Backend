package com.Elite.Erum_Makeover.SecurityConfig;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sess ->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // ✅ PUBLIC APIs
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/courses/**").permitAll()
                                .requestMatchers( "/contact").permitAll()
                                .requestMatchers("/demo/**").authenticated()
                                .requestMatchers("/courses/admin/**").authenticated()
                                .requestMatchers("/api/profiles/**").authenticated()
//                         // ENROLL NEED LOGIN
                   .requestMatchers("/enrollments/**").authenticated()
                                // ✅ PUBLIC CONTACT SUBMIT ONLY
                                .requestMatchers(HttpMethod.POST, "/contact").permitAll()

                                // ✅ ADMIN ONLY CONTACT VIEW + DELETE
                                .requestMatchers(HttpMethod.GET, "/contact/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/contact/**").hasRole("ADMIN")
                                // DEMO
                               // .requestMatchers(HttpMethod.POST, "/demo/register").authenticated()
                                .requestMatchers(HttpMethod.GET, "/demo/all").hasRole("ADMIN")
                                .requestMatchers("/api/images/upload").authenticated()
                                // everything else protected
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    // ✅ CORS CONFIGURATION
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "http://localhost:3000",  // React local
                "http://localhost:5173",
                "http://localhost:5174", // Vite local
                "https://makeover-academy-eram.vercel.app/" // production
        ));

        configuration.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}