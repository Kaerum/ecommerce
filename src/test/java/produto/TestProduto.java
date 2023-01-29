package produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static produto.Informatica.Categoria.NOTEBOOK;

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

