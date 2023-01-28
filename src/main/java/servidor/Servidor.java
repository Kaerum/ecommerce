package servidor;

import servidor.dados.GerenciadorDeDados;
import servidor.usuarios.TipoUsuario;
import servidor.usuarios.Usuario;

import java.util.Optional;
import java.util.UUID;

public class Servidor {
    private GerenciadorDeDados<Usuario> gerenciadorDeUsuarios = new GerenciadorDeDados<>();

    public RespostaServidor<UUID> logar(String usuario) {
        var usuarios = gerenciadorDeUsuarios.listar((entry) -> entry.getValue().getNome() == usuario, null, Optional.of(1));
        var id = Optional.ofNullable(usuarios.get(0)).map(u -> u.getId());
        return RespostaServidor.sucesso(id);
    }

    public RespostaServidor<String> registrar(String usuario, TipoUsuario tipo) {
        var usuarios = gerenciadorDeUsuarios.listar(entry -> entry.getValue().getNome() == usuario, null, Optional.of(1));
        if (usuarios.size() > 0) {
            return RespostaServidor.erro("Usuário %s já existe, por favor usar outro nome", usuario);
        }
        switch (tipo) {
            case Cliente -> {}
            case Administrador -> {}
        }
        return null;
    }
}
