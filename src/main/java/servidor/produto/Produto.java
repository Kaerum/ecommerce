package servidor.produto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


public class Produto {
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private double preco;
    @Getter @Setter
    private String marca;
    @Getter @Setter
    protected Enum categoria;
    @Getter @Setter
    protected Enum subCategoria;
    @Getter
    private UUID uuid = null;
    public Produto() {
        uuid = UUID.randomUUID();
    }
}
