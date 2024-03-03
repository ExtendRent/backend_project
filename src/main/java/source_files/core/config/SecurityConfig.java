package source_files.core.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import source_files.core.filters.JwtAuthFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private static final String[] DEFAULT_WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/auth/**",
            "/swagger-ui.html",
            "/extendrent.azurewebsites.net/api/v1/**"
    };


    private final JwtAuthFilter jwtAuthFilter;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((req) -> req
                        .requestMatchers(DEFAULT_WHITE_LIST_URLS).permitAll()
                        .requestMatchers("/api/v1/admins/**").permitAll()
                        .requestMatchers("/api/v1/employees/**").permitAll()
                        .requestMatchers("/api/v1/customers/**").permitAll()
                        .requestMatchers("/api/v1/brands/**").permitAll()
                        .requestMatchers("/api/v1/colors/**").permitAll()
                        .requestMatchers("/api/v1/carBodyTypes/**").permitAll()
                        .requestMatchers("/api/v1/paymentTypes/**").permitAll()
                        .requestMatchers("/api/v1/carModels/**").permitAll()
                        .requestMatchers("/api/v1/discountCodes/**").permitAll()
                        .requestMatchers("/api/v1/cars/**").permitAll()
                        .requestMatchers("/api/v1/rentals/**").permitAll()
                        .requestMatchers("/api/v1/discounts/**").permitAll()
                        .requestMatchers("/api/v1/fuels/**").permitAll()
                        .requestMatchers("/api/v1/gearshifts/**").permitAll()
                        .requestMatchers("/api/v1/vehicle-statuses/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/drivingLicenseType/**").permitAll()
                        .requestMatchers("/api/v1/paymentDetails/**").permitAll()
                        .requestMatchers("api/v1/car-segments/**").permitAll()
                        .requestMatchers("/api/v1/images/**").permitAll()
                        .requestMatchers("/api/v1/users/**").permitAll()
                        .requestMatchers("/api/v1/rentalStatuses/**").permitAll()

                        .anyRequest().authenticated()
                )

                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
