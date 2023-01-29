package cliente.prod;

import org.jetbrains.annotations.NotNull;
import servidor.usuarios.TipoUsuario;

import java.util.UUID;

public class Cliente {
    public static Cliente instance = new Cliente();
    private Servidor servidor = new Servidor();
    private UUID uuidCliente = null;
    public boolean logar(String usuario) {
        var resposta = servidor.logar(usuario);
        if (resposta.valor().isPresent()) {
            uuidCliente = resposta.valor().get();
            return true;
        } else {
            return false;
            // TODO retorna um popup com a mensagem de erro do servidor
        }
    }
    public boolean registrar(String usuario, String tipoUsuario) {

        var resposta = servidor.registrar(usuario, map(tipoUsuario));
        if (resposta.valor().isPresent()) {
            // retorna para janela de login
            return true;
        } else {
            // TODO retorna um popup com a mensagem de erro do servidor
            return false;
        }
    }
    public boolean verificaCredenciais() {
        // TODO
        return false;
    }
    private TipoUsuario map(@NotNull String tipoUsuario) {
        if (tipoUsuario.equals(TipoUsuario.Administrador.toString())) {
            return TipoUsuario.Administrador;
        }
        else return TipoUsuario.Cliente;
    }

}
