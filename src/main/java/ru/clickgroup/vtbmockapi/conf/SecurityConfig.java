package ru.clickgroup.vtbmockapi.conf;


import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.clickgroup.vtbmockapi.services.jwt.JwtFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;;import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    //private final KrbUserDetailServiceImpl krbUserDetailService;

    @Setter
    @Value("#{'${auth.whitelist}'.split(' ')}")
    private String[] AUTH_WHITELIST;

//    @Setter
//    @Value("${app.service-principal}")
//    private String servicePrincipal;
//    @Setter
//    @Value("${app.keytab-location}")
//    private String keytabLocation;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    /*    URI uri = new URI(camundaURL);

        InetAddress[] addresses = InetAddress.getAllByName(uri.getHost());
        for (int i = 0; i < addresses.length; i++) {
            log.info(addresses[i].getHostName() + ": " + addresses[i].getHostAddress());
        }
     */
        http.
                csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {
                })
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITELIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);
//        http.cors().disable();
        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//
////        configuration.setAllowedOrigins(ImmutableList.of("https://www.yourdomain.com")); // www - obligatory
////        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedOriginPatterns(List.of("*","http://*","https://*","http://localhost:3000", "http://localhost:5173"));  //set access from all domains
//        configuration.setAllowedMethods(List.of("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE"
//        ));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",  "X-Requested-With", "requestId", "Correlation-Id"));
//        configuration.setExposedHeaders(List.of("Authorization"));
//
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
