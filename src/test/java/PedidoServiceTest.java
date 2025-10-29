import org.example.exception.ClienteNaoEncontradoException;
import org.example.exception.PedidoInvalidoException;
import org.example.model.Cliente;
import org.example.repository.ClienteRepository;
import org.example.repository.PedidoRepository;
import org.example.repository.ProdutoRepository;
import org.example.service.PedidoService;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertThrows;

public class PedidoServiceTest {

    @Test
    public void deveLancarExcecaoClienteNaoEncontrado() {
        ClienteRepository clienteRepo = new ClienteRepository();
        ProdutoRepository produtoRepo = new ProdutoRepository();
        PedidoRepository pedidoRepo = new PedidoRepository();

        PedidoService service = new PedidoService(clienteRepo, produtoRepo, pedidoRepo);
        Scanner scanner = new Scanner("0"); // simula input do console

        assertThrows(ClienteNaoEncontradoException.class, () -> {
            service.criarPedido("nao@existe.com", scanner);
        });
    }

    @Test
    public void deveLancarExcecaoPedidoVazio() {
        ClienteRepository clienteRepo = new ClienteRepository();
        ProdutoRepository produtoRepo = new ProdutoRepository();
        PedidoRepository pedidoRepo = new PedidoRepository();

        Cliente c = new Cliente("João", "joao@email.com");
        clienteRepo.adicionar(c);

        PedidoService service = new PedidoService(clienteRepo, produtoRepo, pedidoRepo);
        Scanner scanner = new Scanner("0\n"); // adiciona quebra de linha

        assertThrows(PedidoInvalidoException.class, () -> {
            service.criarPedido("joao@email.com", scanner);
        });
    }
}