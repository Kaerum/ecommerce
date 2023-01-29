package interfaces;

import interfaces.usuario.TipoUsuario;

import java.util.UUID;

public interface Servidor {
    public RespostaServidor<ContextoAutenticado> logar(String usuario);
    public RespostaServidor<String> registrar(String usuario, TipoUsuario tipo);
}
