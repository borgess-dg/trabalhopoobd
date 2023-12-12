package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import database.Database;
import model.Caminhao;
import model.Veiculo;
import model.Carro;

public class VeiculoController {
	
	private Connection connectionDB;
	
	public VeiculoController() {
		this.connectionDB = new Database().connect();
	}
	
	public void incluir(Veiculo veiculo) {
		String query = "INSERT INTO veiculos(marca, modelo, placa, ano_fabricacao, ano_modelo, capacidade_passageiros, quantidade_portas, capacidade_carga, numero_eixos, data_cadastro) VALUES (?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement preparedStatement = null;
		Caminhao caminhaoCadastro = null;
		Carro carroCadastro = null;
		
		try {
			preparedStatement = this.connectionDB.prepareStatement(query);
			preparedStatement.setString(1, veiculo.getMarca());
			preparedStatement.setString(2, veiculo.getModelo());
			preparedStatement.setString(3, veiculo.getPlaca());
			preparedStatement.setInt(4, veiculo.getAnofabricacao());
			preparedStatement.setInt(5, veiculo.getAnomodelo());
			
			if(veiculo instanceof Carro) {
				carroCadastro = (Carro) veiculo;
				preparedStatement.setInt(6, carroCadastro.getCapacidadePassageiros());
				preparedStatement.setInt(7, carroCadastro.getQuantidadePortas());
				preparedStatement.setNull(8, 0);
				preparedStatement.setNull(9, 0);
				preparedStatement.setTimestamp(10, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
			}
			else {
				caminhaoCadastro = (Caminhao) veiculo;
				preparedStatement.setNull(6, 0);
				preparedStatement.setNull(7, 0);
				preparedStatement.setFloat(8, caminhaoCadastro.getCapacidadeCarga());
				preparedStatement.setInt(9, caminhaoCadastro.getNumerosdeEixos());
				preparedStatement.setTimestamp(10, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
			}
			
			preparedStatement.execute();
			System.out.println("Veículo incluído com sucesso!");
		}
		catch (SQLException error) {
			System.out.println(error);
		}
	}
	
	public void alterar(Veiculo veiculo, String placa) {
		String query = "";
		PreparedStatement preparedStatement = null;
		
		if(veiculo instanceof Carro) {
			query = "UPDATE veiculos SET marca = ? modelo = ?, placa = ?, ano_fabricacao = ?, ano_modelo = ?, capacidade_passageiros = ?, quantidade_portas = ?, data_alteracao = ? WHERE placa = ?";
		}
		else {
			query = "UPDATE veiculos SET marca = ? modelo = ?, placa = ?, ano_fabricacao = ?, ano_modelo = ?, capacidade_carga = ?, numero_eixos = ?, data_alteracao = ? WHERE placa = ?";
		}
		
		Carro carroModel = null;
		Caminhao caminhaoModel = null;
		
		try {
			preparedStatement = this.connectionDB.prepareStatement(query);
			if(veiculo instanceof Carro) {
				carroModel = (Carro) veiculo;
				preparedStatement.setString(1, carroModel.getMarca());
				preparedStatement.setString(2, carroModel.getModelo());
				preparedStatement.setString(3, carroModel.getPlaca());
				preparedStatement.setInt(4, carroModel.getAnofabricacao());
				preparedStatement.setInt(5, carroModel.getAnomodelo());
				preparedStatement.setInt(6, carroModel.getCapacidadePassageiros());
				preparedStatement.setInt(7, carroModel.getQuantidadePortas());
				preparedStatement.setTimestamp(8, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
				preparedStatement.setString(9, placa);
			}
			else {
				caminhaoModel = (Caminhao) veiculo;
				preparedStatement.setString(1, caminhaoModel.getMarca());
				preparedStatement.setString(2, caminhaoModel.getModelo());
				preparedStatement.setString(3, caminhaoModel.getPlaca());
				preparedStatement.setInt(4, caminhaoModel.getAnofabricacao());
				preparedStatement.setInt(5, caminhaoModel.getAnomodelo());
				preparedStatement.setFloat(6, caminhaoModel.getCapacidadeCarga());
				preparedStatement.setInt(7, caminhaoModel.getNumerosdeEixos());
				preparedStatement.setTimestamp(8, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
				preparedStatement.setString(9, placa);
			}
			preparedStatement.execute();
			preparedStatement.close();
		}
		catch(SQLException error) {
			System.out.println(error);
		}
	}
	
	public void excluir(String placa) {
		String query = "DELETE FROM veiculos WHERE placa = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connectionDB.prepareStatement(query);		
			preparedStatement.setString(1, placa);
			preparedStatement.execute();
			preparedStatement.close();
		}
		catch (SQLException error) {
			System.out.println(error);
		}
	}
	
	public Veiculo consulta(String placa) {
		String query = "SELECT marca, modelo, placa, ano_fabricacao, ano_modelo, capacidade_passageiros, quantidade_portas, capacidade_carga, numero_eixos FROM veiculos WHERE placa = ?";
		PreparedStatement preparedStatement = null;
		Carro carroModel = new Carro();
		Caminhao caminhaoModel = new Caminhao();
		Veiculo veiculo = null;
		ArrayList<Veiculo> veiculos = new ArrayList<>();
		
		try {
			ResultSet resultSet = null;
			preparedStatement = this.connectionDB.prepareStatement(query);
			preparedStatement.setString(1, placa);
			resultSet = preparedStatement.executeQuery();	
			while(resultSet.next()) {
				if(resultSet.getInt("capacidade_passageiros") != 0){
					carroModel.setMarca(resultSet.getString("marca"));
					carroModel.setModelo(resultSet.getString("modelo"));
					carroModel.setPlaca(resultSet.getString("placa"));
					carroModel.setAnofabricacao(resultSet.getInt("ano_fabricacao"));
					carroModel.setAnomodelo(resultSet.getInt("ano_modelo"));
					carroModel.setCapacidadePassageiros(resultSet.getInt("capacidade_passageiros"));
					carroModel.setQuantidadePortas(resultSet.getInt("quantidade_portas"));
					veiculo = (Veiculo) carroModel;
					veiculos.add(veiculo);
				}
				else {
					caminhaoModel.setMarca(resultSet.getString("marca"));
					caminhaoModel.setModelo(resultSet.getString("modelo"));
					caminhaoModel.setPlaca(resultSet.getString("placa"));
					caminhaoModel.setAnofabricacao(resultSet.getInt("ano_fabricacao"));
					caminhaoModel.setAnomodelo(resultSet.getInt("ano_modelo"));
					caminhaoModel.setCapacidadecarga(resultSet.getFloat("capacidade_carga"));
					caminhaoModel.setNumerosdeEixos(resultSet.getInt("numero_eixos"));
					veiculo = (Veiculo) caminhaoModel;
					veiculos.add(veiculo);
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
		return !veiculos.isEmpty() ? veiculos.get(0) : null;
	}
	
	public ArrayList<Veiculo> relatorio() {
		String query = "SELECT marca, modelo, placa, ano_fabricacao, ano_modelo, capacidade_passageiros, quantidade_portas, capacidade_carga, numero_eixos FROM veiculos";
		PreparedStatement preparedStatement = null;
		Carro carroModel = new Carro();
		Caminhao caminhaoModel = new Caminhao();
		Veiculo veiculo = null;
		ArrayList<Veiculo> veiculos = new ArrayList<>();
		
		try {
			ResultSet resultSet = null;
			preparedStatement = this.connectionDB.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();	
			while(resultSet.next()) {
				if(resultSet.getInt("capacidade_passageiros") != 0){
					carroModel.setMarca(resultSet.getString("marca"));
					carroModel.setModelo(resultSet.getString("modelo"));
					carroModel.setPlaca(resultSet.getString("placa"));
					carroModel.setAnofabricacao(resultSet.getInt("ano_fabricacao"));
					carroModel.setAnomodelo(resultSet.getInt("ano_modelo"));
					carroModel.setCapacidadePassageiros(resultSet.getInt("capacidade_passageiros"));
					carroModel.setQuantidadePortas(resultSet.getInt("quantidade_portas"));
					veiculo = (Veiculo) carroModel;
					veiculos.add(veiculo);
				}
				else {
					caminhaoModel.setMarca(resultSet.getString("marca"));
					caminhaoModel.setModelo(resultSet.getString("modelo"));
					caminhaoModel.setPlaca(resultSet.getString("placa"));
					caminhaoModel.setAnofabricacao(resultSet.getInt("ano_fabricacao"));
					caminhaoModel.setAnomodelo(resultSet.getInt("ano_modelo"));
					caminhaoModel.setCapacidadecarga(resultSet.getFloat("capacidade_carga"));
					caminhaoModel.setNumerosdeEixos(resultSet.getInt("numero_eixos"));
					veiculo = (Veiculo) caminhaoModel;
					veiculos.add(veiculo);
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
		return !veiculos.isEmpty() ? veiculos : null;
	}
}
