package middlewares;

import java.util.ArrayList;
import java.util.Iterator;
import model.Veiculo;

public class ValidaRegistrosDeVeiculos {
	
	private ArrayList<Veiculo> veiculos;
	private Iterator<Veiculo> veiculosIterator;
	
	public ValidaRegistrosDeVeiculos(ArrayList<Veiculo> veiculos) {
		this.veiculos = veiculos;
		this.veiculosIterator = veiculos.iterator();
	}
	
	public boolean existeVeiculoCadastrado() {
		return veiculos.isEmpty();
	}
}
