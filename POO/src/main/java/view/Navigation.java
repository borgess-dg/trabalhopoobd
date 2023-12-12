package view;

import java.util.Scanner;

public class Navigation {
	private Telas telaAtual = Telas.MENU;
	private Telas telaAnterior = Telas.MENU;
	
	private Agenda agenda;
	private Menu menu;
	private Veiculos veiculos;
	private Clientes clientes;
	private Caixa caixa;
	
	public Navigation() {
		this.agenda = new Agenda();
		this.menu = new Menu();
		this.veiculos = new Veiculos();
		this.clientes = new Clientes();
		this.caixa = new Caixa();
	}
	
	public void navigate(){
		while(telaAtual != Telas.SAIR) {
			switch(this.telaAtual) {
			case MENU:
				this.menu.mainMenu();
				getScreen();
				break;
			case AGENDA:
				this.agenda.menuAgenda();
				getScreen();
				break;
			case CAIXA:
				this.caixa.menuCaixa();
				getScreen();
				break;
			case VEICULOS:
				this.veiculos.menuVeiculos();
				getScreen();
				break;
			case CADASTROVEICULO:
				this.veiculos.incluir();
				getScreen();
				break;
			case EDICAOVEICULO:
				this.veiculos.alterar();
				getScreen();
				break;	
			case EXCLUSAOVEICULO:
				this.veiculos.excluir();
				getScreen();
				break;
			case CONSULTAVEICULO:
				this.veiculos.consultar();
				getScreen();
				break;
			case RELATORIOVEICULO:
				this.veiculos.relatorio();
			case CLIENTES:
				this.clientes.menuClientes();
				getScreen();
				break;
			case CADASTROCLIENTE:
				this.clientes.incluir();
				getScreen();
				break;
			case EDICAOCLIENTE:
				this.clientes.alterar();
				getScreen();
				break;
			case EXCLUSAOCLIENTE:
				this.clientes.excluir();
				getScreen();
				break;
			case CONSULTACLIENTE:
				this.clientes.consultar();
				getScreen();
				break;
			case RELATORIOCLIENTE:
				this.clientes.relatorio();
				getScreen();
				break;
			default:
				this.telaAtual = Telas.SAIR;
				break;
			}
		}
	}
	
	public void getScreen() {
		Scanner scanner = new Scanner(System.in);
		this.telaAnterior = this.telaAtual;
		
		switch(this.telaAnterior) {
			case MENU:
				this.telaAtual = Telas.values()[scanner.nextInt()];
				break;
			case AGENDA:
				this.telaAtual = Telas.values()[scanner.nextInt() + 7];
				break;
			case CAIXA:
				this.telaAtual = Telas.values()[scanner.nextInt() + 12];
				break;
			case VEICULOS:
				this.telaAtual = Telas.values()[scanner.nextInt() + 14];
				break;
			case CLIENTES:
				this.telaAtual = Telas.values()[scanner.nextInt() + 19];
				break;
			default:
				this.telaAtual = Telas.MENU;
				break;
		}
	}
}
