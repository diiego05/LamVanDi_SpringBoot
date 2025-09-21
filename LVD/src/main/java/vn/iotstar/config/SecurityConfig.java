package vn.iotstar.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("123")
            .roles("ADMIN")
            .build();

        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("123")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // cấu hình quyền cho URL
            .authorizeHttpRequests(auth -> auth
                // Cho phép ai cũng xem trang login, resource tĩnh
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                // Trang CRUD chỉ cho admin
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Các URL khác thì chỉ cần login
                .anyRequest().authenticated()
            )
            // bật form login mặc định
            .formLogin(Customizer.withDefaults())
            // cho phép logout
            .logout(logout -> logout.permitAll())
            // tạm thời tắt CSRF cho dễ test form POST
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}