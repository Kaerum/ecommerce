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
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "autenticacao")
//@Slf4j
//public class AutenticacaoController {
//
//    @Autowired
//    private AutenticacaoService autenticacaoService;
//
//    @PostMapping(value = "logar")
//    public ResponseEntity<LoginResposta> logar(@RequestBody @Validated LoginCorpo corpo) {
//        try {
//            return ResponseEntity.ok(autenticacaoService.logar(corpo));
//        } catch (BadCredentialsException badCredentialsException) {
//            return ResponseEntity.badRequest().build();
//        } catch (JWTCreationException jwtCreationException) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @PostMapping(value = "registrar")
//    public ResponseEntity<Boolean> registrar(@RequestBody @Validated RegistroCorpo corpo) {
//        try {
//            autenticacaoService.registrar(corpo);
//        } catch (UserAlreadyExistsException exception) {
//            return ResponseEntity.badRequest().build();
//        } catch (Exception exception) {
//            return ResponseEntity.internalServerError().build();
//        }
//        return ResponseEntity.ok(true);
//    }
//}
