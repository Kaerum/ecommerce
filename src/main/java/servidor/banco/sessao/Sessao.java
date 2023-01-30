package servidor.banco.sessao;

import compartilhado.usuario.TipoUsuario;
import lombok.Builder;
import lombok.Getter;
import compartilhado.produto.Produto;
import servidor.banco.usuarios.Usuario;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Builder
public class Sessao {
    @Builder.Default private HashMap<Class<?>, Set<Permissao>> colecaoPermissaoMapa = new HashMap<>();
    @Builder.Default @Getter private TipoUsuario tipoUsuario = TipoUsuario.Cliente;
    @Getter private String nome = "";

    public static Sessao fromUsuario(Usuario usuario) {
        return Sessao.builder()
                .nome(usuario.nome())
                .tipoUsuario(usuario.tipo())
                .colecaoPermissaoMapa(mapaPermissaoPara(usuario.tipo()))
                .build();
    }

    private static HashMap<Class<?>, Set<Permissao>> mapaPermissaoPara(TipoUsuario tipoUsuario) {
        return switch (tipoUsuario) {
            case Administrador -> {
                HashMap<Class<?>, Set<Permissao>> mapa = new HashMap<>();
                HashSet<Permissao> permissoesProduto = new HashSet<>();
                permissoesProduto.add(Permissao.Ler);
                permissoesProduto.add(Permissao.Remover);
                permissoesProduto.add(Permissao.Modificar);
                mapa.put(Produto.class, permissoesProduto);
                yield mapa;
            }
            case Cliente -> {
                HashMap<Class<?>, Set<Permissao>> mapa = new HashMap<>();
                HashSet<Permissao> permissoesProduto = new HashSet<>();
                permissoesProduto.add(Permissao.Ler);
                mapa.put(Produto.class, permissoesProduto);
                yield mapa;
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
