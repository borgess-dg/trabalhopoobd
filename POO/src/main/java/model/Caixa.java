package model;

import java.util.ArrayList;

public class Caixa {
    private ArrayList<Locacao> locacoes;

    public Caixa(ArrayList<Locacao> locacoes){
        this.locacoes = locacoes;
    }

    public void pagamentoLocacao(){}
    public void totalArrecadado(){}
    public void totalArrecadadoPorPeriodo(){}
    public void totalAReceber(){}
}
