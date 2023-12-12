package model;

public class Caminhao extends Veiculo{
	private float capacidadeCarga;
	private int numerodeEixos;
	public Caminhao() {
		super();
	}
	public Caminhao(String marca, String modelo, String placa, int anofabricacao, int anomodelo, float capacidadeCarga, int numeroDeEixos) {
		super(marca, modelo, placa, anofabricacao, anomodelo);
		this.capacidadeCarga = capacidadeCarga;
		this.numerodeEixos = numeroDeEixos;
	}
	public float getCapacidadeCarga() {
		return capacidadeCarga;
	}
	public void setCapacidadecarga(float capacidadeCarga) {
		this.capacidadeCarga = capacidadeCarga;
	}
	public int getNumerosdeEixos() {
		return numerodeEixos;
	}
	public void setNumerosdeEixos(int numerodeEixos) {
		this.numerodeEixos = numerodeEixos;
	}
	
	
}
