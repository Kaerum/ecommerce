package compartilhado;

import compartilhado.usuario.TipoUsuario;
import java.util.UUID;

public interface Servidor {
    public RespostaServidor<ContextoAutenticado> logar(String usuario);
    public RespostaServidor<UUID> registrar(String usuario, TipoUsuario tipo);
}

/**
 * ServidorImpl servidor = new ServidorImpl();
 */