//package application.ecommerce.security.jwt;
//
//import application.ecommerce.models.usuario.Usuario;
//import application.ecommerce.models.usuario.UsuarioService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.List;
//
//@Component
//public class FiltroJWT extends OncePerRequestFilter {
//
//    @Autowired
//    private UsuarioService usuarioRepository;
//
//    @Autowired
//    private JwtTokenService jwtTokenService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header == null || !header.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String token = header.split(" ")[1].trim();
//
//        if(!jwtTokenService.isValid(token)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        Usuario usuario = usuarioRepository.findOne(Example.of(
//                Usuario.builder()
//                        .nome(jwtTokenService.getUserName(token))
//                        .build())
//        ).orElse(null);
//
//        Collection<? extends GrantedAuthority> authorities = usuario == null ? List.of() : usuario.getAuthorities();
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(usuario, null, authorities);
//
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        filterChain.doFilter(request, response);
//    }
//}
