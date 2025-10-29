package org.example.service;

import org.example.model.Cliente;
import org.example.model.ItemPedido;
import org.example.model.Pedido;
import org.example.model.Produto;
import org.example.repository.ClienteRepository;
import org.example.repository.PedidoRepository;
import org.example.repository.ProdutoRepository;

import java.io.*;

public class PersistenciaService {
    private final ClienteRepository clienteRepo;
    private final ProdutoRepository produtoRepo;
    private final PedidoRepository pedidoRepo;

    private static final String ARQUIVO = "dados.txt";

    public PersistenciaService(ClienteRepository c, ProdutoRepository p, PedidoRepository pe) {
        this.clienteRepo = c;
        this.produtoRepo = p;
        this.pedidoRepo = pe;
    }

    public void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            writer.println("[CLIENTES]");
            for (Cliente c : clienteRepo.listar()) {
                writer.println(c.getNome() + ";" + c.getEmail());
            }

            writer.println("[PRODUTOS]");
            for (Produto p : produtoRepo.listar()) {
                writer.println(p.getId() + ";" + p.getNome() + ";" + p.getPreco());
            }

            writer.println("[PEDIDOS]");
            for (Pedido ped : pedidoRepo.listar()) {
                StringBuilder sb = new StringBuilder();
                sb.append(ped.getCliente().getEmail()).append("|"); // vincula pedido ao cliente
                for (ItemPedido item : ped.getItens()) {
                    sb.append(item.getProduto().getId()).append(":").append(item.getQuantidade()).append(",");
                }
                writer.println(sb.toString());
            }

            System.out.println("✅ Dados salvos em " + ARQUIVO);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            String secao = "";

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("[")) {
                    secao = linha;
                    continue;
                }
                if (linha.isBlank()) continue;

                switch (secao) {
                    case "[CLIENTES]" -> {
                        String[] parts = linha.split(";");
                        clienteRepo.adicionar(new Cliente(parts[0], parts[1]));
                    }
                    case "[PRODUTOS]" -> {
                        String[] parts = linha.split(";");
                        Produto p = new Produto(parts[1], Double.parseDouble(parts[2]));
                        produtoRepo.adicionar(p);
                    }
                    case "[PEDIDOS]" -> {
                        String[] parts = linha.split("\\|");
                        Cliente cliente = clienteRepo.buscarPorEmail(parts[0]);
                        if (cliente == null) continue;

                        Pedido pedido = new Pedido(cliente);

                        String[] itens = parts[1].split(",");
                        for (String itemStr : itens) {
                            if (itemStr.isBlank()) continue;
                            String[] itemParts = itemStr.split(":");
                            int produtoId = Integer.parseInt(itemParts[0]);
                            int quantidade = Integer.parseInt(itemParts[1]);

                            Produto produto = produtoRepo.buscarPorId(produtoId);
                            if (produto != null) pedido.adicionarItem(produto, quantidade);
                        }
                        pedidoRepo.adicionar(pedido);
                    }
                }
            }

            System.out.println("✅ Dados carregados de " + ARQUIVO);

        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}