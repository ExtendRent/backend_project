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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import source_files.core.filters.JwtAuthFilter;
import source_files.services.userServices.abstracts.UserService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/auth/**",
            "/api/v1/auth/**",
            "/swagger-ui.html"
    };
    private final JwtAuthFilter jwtAuthFilter;
    private final PasswordEncoder passwordEncoder;

    //    .requestMatchers("/api/v1/users/**").hasAnyAuthority(
//                        .requestMatchers(HttpMethod.POST, "/api/v1/brands/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/colors/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/carBodyTypes/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/employees/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/paymentTypes/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/admins/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/carModels/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/discountCodes/**").hasAnyAuthority(UserRole.ADMIN.toString())

    //  .authorizeHttpRequests((req) -> req
//            .requestMatchers("/api/v1/users/**").hasAnyAuthority(
//                        .requestMatchers(HttpMethod.GET, "/api/v1/brands/").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.GET, "/api/v1/brands/**").hasAnyAuthority(
//                        .anyRequest().authenticated()
//                )
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((req) -> req
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
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
