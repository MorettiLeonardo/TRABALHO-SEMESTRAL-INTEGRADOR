package org.example.repository.interfaces;

import org.example.model.Produto;

import java.util.Collection;

public interface IProdutoRepository {

    void adicionar(Produto produto);

    Produto buscarPorId(int id);

    Collection<Produto> listar();

    String serializar();
}
