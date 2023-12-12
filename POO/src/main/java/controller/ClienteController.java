package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import database.Database;
import model.Cliente;
import model.PessoaFisica;
import model.PessoaJuridica;

public class ClienteController {
	
	private Connection connectionDB;
	
	public ClienteController() {
		this.connectionDB = new Database().connect();
	}
	
	public void incluir(Cliente clienteParaRegistro) {
		String query = "INSERT INTO clientes(nome_razao, telefone, endereco, cpf_cnpj, data_nascimento, data_cadastro) VALUES (?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		PessoaFisica pessoaFisicaModel = null;
		PessoaJuridica pessoaJuridicaModel = null;
		
		try {
			preparedStatement = this.connectionDB.prepareStatement(query);
			
			preparedStatement.setString(1, clienteParaRegistro.getNome());
			preparedStatement.setString(2, clienteParaRegistro.getTelefone());
			preparedStatement.setString(3, clienteParaRegistro.getEndereco());
			
			if(clienteParaRegistro instanceof PessoaFisica) {
				
				pessoaFisicaModel = (PessoaFisica) clienteParaRegistro;
				Timestamp timestamp = Timestamp.valueOf(pessoaFisicaModel.getDataNascimento().atStartOfDay());
				
				preparedStatement.setString(4, pessoaFisicaModel.getCpf());
				preparedStatement.setTimestamp(5, timestamp);
				preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
			}
			else {
				pessoaJuridicaModel = (PessoaJuridica) clienteParaRegistro;
				preparedStatement.setString(4, pessoaJuridicaModel.getCnpj());
				preparedStatement.setNull(5, 0);
				preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
			}
			
			preparedStatement.execute();
			System.out.println("Cliente incluído com sucesso!");
		}
		catch (SQLException err) {
			System.out.println(err);
		}
	}
	public Cliente consulta(int codigoCliente, String documentoIdentificador) {
		String query = "SELECT nome_razao, telefone, endereco, cpf_cnpj, data_nascimento FROM clientes WHERE cliente_id = ? AND flag_excluido IS NULL";
		if(documentoIdentificador != null) {
			query = "SELECT nome_razao, telefone, endereco, cpf_cnpj, data_nascimento FROM clientes WHERE cpf_cnpj = ? AND flag_excluido IS NULL";
		}
		PreparedStatement preparedStatement = null;
		PessoaFisica pessoaFisicaModel = new PessoaFisica();
		PessoaJuridica pessoaJuridicaModel = new PessoaJuridica();
		Cliente cliente = null;
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		try {
			ResultSet resultSet = null;
			preparedStatement = this.connectionDB.prepareStatement(query);
			if(codigoCliente != - 1) {
				preparedStatement.setInt(1, codigoCliente);				
			}
			else {
				preparedStatement.setString(1, documentoIdentificador);
			}
			resultSet = preparedStatement.executeQuery();	
			while(resultSet.next()) {
				if(resultSet.getTimestamp("data_nascimento") != null){
					pessoaFisicaModel.setDataNascimento(resultSet.getTimestamp("data_nascimento").toLocalDateTime().toLocalDate());
					pessoaFisicaModel.setCpf(resultSet.getString("cpf_cnpj"));
					pessoaFisicaModel.setNome(resultSet.getString("nome_razao"));
					pessoaFisicaModel.setEndereco(resultSet.getString("endereco"));
					pessoaFisicaModel.setTelefone(resultSet.getString("telefone"));
					cliente = (Cliente) pessoaFisicaModel;
					clientes.add(cliente);
				}
				else {
					pessoaJuridicaModel.setCnpj(resultSet.getString("cpf_cnpj"));
					pessoaJuridicaModel.setNome(resultSet.getString("nome_razao"));
					pessoaJuridicaModel.setEndereco(resultSet.getString("endereco"));
					pessoaJuridicaModel.setTelefone(resultSet.getString("telefone"));
					cliente = (Cliente) pessoaJuridicaModel;
					clientes.add(cliente);
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
		return clientes.get(0);
	}
	
	public void excluir(int codigoCliente, String documentoIdentificador) {
		String query = "DELETE FROM clientes WHERE cliente_id = ?";
		if(documentoIdentificador != null) {
			query = "DELETE FROM clientes WHERE cpf_cnpj = '?'";
		}
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connectionDB.prepareStatement(query);
		if(codigoCliente != -1) {			
			preparedStatement.setInt(1, codigoCliente);
		}
		else {
			preparedStatement.setString(1, documentoIdentificador);
		}
			preparedStatement.execute();
			preparedStatement.close();
		}
		catch (SQLException error) {
			System.out.println("error");
		}
	}
	
	public ArrayList<Cliente> relatorio() {
		String query = "SELECT nome_razao, telefone, endereco, cpf_cnpj, data_nascimento FROM clientes WHERE flag_excluido IS NULL";
		PreparedStatement preparedStatement = null;
		PessoaFisica pessoaFisicaModel = new PessoaFisica();
		PessoaJuridica pessoaJuridicaModel = new PessoaJuridica();
		Cliente cliente = null;
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		try {
			ResultSet resultSet = null;
			preparedStatement = this.connectionDB.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();			
			while(resultSet.next()) {
				if(resultSet.getTimestamp("data_nascimento") != null){
					pessoaFisicaModel.setDataNascimento(resultSet.getTimestamp("data_nascimento").toLocalDateTime().toLocalDate());
					pessoaFisicaModel.setCpf(resultSet.getString("cpf_cnpj"));
					pessoaFisicaModel.setNome(resultSet.getString("nome_razao"));
					pessoaFisicaModel.setEndereco(resultSet.getString("endereco"));
					pessoaFisicaModel.setTelefone(resultSet.getString("telefone"));
					cliente = (Cliente) pessoaFisicaModel;
					clientes.add(cliente);
				}
				else {
					pessoaJuridicaModel.setCnpj(resultSet.getString("cpf_cnpj"));
					pessoaJuridicaModel.setNome(resultSet.getString("nome_razao"));
					pessoaJuridicaModel.setEndereco(resultSet.getString("endereco"));
					pessoaJuridicaModel.setTelefone(resultSet.getString("telefone"));
					cliente = (Cliente) pessoaJuridicaModel;
					clientes.add(cliente);
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
		return clientes;
	}
}
