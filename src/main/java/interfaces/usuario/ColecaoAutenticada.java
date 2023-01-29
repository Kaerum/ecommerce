package interfaces.usuario;

import interfaces.RespostaServidor;
import servidor.banco.Identificado;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ColecaoAutenticada<T> {
    public RespostaServidor<List<Identificado<T>>> listar(
            Predicate<Map.Entry<UUID, T>> filtro,
            Comparator<Map.Entry<UUID, T>> sortear,
            Optional<Integer> limite
    );

    public RespostaServidor<Identificado<T>> listarUm(Predicate<Map.Entry<UUID, T>> filtro);

    public RespostaServidor<Boolean> remover(UUID id);

    public RespostaServidor<Identificado<T>> pegar(UUID id);

    public RespostaServidor<UUID> inserir(T objeto);

    public RespostaServidor<Void> atualizar(UUID id, Function<T, Void> metodoAtualizador);

}
