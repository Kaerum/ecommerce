package servidor;

import interfaces.RespostaServidor;
import interfaces.usuario.ColecaoAutenticada;
import servidor.banco.Identificado;
import servidor.banco.colecao.Colecao;
import servidor.banco.sessao.Permissao;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class ColecaoAutenticadaImpl<T> implements ColecaoAutenticada<T> {
    private Colecao<T> colecao;
    private Set<Permissao> permissoes;
    ColecaoAutenticadaImpl (Colecao<T> colecao, Set<Permissao> permissoes) {
        this.colecao = colecao;
        this.permissoes = permissoes;
    }

    private <T> Optional<RespostaServidor<T>> checarPermissao(Permissao permissao) {
        if (!permissoes.contains(permissao)) {
            return Optional.of(RespostaServidor.erro(
                    RespostaServidor.faltaPermissaoColecao(permissao, colecao.getClass().getSimpleName())
            ));
        }
        return Optional.empty();
    }

    @Override
    public RespostaServidor<List<Identificado<T>>> listar(Predicate<Map.Entry<UUID, T>> filtro, Comparator<Map.Entry<UUID, T>> sortear, Optional<Integer> limite) {
        Optional<RespostaServidor<List<Identificado<T>>>> naoPodeListar = checarPermissao(Permissao.Ler);
        return naoPodeListar.orElseGet(() -> RespostaServidor.sucesso(colecao.listar(filtro, sortear, limite)));
    }

    @Override
    public RespostaServidor<Identificado<T>> listarUm(Predicate<Map.Entry<UUID, T>> filtro) {
        Optional<RespostaServidor<Identificado<T>>> naoPodeListar = checarPermissao(Permissao.Ler);
        return naoPodeListar.orElseGet(() -> RespostaServidor.sucesso(colecao.listarUm(filtro)));
    }

    @Override
    public RespostaServidor<Boolean> remover(UUID id) {
        Optional<RespostaServidor<Boolean>> naoPodeRemover = checarPermissao(Permissao.Remover);
        return naoPodeRemover.orElseGet(() -> RespostaServidor.sucesso(colecao.remover(id)));
    }

    @Override
    public RespostaServidor<Identificado<T>> pegar(UUID id) {
        Optional<RespostaServidor<Identificado<T>>> naoPodePegar = checarPermissao(Permissao.Ler);
        return naoPodePegar.orElseGet(() -> RespostaServidor.sucesso(colecao.pegar(id)));
    }

    @Override
    public RespostaServidor<UUID> inserir(T objeto) {
        Optional<RespostaServidor<UUID>> naoPodeInserir = checarPermissao(Permissao.Modificar);
        return naoPodeInserir.orElseGet(() -> RespostaServidor.sucesso(colecao.inserir(objeto)));
    }

    @Override
    public RespostaServidor<Boolean> atualizar(UUID id, Function<T, Void> metodoAtualizador) {
        Optional<RespostaServidor<Boolean>> naoPodeAtualizar = checarPermissao(Permissao.Modificar);
        return naoPodeAtualizar.orElseGet(() -> RespostaServidor.sucesso(colecao.atualizar(id, metodoAtualizador)));
    }
}
