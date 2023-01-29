package servidor.banco.sessao;

import interfaces.usuario.TipoUsuario;
import lombok.Getter;

import java.util.HashMap;
import java.util.Set;

public class Sessao {
    private HashMap<Class<?>, Set<Permissao>> colecaoPermissaoMapa = new HashMap<>();
    @Getter
    private TipoUsuario tipoUsuario;

    public Sessao(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean getTemPermissao(Class<?> colecao, Permissao permissao) {
        var permissoes = colecaoPermissaoMapa.get(colecao);
        if (permissoes == null) {
            return false;
        }
        return permissoes.contains(permissao);
    }

    public Set<Permissao> getPermissoesPara(Class<?> colecao) {
        var permissoes = colecaoPermissaoMapa.get(colecao);
        if (permissoes == null) {
            return Set.of();
        }
        return permissoes;
    }
}
