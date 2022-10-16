package br.com.cesarschool.poo.telas;

import java.util.Scanner;

import br.com.cesarschool.poo.entidades.StatusConta;
import br.com.cesarschool.poo.mediators.ContaMediator;

public class TelaConta {
	private ContaMediator contaMediator = new ContaMediator();
	private static final Scanner SC = new Scanner(System.in);
	
	public void executarTela() {
		while (true) {
			System.out.println();
			Imprimir.opcoes();
			int entrada = SC.nextInt();
			escolherEntrada(entrada);
		}
	}
	
	public void escolherEntrada(int entrada) {
		switch(entrada) {
			case 1:
				contaMediator.incluir();
				break;
			case 2:
				contaMediator.alterar();
				break;
			case 3:
				contaMediator.alterarStatus(StatusConta.ENCERRADA);
				break;
			case 4:
				contaMediator.alterarStatus(StatusConta.BLOQUEADA);
				break;
			case 5:
				contaMediator.alterarStatus(StatusConta.ATIVA);
				break;
			case 6:
				contaMediator.excluir();
				break;
			case 7:
				contaMediator.buscar();
				break;
			case 8:
				contaMediator.operacao(OperacoesTelaConta.CREDITAR);
				break;
			case 9:
				contaMediator.operacao(OperacoesTelaConta.DEBITAR);
				break;
			case 10:
				System.out.println("Saindo...");
				System.exit(0);
				break;
			default:
				System.out.println("Opcao invalida");
				break;
		}
	}	
	
}
