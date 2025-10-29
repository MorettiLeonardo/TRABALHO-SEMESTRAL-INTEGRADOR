import org.example.model.Cliente;
import org.example.model.Pedido;
import org.example.model.Produto;
import org.junit.Test;

import static org.junit.Assert.*;

public class PedidoTest {

    @Test
    public void deveCalcularTotalCorretamente() {
        Cliente c = new Cliente("João", "joao@email.com");
        Produto p1 = new Produto("Mouse", 50);
        Produto p2 = new Produto("Teclado", 100);

        Pedido pedido = new Pedido(c);
        pedido.adicionarItem(p1, 2);
        pedido.adicionarItem(p2, 1);

        assertEquals(200.0, pedido.calcularTotal(), 0.001);
    }

    @Test
    public void naoDevePermitirItemComQuantidadeZero() {
        Cliente c = new Cliente("Maria", "maria@email.com");
        Produto p = new Produto("Monitor", 500);

        Pedido pedido = new Pedido(c);
        assertThrows(IllegalArgumentException.class, () -> {
            pedido.adicionarItem(p, 0);
        });
    }

    @Test
    public void deveGerarPedidoComIdUnico() {
        Cliente c = new Cliente("Ana", "ana@email.com");
        Pedido p1 = new Pedido(c);
        Pedido p2 = new Pedido(c);
        assertNotEquals(p1.getId(), p2.getId());
    }
}