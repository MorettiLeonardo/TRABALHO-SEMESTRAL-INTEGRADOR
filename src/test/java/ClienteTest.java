import org.example.model.Cliente;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ClienteTest {

    @Test
    public void deveCriarClienteComSucesso() {
        Cliente c = new Cliente("João", "joao@email.com");
        assertEquals("João", c.getNome());
        assertEquals("joao@email.com", c.getEmail());
    }

    @Test
    public void naoDevePermitirEmailInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Maria", "mariaemail.com");
        });
    }

    @Test
    public void naoDevePermitirNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("", "maria@email.com");
        });
    }
}