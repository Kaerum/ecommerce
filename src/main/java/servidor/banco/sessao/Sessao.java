package servidor.banco.sessao;

import java.util.HashMap;
import java.util.Set;

public class Sessao {
    private HashMap<Class<?>, Set<Permissao>> colecaoPermissaoMapa = new HashMap<>();

    public boolean temPermissao(Class<?> colecao, Permissao permissao) {
        var permissoes = colecaoPermissaoMapa.get(colecao);
        if (permissoes == null) {
            return false;
        }
        return permissoes.contains(permissao);
    }
}
