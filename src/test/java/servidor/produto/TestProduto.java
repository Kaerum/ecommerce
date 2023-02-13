package servidor.produto;
import compartilhado.produto.informatica.Informatica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static compartilhado.produto.informatica.CategoriaInformatica.NOTEBOOK;


public class TestProduto {
    @Test
    public void testProduto_Setter_Success() {
        Informatica produto = new Informatica();
        produto.setCategoria(NOTEBOOK);
        Assertions.assertEquals(NOTEBOOK, produto.getCategoria());
    }
}
enum TesteBug {
    TESTE_BUG
}

