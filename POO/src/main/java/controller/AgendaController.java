package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import database.Database;
import model.Cliente;
import model.Locacao;
import model.PessoaFisica;
import model.PessoaJuridica;

public class AgendaController {
	
	private Connection connectionDB;
	
	public AgendaController() {
		this.connectionDB = new Database().connect();
	}
	
	public void agendar(Locacao locacao) {
		String queryLocacao = "INSERT INTO locacoes(data_inicio, data_prevista_devolucao, cliente_id, preco, multa, status) VALUES(?,?,?,?,?,?);";
		String queryVeiculosLocacao = "INSERT INTO veiculos_da_locacao(veiculo_id, locacao_id) VALUES(?,?)";
		PreparedStatement preparedStatementLocacao = null;
		PreparedStatement preparedStatementVeiculosLocacao = null;
		
		try {
			preparedStatementLocacao = this.connectionDB.prepareStatement(queryLocacao);
			preparedStatementVeiculosLocacao = this.connectionDB.prepareStatement(queryVeiculosLocacao);
			
			preparedStatementLocacao.setTimestamp(1, Timestamp.valueOf(locacao.getDataInicio().atStartOfDay()));
			preparedStatementLocacao.setTimestamp(2, Timestamp.valueOf(locacao.getDataPrevistaDevolucao().atStartOfDay()));
			preparedStatementLocacao.setInt(3, getClienteId(locacao.getCliente()));
			preparedStatementLocacao.setDouble(4, locacao.getPreco());
			preparedStatementLocacao.setDouble(5, locacao.getMulta());
			preparedStatementLocacao.setInt(6, 0);
			
			preparedStatementLocacao.execute();
		}
		catch(SQLException error) {
			System.out.println(error);
		}
				
	}
	
	public int getClienteId(Cliente cliente) {
		String query = "SELECT cliente_id FROM cliente WHERE cpf_cnpj = ?";
		PreparedStatement preparedStatement = null;
		PessoaFisica pessoaFisicaModel = null;
		PessoaJuridica pessoaJuridicaModel = null;
		ResultSet resultSet = null;
		int codigo = 0;
		
		try {
			preparedStatement = this.connectionDB.prepareStatement(query);
			
			if(cliente instanceof PessoaFisica) {
				pessoaFisicaModel = (PessoaFisica) cliente;
				preparedStatement.setString(1, pessoaFisicaModel.getCpf());
				resultSet = preparedStatement.executeQuery();
				
			}
			
			else {
				pessoaJuridicaModel = (PessoaJuridica) cliente;
				preparedStatement.setString(1, pessoaJuridicaModel.getCnpj());
				resultSet = preparedStatement.executeQuery();
			}
			
			while(resultSet.next()) {
				codigo = resultSet.getInt("cliente_id");
			}
			
		}
		catch (SQLException error) {
			System.out.println(error);
		}
		return codigo;
	}
}
