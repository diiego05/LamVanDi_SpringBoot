package vn.iotstar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()         // Cho phép API public
                .requestMatchers("/uploads/**").permitAll()     // Cho phép load ảnh upload
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/admin/**").permitAll()       // Tạm cho phép admin
                .anyRequest().permitAll()                       // Cho phép hết
            )
            .formLogin(form -> form.disable())                  // Disable login form
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}