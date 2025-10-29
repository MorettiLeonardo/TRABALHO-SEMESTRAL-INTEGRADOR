package org.example.service;


import org.example.exception.ClienteNaoEncontradoException;
import org.example.exception.PedidoInvalidoException;
import org.example.exception.ProdutoNaoEncontradoException;
import org.example.model.Cliente;
import org.example.model.Pedido;
import org.example.model.Produto;
import org.example.repositorys.ClienteRepository;
import org.example.repositorys.PedidoRepository;
import org.example.repositorys.ProdutoRepository;

import java.util.Scanner;

public class PedidoService {
    private final ClienteRepository clienteRepo;
    private final ProdutoRepository produtoRepo;
    private final PedidoRepository pedidoRepo;

    public PedidoService(ClienteRepository clienteRepo, ProdutoRepository produtoRepo, PedidoRepository pedidoRepo) {
        this.clienteRepo = clienteRepo;
        this.produtoRepo = produtoRepo;
        this.pedidoRepo = pedidoRepo;
    }

    public void criarPedido(String emailCliente, Scanner scanner) {
        Cliente cliente = clienteRepo.buscarPorEmail(emailCliente);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente com e-mail " + emailCliente + " não encontrado.");
        }

        Pedido pedido = new Pedido(cliente);

        boolean adicionando = true;
        while (adicionando) {
            System.out.println("\n=== ADICIONAR ITEM AO PEDIDO ===");
            listarProdutosDisponiveis();

            System.out.print("Digite o ID do produto (ou 0 para finalizar): ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id == 0) {
                adicionando = false;
                break;
            }

            Produto produto = produtoRepo.buscarPorId(id);
            if (produto == null) {
                throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado.");
            }

            System.out.print("Quantidade: ");
            int qtd = scanner.nextInt();
            scanner.nextLine();

            if (qtd <= 0) {
                throw new PedidoInvalidoException("Quantidade deve ser maior que zero.");
            }

            pedido.adicionarItem(produto, qtd);
            System.out.println("Item adicionado com sucesso!");
        }

        if (pedido.getItens().isEmpty()) {
            throw new PedidoInvalidoException("O pedido não pode estar vazio.");
        }

        pedidoRepo.adicionar(pedido);
        System.out.println("\n✅ Pedido criado com sucesso!");
        System.out.println(pedido);
    }

    private void listarProdutosDisponiveis() {
        System.out.println("\nProdutos disponíveis:");
        for (Produto p : produtoRepo.listar()) {
            System.out.println(p);
        }
    }
}