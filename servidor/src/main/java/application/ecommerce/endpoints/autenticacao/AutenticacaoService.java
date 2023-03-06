//package application.ecommerce.endpoints.autenticacao;
//
//import application.ecommerce.endpoints.autenticacao.data.LoginCorpo;
//import application.ecommerce.endpoints.autenticacao.data.LoginResposta;
//import application.ecommerce.endpoints.autenticacao.data.RegistroCorpo;
//import application.ecommerce.endpoints.autenticacao.exceptions.UserAlreadyExistsException;
//import application.ecommerce.models.usuario.Usuario;
//import application.ecommerce.models.usuario.UsuarioRepository;
//import application.ecommerce.security.GrantedAuthorityImpl;
//import application.ecommerce.security.Roles;
//import application.ecommerce.security.jwt.JwtTokenService;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class AutenticacaoService {
//    @Autowired
//    private JwtTokenService jwtTokenService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    public LoginResposta logar(LoginCorpo corpo) throws BadCredentialsException, JWTCreationException {
//        var token = new UsernamePasswordAuthenticationToken(
//                corpo.getNome(), corpo.getSenha()
//        );
//        Authentication authentication = authenticationManager.authenticate(token);
//        Usuario usuario = (Usuario) authentication.getPrincipal();
//        return LoginResposta.builder()
//                .autorizacoes(usuario.getAuthorities())
//                .nome(usuario.getNome())
//                .token(jwtTokenService.generateAccessToken(usuario))
//                .build();
//    }
//
//    public void registrar(RegistroCorpo corpo) throws UserAlreadyExistsException {
//        List<GrantedAuthorityImpl> authorities = new ArrayList<>();
//        authorities.add(new GrantedAuthorityImpl(Roles.USER));
//        if (corpo.getAutoridadeUsuario() == RegistroCorpo.AutoridadeUsuario.ADMIN) {
//            authorities.add(new GrantedAuthorityImpl(Roles.ADMIN));
//        }
//        if (corpo.isCnpj()) {
//            authorities.add(new GrantedAuthorityImpl(Roles.CNPJ));
//        }
//        try {
//            usuarioRepository.save(Usuario.builder()
//                    .nome(corpo.getNome())
//                    .senha(passwordEncoder.encode(corpo.getSenha()))
//                    .authorities(authorities)
//                    .build());
//        } catch (Exception exception) {
//            if (exception instanceof DataIntegrityViolationException) {
//                throw new UserAlreadyExistsException();
//            } else {
//                throw exception;
//            }
//        }
//    }
//}
