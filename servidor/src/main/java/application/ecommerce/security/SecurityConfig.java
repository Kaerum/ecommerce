//package application.ecommerce.security;
//
//import application.ecommerce.models.usuario.UsuarioService;
//import application.ecommerce.security.jwt.FiltroJWT;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.core.GrantedAuthorityDefaults;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
//public class SecurityConfig {
//
//    @Autowired
//    private UsuarioService usuarioService;
//
//    @Autowired
//    private FiltroJWT filtroJWT;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                    .and()
//                .csrf()
//                    .disable()
//                .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and()
//                .exceptionHandling()
//                    .authenticationEntryPoint(((request, response, authException) -> {
//                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//                    }))
//                    .and()
//                .authorizeHttpRequests()
//                    .requestMatchers(HttpMethod.GET, "/produtos/**").permitAll()
//                    .requestMatchers(HttpMethod.POST, "/produtos/buscar").permitAll()
//                    .requestMatchers(HttpMethod.POST, "/autenticacao/registrar").permitAll()
//                    .requestMatchers(HttpMethod.POST, "/autenticacao/logar").permitAll()
//                    .anyRequest().authenticated().and()
//                .addFilterBefore(filtroJWT, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        builder.userDetailsService(username -> usuarioService.getByName(username)
//                    .orElseThrow(() -> new UsernameNotFoundException(
//                            String.format("Usuário: %s não encontrado", username)
//                    ))
//        );
//        return builder.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:3000");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//    @Bean
//    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
//        return new GrantedAuthorityDefaults("");
//    }
//}
