package interfaces.usuario;

import interfaces.RespostaServidor;
import servidor.banco.Identificado;
import servidor.banco.colecao.OpcoesListagem;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface ColecaoAutenticada<T> {
    public RespostaServidor<List<Identificado<T>>> listar(
            Predicate<Map.Entry<UUID, T>> filtro,
            Comparator<Map.Entry<UUID, T>> sortear,
            OpcoesListagem opcoes
    );

    public RespostaServidor<Identificado<T>> listarUm(Predicate<Map.Entry<UUID, T>> filtro);

    public RespostaServidor<Boolean> remover(UUID id);

    public RespostaServidor<Identificado<T>> pegar(UUID id);

    public RespostaServidor<UUID> inserir(T objeto);

    public RespostaServidor<Boolean> atualizar(UUID id, Consumer<T> metodoAtualizador);

}
