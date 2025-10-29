package org.example.ui;

import org.example.model.Cliente;
import org.example.model.Produto;
import org.example.repository.ClienteRepository;
import org.example.repository.PedidoRepository;
import org.example.repository.ProdutoRepository;
import org.example.service.PedidoService;
import org.example.service.PersistenciaService;

import java.util.*;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private final ClienteRepository clienteRepo = new ClienteRepository();
    private final ProdutoRepository produtoRepo = new ProdutoRepository();
    private final PedidoRepository pedidoRepo = new PedidoRepository();
    private final PedidoService pedidoService = new PedidoService(clienteRepo, produtoRepo, pedidoRepo);
    private final PersistenciaService persistenciaService = new PersistenciaService(clienteRepo, produtoRepo, pedidoRepo);

    public void iniciar() {
        persistenciaService.carregarDados();
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE PEDIDOS ===");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar produto");
            System.out.println("3. Criar pedido");
            System.out.println("4. Listar pedidos");
            System.out.println("5. Salvar e sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1 -> cadastrarCliente();
                    case 2 -> cadastrarProduto();
                    case 3 -> criarPedido();
                    case 4 -> listarPedidos();
                    case 5 -> persistenciaService.salvarDados();
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 5);
    }

    private void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        clienteRepo.adicionar(new Cliente(nome, email));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        produtoRepo.adicionar(new Produto(nome, preco));
        System.out.println("Produto cadastrado!");
    }

    private void criarPedido() {
        System.out.print("Email do cliente: ");
        String email = scanner.nextLine();
        pedidoService.criarPedido(email, scanner);
    }

    private void listarPedidos() {
        pedidoRepo.listar().forEach(System.out::println);
    }
}
