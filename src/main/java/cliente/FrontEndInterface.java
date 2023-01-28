package cliente;

import cliente.respostas.RespostaTelaLogin;
import cliente.respostas.RespostaTelaRegistro;

import java.util.Optional;

public interface FrontEndInterface {
    RespostaTelaLogin mostrarTelaLogin();
    Optional<RespostaTelaRegistro> mostrarTelaRegistro();

}
