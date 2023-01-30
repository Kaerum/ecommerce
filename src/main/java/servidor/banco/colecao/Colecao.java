package servidor.banco.colecao;

import servidor.banco.Identificado;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
public class Colecao<T> {
    private HashMap<UUID, T> mapa = new LinkedHashMap<>();

    public List<Identificado<T>> listar(
            Predicate<Map.Entry<UUID, T>> filtro,
            Comparator<Map.Entry<UUID, T>> sortear,
            OpcoesListagem opcoes
    ) {
        var lista = mapa.entrySet().stream();
        if (filtro != null) {
            lista = lista.filter(filtro);
        }
        if (sortear != null) {
            lista = lista.sorted(sortear);
        }
        if (opcoes.getPular().isPresent()) {
            lista = lista.skip(opcoes.getPular().getAsInt());
        }
        if (opcoes.getLimite().isPresent()) {
            lista = lista.limit(opcoes.getLimite().getAsInt());
        }
        return lista.map(entry -> new Identificado<T>(entry.getKey(), entry.getValue())).toList();
    }

    public Optional<Identificado<T>> listarUm(Predicate<Map.Entry<UUID, T>> filtro) {
        var usuarios = listar(
                filtro,
                null,
                OpcoesListagem.builder()
                        .limite(OptionalInt.of(1))
                        .build()
        );
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

    public boolean atualizar(UUID id, Consumer<T> metodoAtualizador) {
        var objeto = mapa.get(id);
        if (objeto != null) {
            metodoAtualizador.accept(objeto);
            return true;
        }
        return false;
    }
}
