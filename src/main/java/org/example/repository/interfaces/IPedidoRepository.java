package org.example.repository.interfaces;

import org.example.model.Pedido;

import java.util.List;

public interface IPedidoRepository {

    void adicionar(Pedido pedido);

    List<Pedido> listar();

    String serializar();
}
