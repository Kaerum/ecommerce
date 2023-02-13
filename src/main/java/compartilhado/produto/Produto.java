package compartilhado.produto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


public class Produto<T extends Enum<?>> {
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private double preco;
    @Getter @Setter
    private String marca;
    @Getter @Setter
    protected T categoria;
    @Getter
    private UUID uuid = null;
    public Produto() {
        uuid = UUID.randomUUID();
    }
}
