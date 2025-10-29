package org.example.repositorys;

import org.example.model.Produto;

import java.util.*;

public class ProdutoRepository {
    private final Map<Integer, Produto> produtos = new HashMap<>();

    public void adicionar(Produto produto) {
        produtos.put(produto.getId(), produto);
    }

    public Produto buscarPorId(int id) {
        return produtos.get(id);
    }

    public Collection<Produto> listar() {
        return produtos.values();
    }

    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append("[PRODUTOS]\n");
        for (Produto p : produtos.values()) {
            sb.append(p.getId()).append(";").append(p.getNome()).append(";").append(p.getPreco()).append("\n");
        }
        return sb.toString();
    }
}