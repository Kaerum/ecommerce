package servidor.dados;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

class Produto {}

public class GerenciadorDeDados<T> implements GerenteDeDados<T> {
    private HashMap<UUID, T> mapa = new HashMap<>();

    @Override
    public List<Identificado<T>> listar(Predicate<Map.Entry<UUID, T>> filtro, Comparator<Map.Entry<UUID, T>> sortear, Optional<Integer> limite) {
        var lista = mapa.entrySet().stream();
        if (filtro != null) {
            lista = lista.filter(filtro);
        }
        if (sortear != null) {
            lista = lista.sorted(sortear);
        }
        if (limite.isPresent()) {
            lista = lista.limit(limite.get());
        }
        return lista.map(entry -> new Identificado<T>(entry.getKey(), entry.getValue())).toList();
    }

    @Override
    public boolean remover(UUID id) {
        return mapa.remove(id) != null;
    }

    @Override
    public Optional<Identificado<T>> pegar(UUID id) {
        return Optional.ofNullable(mapa.get(id)).map(objeto -> new Identificado<>(id, objeto));
    }

    @Override
    public void inserir(T objeto) {
        UUID id = UUID.randomUUID();
        mapa.put(id, objeto);
    }

    @Override
    public void atualizar(UUID id, Function<T, Void> metodoAtualizador) {
        var objeto = mapa.get(id);
        if (objeto != null) {
            metodoAtualizador.apply(objeto);
        }
    }
}
