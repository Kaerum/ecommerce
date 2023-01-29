package servidor;

import servidor.usuarios.TipoUsuario;

import java.util.UUID;

public interface IServidor {
    public RespostaServidor<UUID> logar(String usuario);
    public RespostaServidor<String> registrar(String usuario, TipoUsuario tipo);
}
