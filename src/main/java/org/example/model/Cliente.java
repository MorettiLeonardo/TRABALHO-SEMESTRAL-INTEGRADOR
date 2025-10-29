package org.example.model;


public class Cliente {
    private String nome;
    private String email;

    public Cliente(String nome, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", nome, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente)) return false;
        Cliente other = (Cliente) obj;
        return this.email.equalsIgnoreCase(other.email);
    }

    @Override
    public int hashCode() {
        return email.toLowerCase().hashCode();
    }
}
