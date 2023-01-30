package servidor.banco.colecao;

import servidor.banco.Identificado;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
public class Colecao<T> {
    private HashMap<UUID, T> mapa = new HashMap<>();

    public List<Identificado<T>> listar(
            Predicate<Map.Entry<UUID, T>> filtro,
            Comparator<Map.Entry<UUID, T>> sortear,
            Optional<Integer> limite
    ) {
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

    public Optional<Identificado<T>> listarUm(Predicate<Map.Entry<UUID, T>> filtro) {
        var usuarios = listar(filtro, null, Optional.of(1));
        if (usuarios.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(usuarios.get(0));
    }
    public boolean remover(UUID id) {
        return mapa.remove(id) != null;
    }

    public Optional<Identificado<T>> pegar(UUID id) {
        return Optional.ofNullable(mapa.get(id)).map(objeto -> new Identificado<>(id, objeto));
    }

    public UUID inserir(T objeto) {
        UUID id = UUID.randomUUID();
        mapa.put(id, objeto);
        return id;
    }

    public boolean atualizar(UUID id, Function<T, Void> metodoAtualizador) {
        var objeto = mapa.get(id);
        if (objeto != null) {
            metodoAtualizador.apply(objeto);
            return true;
        }
        return false;
    }
}
