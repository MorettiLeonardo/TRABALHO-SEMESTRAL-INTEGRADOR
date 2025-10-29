package org.example.repository;


import org.example.model.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    public void adicionar(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> listar() {
        return Collections.unmodifiableList(pedidos);
    }

    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append("[PEDIDOS]\n");
        for (Pedido p : pedidos) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}
