package cliente.javafx.dialogs.login;

import cliente.servicos.AuthenticationService;
import compartilhado.ContextoAutenticado;
import compartilhado.RespostaServidor;
import compartilhado.TipoRespostaServidor;

import java.util.Optional;

public class LoginDialogViewModel {
    public RespostaServidor<ContextoAutenticado> logar(String usuario) {
        return AuthenticationService.instance.logar(usuario);
    }
}
