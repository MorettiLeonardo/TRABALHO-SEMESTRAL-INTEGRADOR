import org.example.model.Produto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProdutoTest {

    @Test
    public void deveCriarProdutoCorretamente() {
        Produto p = new Produto("Mouse", 89.90);
        assertEquals("Mouse", p.getNome());
        assertEquals(89.90, p.getPreco(), 0.001); // delta = 0.001
    }

    @Test
    public void deveGerarIdsDiferentes() {
        Produto p1 = new Produto("Teclado", 129.90);
        Produto p2 = new Produto("Monitor", 899.90);
        assertNotEquals(p1.getId(), p2.getId());
    }
}