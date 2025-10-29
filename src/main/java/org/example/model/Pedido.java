package org.example.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private static int contador = 1;
    private final int id;
    private final Cliente cliente;
    private final List<ItemPedido> itens;
    private final LocalDateTime dataCriacao;

    public Pedido(Cliente cliente) {
        this.id = contador++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataCriacao = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        itens.add(new ItemPedido(produto, quantidade));
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(id)
                .append(" - Cliente: ").append(cliente.getNome())
                .append(" - Data: ")
                .append(dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .append("\n");

        for (ItemPedido item : itens) {
            sb.append("  ").append(item).append("\n");
        }

        sb.append(String.format("Total: R$ %.2f", calcularTotal()));
        return sb.toString();
    }
}