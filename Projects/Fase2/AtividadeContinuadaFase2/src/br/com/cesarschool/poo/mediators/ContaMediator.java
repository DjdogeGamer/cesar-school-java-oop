package br.com.cesarschool.poo.mediators;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.Status;
import br.com.cesarschool.poo.repositorios.RepositorioConta;

public class ContaMediator {
	
	//private RepositorioConta repositorio = RepositorioConta.getInstance();

	private static final int SUCESSO = 0;
	private static final int VALOR_NEGATIVO = -1;
	private static final int CONTA_ENCERRADA = -2;
	private static final int CONTA_BLOQUEADA = -3;
	
	
	public static int creditar(double valorCreditado, Conta conta) {
		if (valorCreditado < 0.0) {
			System.out.println("O valor informado de " + valorCreditado + " é negativo. Abortando operação...");
			return VALOR_NEGATIVO;
		}
		
		else if (conta.getStatus() == Status.ENCERRADA) {
			System.out.println("A conta " + conta.getNumero() + " foi encerrada. Abortando operação...");
			return CONTA_ENCERRADA;
		}
		double saldo= valorCreditado + conta.getSaldo();
		conta.setSaldo(saldo);
		return SUCESSO;
	}
	
	public static int debitar(double valorDebitado, Conta conta) {
		if (valorDebitado < 0.0) {
			System.out.println("O valor informado de " + valorDebitado + " é negativo. Abortando operação...");
			return VALOR_NEGATIVO;
		}
		else if (conta.getStatus() == Status.BLOQUEADA) {
			System.out.println("A conta " + conta.getNumero() + " foi bloqueada. Abortando operação...");
			return CONTA_BLOQUEADA;
		}
		double saldo = conta.getSaldo() - valorDebitado;
		conta.setSaldo(saldo);
		return SUCESSO;
	}
	

	
}
