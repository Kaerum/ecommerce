package produto;

import produto.Informatica;
import produto.Produto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static produto.Informatica.Categoria.*;

public class TesteInformatica {
    public static void main(String[] args) {
        // TODO repensar isso
        Map<UUID, Produto> produtos = new HashMap<UUID, Produto>();
        Informatica prod1 = new Informatica();
        prod1.setCategoria(NOTEBOOK);
        produtos.put(prod1.getUuid(),prod1);
        System.out.println(produtos.toString());
    }
}
