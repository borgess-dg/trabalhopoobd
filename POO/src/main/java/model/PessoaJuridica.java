package model;

public class PessoaJuridica extends Cliente{
    private String cnpj;

    public PessoaJuridica(String nome, String endereco, String telefone, String cnpj){
        super(nome, endereco, telefone);

        this.cnpj = cnpj;
    }

    public PessoaJuridica(){}

    public String getCnpj(){
        return this.cnpj;
    }

    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }
}
