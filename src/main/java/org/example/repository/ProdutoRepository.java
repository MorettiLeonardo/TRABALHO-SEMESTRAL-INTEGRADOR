package org.example.repository;

import org.example.model.Produto;
import org.example.repository.interfaces.IProdutoRepository;

import java.util.*;

public class ProdutoRepository implements IProdutoRepository {
    private final Map<Integer, Produto> produtos = new HashMap<>();

    @Override
    public void adicionar(Produto produto) {
        produtos.put(produto.getId(), produto);
    }

    @Override
    public Produto buscarPorId(int id) {
        return produtos.get(id);
    }

    @Override
    public Collection<Produto> listar() {
        return produtos.values();
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append("[PRODUTOS]\n");
        for (Produto p : produtos.values()) {
            sb.append(p.getId()).append(";")
                    .append(p.getNome()).append(";")
                    .append(p.getPreco()).append("\n");
        }
        return sb.toString();
    }
}
