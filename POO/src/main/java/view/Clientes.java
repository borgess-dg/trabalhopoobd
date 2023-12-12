package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import model.Cliente;
import model.PessoaFisica;
import model.PessoaJuridica;
import controller.ClienteController;

import middlewares.ValidaRegistrosDeClientes;

public class Clientes {

	public void menuClientes() {
		System.out.println("\u001B[44m" + "\t----- CLIENTES -----\n" + "\u001B[37m");
		System.out.println("1 - Cadastrar Cliente");
		System.out.println("2 - Editar Cliente");
		System.out.println("3 - Consultar Cliente");
		System.out.println("4 - Remover Cliente");
		System.out.println("5 - Relatórios");
		System.out.println("6 - Sair");
	}
	
	public void incluir() {
		
		ValidaRegistrosDeClientes verificadorDeRegistro = new ValidaRegistrosDeClientes();
		
		Cliente clienteModel = new Cliente();
		PessoaFisica pessoaFisicaModel = new PessoaFisica();
		PessoaJuridica pessoaJuridicaModel = new PessoaJuridica();
		String identificadorCliente, tipoCliente;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\t----- CADASTRO DE CLIENTES -----\n");
		System.out.println("Nome/Razão Social: ");
		clienteModel.setNome(scanner.nextLine());
		System.out.println("Endereço: ");
		clienteModel.setEndereco(scanner.nextLine());
		System.out.println("Telefone: ");
		clienteModel.setTelefone(scanner.nextLine());
		
		
		System.out.println("CPF/CNPJ: ");
		identificadorCliente = scanner.nextLine();
		System.out.println(verificadorDeRegistro.cpfValido(identificadorCliente));
		System.out.println(verificadorDeRegistro.cnpjValido(identificadorCliente));
		
		if(verificadorDeRegistro.cpfValido(identificadorCliente)) {
			pessoaFisicaModel.setNome(clienteModel.getNome());
			pessoaFisicaModel.setEndereco(clienteModel.getEndereco());
			pessoaFisicaModel.setTelefone(clienteModel.getTelefone());
			pessoaFisicaModel.setCpf(identificadorCliente);
			System.out.println("Data de Nascimento: ");
			pessoaFisicaModel.setDataNascimento(verificadorDeRegistro.dataStringParaLocalDate(scanner.nextLine()));
			ClienteController clienteController = new ClienteController();
			clienteController.incluir((Cliente) pessoaFisicaModel);
		}
		else{
			pessoaJuridicaModel.setNome(clienteModel.getNome());
			pessoaJuridicaModel.setEndereco(clienteModel.getEndereco());
			pessoaJuridicaModel.setTelefone(clienteModel.getTelefone());
			pessoaJuridicaModel.setCnpj(identificadorCliente);
			ClienteController clienteController = new ClienteController();
			clienteController.incluir((Cliente) pessoaJuridicaModel);
		}
		
	}
	
	public void alterar() {
		System.out.println("----- EDIÇÃO DE CLIENTE -----");
		
		System.out.println("Deseja Editar o Nome? (1 - Sim  |  2 - Não)");
		System.out.println("Nome Atual: ");
		System.out.println("Deseja Editar o Telefone? (1 - Sim  |  2 - Não)");
		System.out.println("Telefone Atual: ");
		System.out.println("Deseja Editar o CPF/CNPJ? (1 - Sim  |  2 - Não)");
		System.out.println("CPF/CNPJ atual: ");
		System.out.println("Deseja Editar o Endereço? (1 - Sim  |  2 - Não)");
		System.out.println("Endereço atual: ");
		System.out.println("Deseja Editar a Data de Nascimento?");
		System.out.println("Data atual:");
	}
	
	public void excluir() {
		Scanner scanner = new Scanner(System.in);
		int opt, codigo = 0;
		String documentoIdentificador = null;
		System.out.println("----- EXCLUSÃO DE CADASTRO -----");
		System.out.println("Selecione o tipo de pesquisa:\n");
		System.out.println("1 - Pesquisa Por Código do Cliente");
		System.out.println("2 - Pesquisa Por CPF/CNPJ do Cliente");
		opt = scanner.nextInt();
		ClienteController clienteController = new ClienteController();
		if(opt == 1) {
			System.out.println("Código do Cliente: ");
			codigo = scanner.nextInt();
			imprimeDadosDoCliente(clienteController.consulta(codigo, null));
		}
		else {
			System.out.println("CPF/CNPJ do Cliente: ");
			documentoIdentificador = scanner.nextLine();
			imprimeDadosDoCliente(clienteController.consulta(-1, documentoIdentificador));
		}
		
		System.out.println("Você realmente deseja excluir esse cliente?");
		opt = scanner.nextInt();
		if(opt == 1) {
			if(documentoIdentificador == null) {				
				clienteController.excluir(codigo, null);
			}
			else {
				clienteController.excluir(-1, documentoIdentificador);
			}
			System.out.println("Cliente Excluído!");
		}
	}
	
	public void consultar() {
		Scanner scanner = new Scanner(System.in);
		ClienteController clienteController = new ClienteController();
		Cliente clienteBase = null;
		int opt, codigo = 0;
		String documentoIdentificador = "";
		System.out.println("----- ALTERAÇÃO DE CADASTRO -----");
		System.out.println("Selecione o tipo de pesquisa:\n");
		System.out.println("1 - Pesquisa Por Código do Cliente");
		System.out.println("2 - Pesquisa Por CPF/CNPJ do Cliente");
		opt = scanner.nextInt();
		if(opt == 1) {
			System.out.println("Código do Cliente: ");
			codigo = scanner.nextInt();
			clienteBase = clienteController.consulta(codigo, null);
			imprimeDadosDoCliente(clienteBase);
		}
		else if(opt == 2) {
			System.out.println("Código do Cliente: ");
			scanner.nextLine();
			documentoIdentificador = scanner.nextLine();
			clienteBase = clienteController.consulta(-1, documentoIdentificador);
			
		}
		
		if(clienteBase == null) {
			System.out.println("Esse cliente não existe nos cadastros!");
		}
	}
	
	public void relatorio() {
		System.out.println("----- CLIENTES CADASTRADOS -----");
		ClienteController clienteController = new ClienteController();
		Cliente clienteBase;
		ArrayList<Cliente> clientes = clienteController.relatorio();
		
		
		if(!clientes.isEmpty()) {
			Iterator<Cliente> clientesIterator = clientes.iterator();
			while(clientesIterator.hasNext()) {
				clienteBase = clientesIterator.next();
				imprimeDadosDoCliente(clienteBase);
			}
		}
		else {
			System.out.println("Não há clientes cadastrados!");
		}
		
	}
	
	public void imprimeDadosDoCliente(Cliente cliente) {
		
		PessoaJuridica pessoaJuridicaModel = null;
		PessoaFisica pessoaFisicaModel = null;
		
		if(cliente instanceof PessoaFisica) {
			pessoaFisicaModel = (PessoaFisica) cliente;
			System.out.println("Nome: " + pessoaFisicaModel.getNome());
			System.out.println("CPF: " + pessoaFisicaModel.getCpf());
			System.out.println("Telefone: " + pessoaFisicaModel.getTelefone());
			System.out.println("Endereço: " + pessoaFisicaModel.getEndereco());
			System.out.println("Data de Nascimento: " + pessoaFisicaModel.getDataNascimento());
		}
		else {
			pessoaJuridicaModel = (PessoaJuridica) cliente;
			System.out.println("Nome: " + pessoaJuridicaModel.getNome());
			System.out.println("CNPJ: " + pessoaJuridicaModel.getCnpj());
			System.out.println("Telefone: " + pessoaJuridicaModel.getTelefone());
			System.out.println("Endereço: " + pessoaJuridicaModel.getEndereco());
		}
	}
}
