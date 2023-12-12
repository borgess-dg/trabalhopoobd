package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import model.Caminhao;
import model.Carro;
import model.Veiculo;

import controller.VeiculoController;

public class Veiculos {
	
	public void menuVeiculos() {
		System.out.println("\t----- VEÍCULOS -----\n");
		System.out.println("1 - Cadastrar Veículo");
		System.out.println("2 - Editar Veículo");
		System.out.println("3 - Excluir Veículo");
		System.out.println("4 - Consultar Veículo");
		System.out.println("5 - Relatórios");
		System.out.println("6 - Sair");
	}
	
	public void incluir() {
		
		Scanner scanner = new Scanner(System.in);
		int tipo = 0;
		
		String marca, placa, modelo;
		int anoModelo, anoFabricacao;
		Carro carroModel = new Carro();
		Caminhao caminhaoModel = new Caminhao();
		VeiculoController veiculoController = new VeiculoController();
		
		System.out.println("----- CADASTRO DE VEÍCULOS -----");
		System.out.println("Qual o tipo de veículo deseja cadastrar?");
		System.out.println("1 - Carro");
		System.out.println("2 - Caminhão");
		tipo = scanner.nextInt();
		System.out.println("Marca:");
		scanner.nextLine();
		marca = scanner.nextLine();
		System.out.println("Modelo:");
		modelo = scanner.nextLine();
		System.out.println("Ano de Fabricação:");
		anoFabricacao = scanner.nextInt();
		System.out.println("Ano do Modelo:");
		anoModelo = scanner.nextInt();
		System.out.println("Placa:");
		scanner.nextLine();
		placa = scanner.nextLine();
		if(tipo == 1) {
			carroModel.setMarca(marca);
			carroModel.setModelo(modelo);
			carroModel.setAnofabricacao(anoFabricacao);
			carroModel.setPlaca(placa);
			carroModel.setAnomodelo(anoModelo);
			System.out.println("Capacidade de Passageiros:");
			carroModel.setCapacidadePassageiros(scanner.nextInt());
			System.out.println("Quantidade de Portas: ");
			carroModel.setQuantidadePortas(scanner.nextInt());
			veiculoController.incluir(carroModel);
		}
		else {
			caminhaoModel.setMarca(marca);
			caminhaoModel.setModelo(modelo);
			caminhaoModel.setAnofabricacao(anoFabricacao);
			caminhaoModel.setPlaca(placa);
			caminhaoModel.setAnomodelo(anoModelo);
			System.out.println("Capacidade de Carga:");
			caminhaoModel.setCapacidadecarga(scanner.nextFloat());
			System.out.println("Numero de Eixos:");
			caminhaoModel.setNumerosdeEixos(scanner.nextInt());
			veiculoController.incluir(caminhaoModel);
		}
	}
	
	public void excluir() {
		
		Scanner scanner = new Scanner(System.in);
		String placa;
		int opt;
		
		System.out.println("----- EXCLUSÃO DE VEÍCULO -----");
		System.out.println("Qual a placa do veículo buscado?");
		placa = scanner.nextLine();
		
		VeiculoController veiculoController = new VeiculoController();
		if(veiculoController.consulta(placa) != null)
			imprimeDadosDoVeiculo(veiculoController.consulta(placa));
		else
			System.out.println("O veículo buscado não existe!");
		
		System.out.println("Você deseja realmente excluir esse veículo?");
		opt = scanner.nextInt();
		if(opt == 1) {
			veiculoController.excluir(placa);
			System.out.println("Veículo Excluído com sucesso!");
		}
	}
	
	public void alterar() {
		Scanner scanner = new Scanner(System.in);
		String placa;
		int opt;
		VeiculoController veiculoController = new VeiculoController();
		
		System.out.println("----- EDIÇÃO DE VEÍCULO -----");
		
		if(veiculoController.relatorio() != null) {	
			System.out.println("Qual a placa do veículo buscado?");
			placa = scanner.nextLine();
			
			Veiculo veiculoParaEdicao = veiculoController.consulta(placa);
			Carro carroModel;
			Caminhao caminhaoModel;
			
			if(veiculoParaEdicao == null) {
				System.out.println("Esse veículo não existe!");
			}
			else {
				System.out.println("Editar Marca? (1 - Sim  |  2 - Não)");
				System.out.println("Marca Atual: " + veiculoParaEdicao.getMarca());
				opt = scanner.nextInt();
				if(opt == 1) {
					System.out.println("Digite o novo valor:");
					veiculoParaEdicao.setMarca(scanner.nextLine());
				}
				System.out.println("Editar Modelo? (1 - Sim  |  2 - Não)");
				System.out.println("Modelo Atual: " + veiculoParaEdicao.getModelo());
				opt = scanner.nextInt();
				if(opt == 1) {
					System.out.println("Digite o novo valor:");
					veiculoParaEdicao.setModelo(scanner.nextLine());
				}
				System.out.println("Editar Ano do Modelo? (1 - Sim  |  2 - Não)");
				System.out.println("Ano Atual: " + veiculoParaEdicao.getAnomodelo());
				opt = scanner.nextInt();
				if(opt == 1) {
					System.out.println("Digite o novo valor:");
					veiculoParaEdicao.setAnomodelo(scanner.nextInt());
				}
				System.out.println("Editar Ano de Fabricação? (1 - Sim  |  2 - Não)");
				System.out.println("Ano Atual: " + veiculoParaEdicao.getAnofabricacao());
				opt = scanner.nextInt();
				if(opt == 1) {
					System.out.println("Digite o novo valor:");
					veiculoParaEdicao.setAnofabricacao(scanner.nextInt());
				}
				if(veiculoParaEdicao instanceof Carro) {
					carroModel = (Carro) veiculoParaEdicao;
					System.out.println("Editar Capacidade de Passageiros? (1 - Sim  |  2 - Não)");
					System.out.println("Capacidade Atual: " + carroModel.getCapacidadePassageiros());
					opt = scanner.nextInt();
					if(opt == 1) {
						System.out.println("Digite o novo valor:");
						carroModel.setCapacidadePassageiros(scanner.nextInt());
					}
					System.out.println("Editar Quantidade de Portas? (1 - Sim  |  2 - Não)");
					System.out.println("Quantidade Atual: " + carroModel.getQuantidadePortas());
					opt = scanner.nextInt();
					if(opt == 1) {
						System.out.println("Digite o novo valor:");
						carroModel.setQuantidadePortas(scanner.nextInt());
					}
					veiculoParaEdicao = (Veiculo) carroModel;
					veiculoController.alterar(veiculoParaEdicao, placa);
				}
				else {
					caminhaoModel = (Caminhao) veiculoParaEdicao;
					System.out.println("Editar Capacidade de Carga? (1 - Sim  |  2 - Não)");
					System.out.println("Capacidade Atual: " + caminhaoModel.getCapacidadeCarga());
					opt = scanner.nextInt();
					if(opt == 1) {
						System.out.println("Digite o novo valor:");
						caminhaoModel.setCapacidadecarga(scanner.nextFloat());
					}
					System.out.println("Editar Número de Eixos? (1 - Sim  |  2 - Não)");
					System.out.println("Quantidade Atual: " + caminhaoModel.getNumerosdeEixos());
					opt = scanner.nextInt();
					if(opt == 1) {
						System.out.println("Digite o novo valor:");
						caminhaoModel.setNumerosdeEixos(scanner.nextInt());
					}
					veiculoParaEdicao = (Veiculo) caminhaoModel;
					veiculoController.alterar(veiculoParaEdicao, placa);
				}
				
			}
		}
		else {
			System.out.println("Você não possui um veículo cadastrado!");
		}
	}
	
	public void consultar() {
		
		Scanner scanner = new Scanner(System.in);
		String placa;
		VeiculoController veiculoController = new VeiculoController();
		
		System.out.println("----- CONSULTA VEÍCULO ----- ");
		if(veiculoController.relatorio() != null) {	
			System.out.println("Qual a placa do veículo buscado?");
			placa = scanner.nextLine();
			if(veiculoController.consulta(placa) != null) {
				imprimeDadosDoVeiculo(veiculoController.consulta(placa));
				System.out.println("Pressione ENTER para finalizar a conferência...");
				scanner.nextLine();
			}
			else {
				System.out.println("O veículo procurado não existe!");
			}
		}
		else {
			System.out.println("Não há veículos cadastrados!");
		}
	}
	
	public void relatorio() {
		
		VeiculoController veiculoController = new VeiculoController();
		Veiculo veiculoBase;
		ArrayList<Veiculo> veiculos = veiculoController.relatorio();
		
		System.out.println("----- VEÍCULOS CADASTRADOS -----");
		if(veiculos != null) {
			Iterator<Veiculo> veiculosIterator = veiculos.iterator();
			while(veiculosIterator.hasNext()) {
				veiculoBase = veiculosIterator.next();
				imprimeDadosDoVeiculo(veiculoBase);
			}
		}
		else {
			System.out.println("Você não possui veículos cadastrados!");
		}
		
	}
	
	public void imprimeDadosDoVeiculo(Veiculo veiculo) {
		Carro carroModel;
		Caminhao caminhaoModel;
		
		if(veiculo instanceof Carro) {
			carroModel = (Carro) veiculo;
			
			System.out.println("Marca: " + carroModel.getMarca());
			System.out.println("Modelo: " + carroModel.getModelo());
			System.out.println("Placa: " + carroModel.getPlaca());
			System.out.println("Ano do Modelo: " + carroModel.getAnomodelo());
			System.out.println("Ano de Fabricação: " + carroModel.getAnofabricacao());
			System.out.println("Capacidade de Passageiros: " + carroModel.getCapacidadePassageiros());
			System.out.println("Número de Portas: " + carroModel.getQuantidadePortas());
		}
		else {
			caminhaoModel = (Caminhao) veiculo;
			
			System.out.println("Marca: " + caminhaoModel.getMarca());
			System.out.println("Modelo: " + caminhaoModel.getModelo());
			System.out.println("Placa: " + caminhaoModel.getPlaca());
			System.out.println("Ano do Modelo: " + caminhaoModel.getAnomodelo());
			System.out.println("Ano de Fabricação: " + caminhaoModel.getAnofabricacao());
			System.out.println("Capacidade de Carga: " + caminhaoModel.getCapacidadeCarga());
			System.out.println("Número de Eixos: " + caminhaoModel.getNumerosdeEixos());
		}
	}
}
