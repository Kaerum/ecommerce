package servidor.dados;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public interface GerenteDeDados<T> {
    List<Identificado<T>> listar(Predicate<Map.Entry<UUID, T>> metodoFiltro, Comparator<Map.Entry<UUID, T>> metodoSortear, Optional<Integer> limite);
    boolean remover(UUID id);
    Optional<Identificado<T>> pegar(UUID id);
    void inserir(T objeto);
    void atualizar(UUID id, Function<T, Void> metodoAtualizador);
}
