package servidor.dados;

import java.util.UUID;

public class Identificado<T> {

    private final UUID id;
    private final T objeto;
    public Identificado (UUID id, T objeto) {
        this.id = id;
        this.objeto = objeto;
    }

    public Identificado (T objeto) {
        this.id = UUID.randomUUID();
        this.objeto = objeto;
    }
    public UUID getId() {
        return this.id;
    }

    public T getObjeto() {
        return this.objeto;
    }
}
