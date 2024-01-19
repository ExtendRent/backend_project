package source_files.core.configurations;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import source_files.data.types.userTypes.UserRole;
import source_files.services.userServices.abstracts.UserService;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    //    .requestMatchers("/api/v1/users/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/v1/brands/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/colors/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/carBodyTypes/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/employees/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/paymentTypes/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/admins/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/carModels/**").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.POST, "/api/v1/discountCodes/**").hasAnyAuthority(UserRole.ADMIN.toString())

//  .authorizeHttpRequests((req) -> req
//            .requestMatchers("/api/v1/users/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/brands/").hasAnyAuthority(UserRole.ADMIN.toString())
//            .requestMatchers(HttpMethod.GET, "/api/v1/brands/**").permitAll()
//                        .anyRequest().authenticated()
//                )

    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/auth/**",
            "/api/users/login"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((req) -> req
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/brands/**").permitAll()
                        .requestMatchers( "/api/v1/colors/**").hasAnyAuthority(UserRole.CUSTOMER.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/carBodyTypes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/employees/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/paymentTypes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/admins/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/carModels/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/discountCodes/**").permitAll()
                        .requestMatchers( "/api/v1/customers/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/customers/**").permitAll()
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
