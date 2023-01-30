package servidor.banco;

import interfaces.usuario.TipoUsuario;
import servidor.banco.colecao.Colecao;
import servidor.banco.sessao.Sessao;
import servidor.banco.usuarios.Usuario;

import java.util.HashMap;
import java.util.UUID;

public class Banco {
    private Colecao<Usuario> colecaoDeUsuarios = new Colecao<>();
    private HashMap<Class<?>, Colecao<?>> colecoes = new HashMap<>();
    private HashMap<UUID, Sessao> sessoes = new HashMap<>();

    private Sessao sessaoPara(UUID id, TipoUsuario tipo) {
        var sessao = sessoes.get(id);
        if (sessao == null) {
            sessao = Sessao.fromTipoUsuario(tipo);
            sessoes.put(id, sessao);
        }
        return sessao;
    }

    public Sessao logar(String nome) throws BancoException {
        var usuario = colecaoDeUsuarios.listarUm((entry) -> entry.getValue().nome() == nome);
        if (usuario.isEmpty()) {
            throw new BancoException("Usuário %s não está cadastrado.", nome);
        }
        return sessaoPara(usuario.get().getId(), usuario.get().getObjeto().tipo());
    }

    public UUID registrar(String nome, TipoUsuario tipo) throws BancoException {
        var usuario = colecaoDeUsuarios.listarUm(
                entry -> entry.getValue().nome() == nome
        );
        if (usuario.isPresent()) {
            throw new BancoException("Usuário %s já existe, por favor usar outro nome", nome);
        }
        return colecaoDeUsuarios.inserir(new Usuario(nome, tipo));
    }

    public <T> Colecao<T> colecao(Class<T> classe) {
        var colecao = colecoes.get(classe);
        if (colecao == null) {
            colecao = new Colecao<>();
            colecoes.put(classe, colecao);
        }
        /** SAFE: Esse é o único lugar que altera o conteúdo de colecoes, portanto
         * conseguimos garantir que a Colecao para a chave Class<T> vai ser Colecao<T>
         * em vez de Colecao<Not T>
        */
        //noinspection unchecked
        return (Colecao<T>) colecao;
    }
}
