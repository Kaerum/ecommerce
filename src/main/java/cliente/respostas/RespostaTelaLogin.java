package cliente.respostas;

import java.util.Optional;

enum TipoRespostaTelaLogin {
    Logar,
    Registrar
}
public record RespostaTelaLogin(TipoRespostaTelaLogin tipo, Optional<String> usuario) {}
