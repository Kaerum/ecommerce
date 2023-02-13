package application.ecommerce.endpoints.autenticacao.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class LoginResposta {
    private String nome;
    private Collection<? extends GrantedAuthority> autorizacoes;
}