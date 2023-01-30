package servidor.banco.sessao;

import compartilhado.usuario.TipoUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import servidor.banco.usuarios.Usuario;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SessaoTest {

    class ValorTeste {}

    @Test
    public void sessaoTest_fromTipoUsuario_Sucesso() {
        Usuario administrador = new Usuario("Adm", TipoUsuario.Administrador);
        Sessao sessao = Sessao.fromUsuario(administrador);
        Assertions.assertEquals(sessao.getTipoUsuario(), TipoUsuario.Administrador);
        Usuario cliente = new Usuario("Cln", TipoUsuario.Cliente);
        Sessao sessaoCliente = Sessao.fromUsuario(cliente);
        Assertions.assertEquals(sessaoCliente.getTipoUsuario(), TipoUsuario.Cliente);
    }

    @Test
    public void sessaoTest_getTemPermissao_Sucesso() {
        HashMap<Class<?>, Set<Permissao>> mapa = new HashMap<>();
        HashSet<Permissao> permissoesProduto = new HashSet<>();
        permissoesProduto.add(Permissao.Ler);
        permissoesProduto.add(Permissao.Remover);
        permissoesProduto.add(Permissao.Modificar);
        mapa.put(ValorTeste.class, permissoesProduto);
        Sessao sessao = Sessao.builder()
                .colecaoPermissaoMapa(mapa)
                .tipoUsuario(TipoUsuario.Cliente)
                .build();
        Assertions.assertTrue(sessao.getTemPermissao(ValorTeste.class, Permissao.Modificar));
        Assertions.assertTrue(sessao.getTemPermissao(ValorTeste.class, Permissao.Ler));
        Assertions.assertTrue(sessao.getTemPermissao(ValorTeste.class, Permissao.Remover));
    }

    @Test
    public void sessaoTest_getPermissaoPara_Sucesso() {
        HashMap<Class<?>, Set<Permissao>> mapa = new HashMap<>();
        HashSet<Permissao> permissoesProduto = new HashSet<>();
        permissoesProduto.add(Permissao.Ler);
        permissoesProduto.add(Permissao.Remover);
        permissoesProduto.add(Permissao.Modificar);
        mapa.put(ValorTeste.class, permissoesProduto);
        Sessao sessao = Sessao.builder()
                .colecaoPermissaoMapa(mapa)
                .tipoUsuario(TipoUsuario.Cliente)
                .build();
        Assertions.assertEquals(sessao.getPermissoesPara(ValorTeste.class), permissoesProduto);
    }
}
