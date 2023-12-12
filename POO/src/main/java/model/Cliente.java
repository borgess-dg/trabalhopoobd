package model;

public class Cliente {
    private String nome;
    private String endereco;
    private String telefone;

    public Cliente(String nome, String endereco, String telefone){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Cliente(){}

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
}
