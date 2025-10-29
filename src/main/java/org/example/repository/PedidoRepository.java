package org.example.repository;

import org.example.model.Pedido;
import org.example.repository.interfaces.IPedidoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PedidoRepository implements IPedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void adicionar(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public List<Pedido> listar() {
        return Collections.unmodifiableList(pedidos);
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append("[PEDIDOS]\n");
        for (Pedido p : pedidos) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}
