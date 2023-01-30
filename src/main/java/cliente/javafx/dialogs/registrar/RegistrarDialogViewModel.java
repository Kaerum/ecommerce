package cliente.javafx.dialogs.registrar;

import cliente.servicos.AuthenticationService;
import compartilhado.ContextoAutenticado;
import compartilhado.RespostaServidor;
import compartilhado.usuario.TipoUsuario;

import java.util.UUID;

public class RegistrarDialogViewModel {
    public RespostaServidor<UUID> registrar(String usuario, TipoUsuario tipoUsuario) {
        return AuthenticationService.instance.registar(usuario, tipoUsuario);
    }
}
