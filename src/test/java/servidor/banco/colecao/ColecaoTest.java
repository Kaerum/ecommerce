package servidor.banco.colecao;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import servidor.banco.Identificado;

import java.util.*;
import java.util.stream.Collectors;

import static utils.TestUtils.listEquals;

public class ColecaoTest {

    static class ValorTeste {
        @Getter @Setter
        private String valor;
        @Getter @Setter
        private int tamanho;
        ValorTeste(String valor, int tamanho) {
            this.valor = valor;
            this.tamanho = tamanho;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof ValorTeste valorTeste) {
                return this.tamanho == valorTeste.tamanho && this.valor == valorTeste.valor;
            }
            return false;
        }
    }
    private static Colecao<ValorTeste> colecaoDeListagem = new Colecao();
    private static List<ValorTeste> valores = List.of(
            new ValorTeste("A", 0),
            new ValorTeste("B", 2),
            new ValorTeste("D", 1),
            new ValorTeste("E", 3)
    );
    static  {
        for (ValorTeste valor : valores) {
            colecaoDeListagem.inserir(valor);
        }
    }

    @Test
    public void colecao_InserirEPegar_Sucesso() {
        var colecao = new Colecao<ValorTeste>();
        var valor = new ValorTeste("A", 0);
        var uuid = colecao.inserir(valor);
        var valorNaBase = colecao.pegar(uuid).map(Identificado::getObjeto);
        Assertions.assertEquals(valorNaBase, Optional.of(valor));
    }

    @Test
    public void colecao_PegarQuandoNaoExiste_Sucesso() {
        var colecao = new Colecao<ValorTeste>();
        var valorNaBase = colecao.pegar(UUID.randomUUID());
        Assertions.assertEquals(valorNaBase, Optional.empty());
    }

    @Test
    public void colecao_RemoverQuandoExiste_Sucesso() {
        var colecao = new Colecao<ValorTeste>();
        var valor = new ValorTeste("A", 0);
        var uuid = colecao.inserir(valor);
        var valorNaBase = colecao.pegar(uuid).map(Identificado::getObjeto);
        Assertions.assertEquals(valorNaBase, Optional.of(valor));
        Assertions.assertTrue(colecao.remover(uuid));
        Assertions.assertEquals(colecao.pegar(uuid).map(Identificado::getObjeto), Optional.empty());
    }

    @Test
    public void colecao_RemoverQuandoNaoExiste_Sucesso() {
        var colecao = new Colecao<ValorTeste>();
        var valor = new ValorTeste("A", 0);
        colecao.inserir(valor);
        Assertions.assertFalse(colecao.remover(UUID.randomUUID()));
    }

    @Test
    public void colecao_Atualizar_Sucesso() {
        var colecao = new Colecao<ValorTeste>();
        var valor = new ValorTeste("A", 0);
        var uuid = colecao.inserir(valor);
        colecao.atualizar(uuid, (v) ->  { v.setTamanho(1); });
        var valorNaBase = colecao.pegar(uuid).map(Identificado::getObjeto);
        Assertions.assertEquals(valorNaBase, Optional.of(new ValorTeste("A", 1)));
    }

    @Test
    public void colecao_ListarSemParametros_Sucesso() {
        var valores = colecaoDeListagem.listar(null, null, OpcoesListagem.builder().build())
                .stream()
                .map(Identificado::getObjeto)
                .collect(Collectors.toList());
        listEquals(valores, ColecaoTest.valores);
    }

    @Test
    public void colecao_ListarComFiltro_Sucesso() {
        var valores = colecaoDeListagem.listar(
                (entry) -> entry.getValue().getTamanho() > 0,
                        null,
                        OpcoesListagem.builder().build()
                )
                .stream()
                .map(Identificado::getObjeto)
                .collect(Collectors.toList());
        listEquals(valores, List.of(
                new ValorTeste("B", 2),
                new ValorTeste("D", 1),
                new ValorTeste("E", 3)
        ));
    }

    @Test
    public void colecao_ListarComSort_Sucesso() {
        var valores = colecaoDeListagem.listar(
                        null,
                        Comparator.comparing(entry -> entry.getValue().getTamanho()),
                        OpcoesListagem.builder().build()
                )
                .stream()
                .map(Identificado::getObjeto)
                .collect(Collectors.toList());
        Assertions.assertEquals(valores, List.of(
                new ValorTeste("A", 0),
                new ValorTeste("D", 1),
                new ValorTeste("B", 2),
                new ValorTeste("E", 3)
        ));
    }

    @Test
    public void colecao_ListarComLimite_Sucesso() {
        var valores = colecaoDeListagem.listar(
                        null,
                        null,
                        OpcoesListagem.builder()
                                .limite(OptionalInt.of(1))
                                .build()
                )
                .stream()
                .map(Identificado::getObjeto)
                .collect(Collectors.toList());
        listEquals(valores, List.of(
                new ValorTeste("A", 0)
        ));
    }

    @Test
    public void colecao_ListarComPular_Sucesso() {
        var valores = colecaoDeListagem.listar(
                        null,
                        null,
                        OpcoesListagem.builder()
                                .pular(OptionalInt.of(1))
                                .build()
                )
                .stream()
                .map(Identificado::getObjeto)
                .collect(Collectors.toList());
        listEquals(valores, List.of(
                new ValorTeste("B", 2)
        ));
    }

    @Test
    public void colecao_ListarUmSemFiltro_Sucesso() {
        var valor = colecaoDeListagem.listarUm(null)
                .map(Identificado::getObjeto);
        Assertions.assertEquals(Optional.of(new ValorTeste("A", 0)), valor);
    }

    @Test
    public void colecao_ListarUmComFiltro_Sucesso() {
        var valor = colecaoDeListagem.listarUm(
                        (entry) -> entry.getValue().tamanho > 0
                )
                .map(Identificado::getObjeto);
        Assertions.assertEquals(Optional.of(new ValorTeste("B", 2)), valor);
    }

}
