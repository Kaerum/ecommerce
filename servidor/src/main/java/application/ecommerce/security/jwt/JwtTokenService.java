package application.ecommerce.security.jwt;

import application.ecommerce.models.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtTokenService {

    // Os segredos ficam por aqui mesmo, já que é simplesmente um projeto
    // de estudo.

    private final String issuer = "ecommerce";
    private final String secret = "secret";
    private final Algorithm algorithm = Algorithm.HMAC256(secret);

    JwtTokenService() {}
    public boolean isValid(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public String getUserName(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            var decoded = verifier.verify(token);
            return decoded.getClaim("nome").asString();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String generateAccessToken(Usuario usuario) throws JWTCreationException {
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("nome", usuario.getNome())
                .sign(algorithm);
    }
}
