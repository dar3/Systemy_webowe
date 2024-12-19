package org.example.spring_2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final BCryptPasswordEncoder passwordEncoder;

    public SecurityConfiguration(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userManager = new InMemoryUserDetailsManager();

        userManager.createUser(User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .authorities("ROLE_USER")
                .build());

        userManager.createUser(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities("ROLE_ADMIN")
                .build());

        return userManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/").permitAll()
                        .requestMatchers("/categories", "/categories/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/products", "/cart").authenticated()
                        //.anyRequest().authenticated()

                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//                .formLogin(login -> login
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/products", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/register")
//                        .permitAll()
//                );

        return http.build();
    }

}
