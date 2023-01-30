package servidor.banco.sessao;

import interfaces.usuario.TipoUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SessaoTest {

    class ValorTeste {}

    @Test
    public void sessaoTest_fromTipoUsuario_Sucesso() {
        Sessao sessao = Sessao.fromTipoUsuario(TipoUsuario.Administrador);
        Assertions.assertEquals(sessao.getTipoUsuario(), TipoUsuario.Administrador);
        Sessao sessaoCliente = Sessao.fromTipoUsuario(TipoUsuario.Cliente);
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
