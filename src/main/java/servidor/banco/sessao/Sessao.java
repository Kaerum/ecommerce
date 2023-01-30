package servidor.banco.sessao;

import interfaces.usuario.TipoUsuario;
import lombok.Builder;
import lombok.Getter;
import servidor.produto.Produto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Builder
public class Sessao {
    @Builder.Default private HashMap<Class<?>, Set<Permissao>> colecaoPermissaoMapa = new HashMap<>();
    @Builder.Default @Getter private TipoUsuario tipoUsuario = TipoUsuario.Cliente;

    public static Sessao fromTipoUsuario(TipoUsuario tipoUsuario) {
        return switch (tipoUsuario) {
            case Administrador -> {
                HashMap<Class<?>, Set<Permissao>> mapa = new HashMap<>();
                HashSet<Permissao> permissoesProduto = new HashSet<>();
                permissoesProduto.add(Permissao.Ler);
                permissoesProduto.add(Permissao.Remover);
                permissoesProduto.add(Permissao.Modificar);
                mapa.put(Produto.class, permissoesProduto);
                yield Sessao.builder()
                    .tipoUsuario(TipoUsuario.Administrador)
                    .colecaoPermissaoMapa(mapa)
                    .build();
            }
            case Cliente -> {
                HashMap<Class<?>, Set<Permissao>> mapa = new HashMap<>();
                HashSet<Permissao> permissoesProduto = new HashSet<>();
                permissoesProduto.add(Permissao.Ler);
                mapa.put(Produto.class, permissoesProduto);
                yield Sessao.builder()
                        .tipoUsuario(TipoUsuario.Cliente)
                        .colecaoPermissaoMapa(mapa)
                        .build();
            }
        };
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
