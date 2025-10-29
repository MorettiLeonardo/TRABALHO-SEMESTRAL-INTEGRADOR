package org.example.repository;

import org.example.model.Cliente;
import org.example.repository.interfaces.IClienteRepository;

import java.util.*;

public class ClienteRepository implements IClienteRepository {
    private final Map<String, Cliente> clientes = new HashMap<>();

    @Override
    public void adicionar(Cliente cliente) {
        clientes.put(cliente.getEmail(), cliente);
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        return clientes.get(email);
    }

    @Override
    public Collection<Cliente> listar() {
        return clientes.values();
    }

    @Override
    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append("[CLIENTES]\n");
        for (Cliente c : clientes.values()) {
            sb.append(c.getNome()).append(";").append(c.getEmail()).append("\n");
        }
        return sb.toString();
    }
}
