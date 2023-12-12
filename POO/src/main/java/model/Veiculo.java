package model;

public abstract class Veiculo {
	private String marca;
	private String modelo;
	private String placa;
	private int anofabricacao;
	private int anomodelo;
	
	public Veiculo() {
		super();
	}

	public Veiculo(String marca, String modelo, String placa, int anofabricacao, int anomodelo) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.anofabricacao = anofabricacao;
		this.anomodelo = anomodelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getAnofabricacao() {
		return anofabricacao;
	}

	public void setAnofabricacao(int anofabricacao) {
		this.anofabricacao = anofabricacao;
	}

	public int getAnomodelo() {
		return anomodelo;
	}

	public void setAnomodelo(int anomodelo) {
		this.anomodelo = anomodelo;
	}
	
	
}
