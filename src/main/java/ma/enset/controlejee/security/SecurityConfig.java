package ma.enset.controlejee.security;

import jakarta.servlet.FilterChain;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("Monaim").password(passwordEncoder.encode("monaim123")).roles("USER", "ADMIN").build(),
                User.withUsername("Mouad").password(passwordEncoder.encode("mouad123")).roles("USER").build(),
                User.withUsername("Amine").password(passwordEncoder.encode("amine123")).roles("USER").build()
        );
    }
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(fl -> fl.loginPage("/login").defaultSuccessUrl("/").permitAll());
        httpSecurity.authorizeHttpRequests(ar -> ar.requestMatchers("/user/**").hasRole("USER"));
        httpSecurity.authorizeHttpRequests(ar -> ar.requestMatchers("/admin/**").hasRole("ADMIN"));
        httpSecurity.authorizeHttpRequests(ar -> ar.requestMatchers("/webjars/**").permitAll());
        httpSecurity.exceptionHandling(ex -> ex.accessDeniedPage("/notAuthorized"));
        httpSecurity.authorizeHttpRequests(ar -> ar.anyRequest().authenticated());

        return httpSecurity.build();
    }
}
