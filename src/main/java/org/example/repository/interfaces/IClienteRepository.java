package org.example.repository.interfaces;

import org.example.model.Cliente;

import java.util.Collection;

public interface IClienteRepository {

    void adicionar(Cliente cliente);

    Cliente buscarPorEmail(String email);

    Collection<Cliente> listar();

    String serializar();
}
