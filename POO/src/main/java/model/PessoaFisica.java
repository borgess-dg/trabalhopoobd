package model;

import java.time.LocalDate;

public class PessoaFisica extends Cliente {
    private String cpf;
    private LocalDate dataNascimento;

    public PessoaFisica(String nome, String endereco, String telefone, String cpf, LocalDate dataNascimento){
        super(nome, endereco, telefone);

        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
    public PessoaFisica(){}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
