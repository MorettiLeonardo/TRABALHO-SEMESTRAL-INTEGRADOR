package org.example.repository;

import org.example.model.Cliente;

import java.util.*;

public class ClienteRepository {
    private final Map<String, Cliente> clientes = new HashMap<>();

    public void adicionar(Cliente cliente) {
        clientes.put(cliente.getEmail(), cliente);
    }

    public Cliente buscarPorEmail(String email) {
        return clientes.get(email);
    }

    public Collection<Cliente> listar() {
        return clientes.values();
    }

    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append("[CLIENTES]\n");
        for (Cliente c : clientes.values()) {
            sb.append(c.getNome()).append(";").append(c.getEmail()).append("\n");
        }
        return sb.toString();
    }
}