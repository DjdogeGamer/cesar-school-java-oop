package br.com.cesarschool.poo.mediators;

import java.time.LocalDate;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.StatusConta;
import br.com.cesarschool.poo.repositorios.RepositorioConta;
import br.com.cesarschool.poo.telas.Imprimir;
import br.com.cesarschool.poo.telas.OperacoesTelaConta;
import br.com.cesarschool.poo.telas.TratarEntrada;

public class ContaMediator {
	
	private static final int SUCESSO = 0;
	private static final int VALOR_NEGATIVO = -1;
	private static final int CONTA_ENCERRADA = -2;
	private static final int CONTA_BLOQUEADA = -3;
	
	private RepositorioConta repositorioConta = RepositorioConta.getInstancia();
	
	public void incluir() {
		Conta novaConta = pegarNovaConta();
		if (novaConta == null) {
			return;
		}
		repositorioConta.incluir(novaConta);
	}
	
	public Conta pegarNovaConta() {
		long posicao = TratarEntrada.pegarNumero();
		if (repositorioConta.temNumero(posicao)) {
			return null;
		}
		StatusConta statusSelecionado = TratarEntrada.pegarStatus();
		LocalDate data = TratarEntrada.pegarData();
		return new Conta(posicao, statusSelecionado, data);
	}
	
	public void alterar() {
		Conta conta = buscar();
		if (conta != null) {
			System.out.println("Conta achada! Verifique os dados acima e insira a nova data");
			int resultado = repositorioConta.alterar(conta.getNumero(), TratarEntrada.pegarData());
			if (resultado == 0) {
				System.out.println("Conta alterada com sucesso");
			}
		}
		Imprimir.conta(conta);
	}
	
	public void excluir() {
		long numero = TratarEntrada.pegarNumero();
		if (numero > 0) {
			int resultado = repositorioConta.excluir(numero);
			if (resultado == 0) {
				System.out.println("Conta excluída com sucesso");
			}
		}
	}
	
	public Conta buscar() {
		long numero = TratarEntrada.pegarNumero();
		Conta aux = repositorioConta.retornarConta(numero);
		if (aux == null) {
			return null;
		}
		Imprimir.conta(aux);
		return aux;
	}
	
	public void operacao(OperacoesTelaConta operacao) {
		long numero = TratarEntrada.pegarNumero();
		Conta conta = repositorioConta.retornarConta(numero);
		if (conta == null) {
			return;
		}
		double valor = TratarEntrada.pegarValor();
		if (operacao == OperacoesTelaConta.CREDITAR) {
			creditar(conta, valor);
		} else if (operacao == OperacoesTelaConta.DEBITAR) {
			debitar(conta, valor);
		} else {
			System.out.println("Operacao desconhecida");
		}
		
		Imprimir.conta(conta);
	}
	
	public void alterarStatus(StatusConta status) {
		long numero = TratarEntrada.pegarNumero();
		Conta aux = repositorioConta.retornarConta(numero);
		if (aux == null) {
			return;
		}
		if (status == StatusConta.ENCERRADA) {
			encerrar(aux);
		} else if (status == StatusConta.BLOQUEADA) {
			bloquear(aux);
		} else if (status == StatusConta.ATIVA) {
			desbloquear(aux);
		} else {
			System.out.println("Status desconhecido");
		}
		
		Imprimir.conta(aux);
	}
	
	public void encerrar(Conta conta) {
		if (conta.getStatus() == StatusConta.ENCERRADA) {
			System.out.println("A conta ja esta encerrada");
		}
		else if (conta.getStatus() != StatusConta.ENCERRADA) {
			StatusConta anterior = conta.getStatus();
			conta.setStatus(StatusConta.ENCERRADA);
			printarMudanca(anterior, conta);
		}
	}
	
	public void bloquear(Conta conta) {
		if (conta.getStatus() == StatusConta.BLOQUEADA) {
			System.out.println("A conta ja esta bloqueada");
		}
		else if (conta.getStatus() == StatusConta.ENCERRADA) {
			System.out.println("A conta foi encerrada, abortando alteracao...");
		} else {
			StatusConta anterior = conta.getStatus();
			conta.setStatus(StatusConta.BLOQUEADA);
			printarMudanca(anterior, conta);
		}
	}
	
	public void desbloquear(Conta conta) {
		if (conta.getStatus() == StatusConta.ATIVA) {
			System.out.println("A conta ja esta ativa");
		}
		else if (conta.getStatus() == StatusConta.ENCERRADA) {
			System.out.println("A conta foi encerrada, abortando alteracao...");
		} else {
			StatusConta anterior = conta.getStatus();
			conta.setStatus(StatusConta.ATIVA);
			printarMudanca(anterior, conta);
		}
	}
	
	public int creditar(Conta conta, double valorCreditado) {
		if (valorCreditado < 0.0) {
			System.out.println("O valor informado de " + valorCreditado + " é negativo. Abortando operação...");
			return VALOR_NEGATIVO;
		}
		else if (conta.getStatus() == StatusConta.ENCERRADA) {
			System.out.println("A conta " + conta.getNumero() + " foi encerrada. Abortando operação...");
			return CONTA_ENCERRADA;
		}
		double antigoSaldo = conta.getSaldo();
		double novoSaldo = antigoSaldo += valorCreditado;
		conta.setSaldo(novoSaldo);
		return SUCESSO;
	}
	
	public int debitar(Conta conta, double valorDebitado) {
		if (valorDebitado < 0.0) {
			System.out.println("O valor informado de " + valorDebitado + " é negativo. Abortando operação...");
			return VALOR_NEGATIVO;
		}
		else if (conta.getStatus() == StatusConta.BLOQUEADA) {
			System.out.println("A conta " + conta.getNumero() + " foi bloqueada. Abortando operação...");
			return CONTA_BLOQUEADA;
		}
		double antigoSaldo = conta.getSaldo();
		double novoSaldo = antigoSaldo -= valorDebitado;
		conta.setSaldo(novoSaldo);
		return SUCESSO;
	}

	
	public void printarMudanca(StatusConta anterior, Conta conta) {
		System.out.println("O status da conta " + conta.getNumero() + " foi alterado");
		System.out.println("Anterior: " + anterior);
		System.out.println("Novo: " + conta.getStatus());
		System.out.println();
	}
}
