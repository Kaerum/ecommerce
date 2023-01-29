package servidor.banco;

import interfaces.RespostaServidor;
import interfaces.usuario.TipoUsuario;
import servidor.banco.colecao.Colecao;
import servidor.banco.sessao.Sessao;
import servidor.banco.usuarios.Usuario;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class Banco {
    private Colecao<Usuario> colecaoDeUsuarios = new Colecao<>();
    private HashMap<Class<?>, Colecao<?>> colecoes = new HashMap<>();
    private HashMap<UUID, Sessao> sessoes = new HashMap<>();

    private Sessao sessaoPara(UUID id) {
        var sessao = sessoes.get(id);
        if (sessao != null) {
            return sessao;
        }
        return null;
    }

    public Sessao logar(String usuario) throws BancoException {
        var usuarios = colecaoDeUsuarios.listar((entry) -> entry.getValue().nome() == usuario, null, Optional.of(1));
        var id = Optional.ofNullable(usuarios.get(0)).map(u -> u.getId());
        if (id.isEmpty()) {
            throw new BancoException("Usuário %s não está cadastrado.", usuario);
        }
        return sessaoPara(id.get());
    }

    public UUID registrar(String nome, TipoUsuario tipo) throws BancoException {
        var usuarios = colecaoDeUsuarios.listar(entry -> entry.getValue().nome() == nome, null, Optional.of(1));
        if (usuarios.size() > 0) {
            throw new BancoException("Usuário %s já existe, por favor usar outro nome", nome);
        }
        return colecaoDeUsuarios.inserir(new Usuario(nome, tipo));
    }
}
