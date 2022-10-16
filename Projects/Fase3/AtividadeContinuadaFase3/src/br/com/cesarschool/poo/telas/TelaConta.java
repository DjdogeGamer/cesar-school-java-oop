package br.com.cesarschool.poo.telas;

import java.time.LocalDate;
import java.util.Scanner;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.Status;
import br.com.cesarschool.poo.mediators.ContaMediator;
import br.com.cesarschool.poo.mediators.Operacoes;
import br.com.cesarschool.poo.mediators.TratarEntrada;
import br.com.cesarschool.poo.repositorios.RepositorioConta;

public class TelaConta {
	private RepositorioConta repositorio = RepositorioConta.getInstance();
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
				incluir();
				break;
			case 2:
				alterar();
				break;
			case 3:
				alterarStatus(Status.ENCERRADA);
				break;
			case 4:
				alterarStatus(Status.BLOQUEADA);
				break;
			case 5:
				alterarStatus(Status.ATIVA);
				break;
			case 6:
				excluir();
				break;
			case 7:
				buscar();
				break;
			case 8:
				operacao(Operacoes.CREDITAR);
				break;
			case 9:
				operacao(Operacoes.DEBITAR);
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
	
	public void incluir() {
		Conta novaConta = pegarNovaConta();
		if (novaConta == null) {
			return;
		}
		repositorio.incluir(novaConta);
	}
	
	public Conta pegarNovaConta() {
		long posicao = TratarEntrada.pegarNumero();
		if (repositorio.temNumero(posicao)) {
			return null;
		}
		Status statusSelecionado = TratarEntrada.pegarStatus();
		LocalDate data = TratarEntrada.pegarData();
		return new Conta(posicao, statusSelecionado, data);
	}
	
	public void alterar() {
		Conta conta = buscar();
		if (conta != null) {
			System.out.println("Conta achada! Verifique os dados acima e insira a nova data");
			int resultado = repositorio.alterar(conta.getNumero(), TratarEntrada.pegarData());
			if (resultado == 0) {
				System.out.println("Conta alterada com sucesso");
			}
		}
		Imprimir.conta(conta);
	}
	
	public void excluir() {
		long numero = TratarEntrada.pegarNumero();
		if (numero > 0) {
			int resultado = repositorio.excluir(numero);
			if (resultado == 0) {
				System.out.println("Conta excluída com sucesso");
			}
		}
	}
	
	public Conta buscar() {
		long numero = TratarEntrada.pegarNumero();
		Conta aux = repositorio.retornarConta(numero);
		if (aux == null) {
			return null;
		}
		Imprimir.conta(aux);
		return aux;
	}
	
	public void operacao(Operacoes operacao) {
		long numero = TratarEntrada.pegarNumero();
		Conta aux = repositorio.retornarConta(numero);
		if (aux == null) {
			return;
		}
		double valor = TratarEntrada.pegarValor();
		if (operacao == Operacoes.CREDITAR) {
			ContaMediator.creditar(valor, aux);
		} else if (operacao == Operacoes.DEBITAR) {
			ContaMediator.debitar(valor, aux);
		} else {
			System.out.println("Operacao desconhecida");
		}
		
		Imprimir.conta(aux);
	}
	
	public void creditar(double valor, Conta conta) {
		if (ContaMediator.creditar(valor, conta) == 0) {
			System.out.println("Novo saldo: " + conta.getSaldo());
		
		}
	}
	
	public void debitar(Conta conta, double valor) {
		if (ContaMediator.debitar(valor, conta) == 0) {
			System.out.println("Novo saldo: " + conta.getSaldo());
		}
	}
	
	public void alterarStatus(Status status) {
		long numero = TratarEntrada.pegarNumero();
		Conta aux = repositorio.retornarConta(numero);
		if (aux == null) {
			return;
		}
		if (status == Status.ENCERRADA) {
			encerrar(aux);
		} else if (status == Status.BLOQUEADA) {
			bloquear(aux);
		} else if (status == Status.ATIVA) {
			desbloquear(aux);
		} else {
			System.out.println("Status desconhecido");
		}
		
		Imprimir.conta(aux);
	}
	
	public void encerrar(Conta conta) {
		if (conta.getStatus() == Status.ENCERRADA) {
			System.out.println("A conta ja esta encerrada");
		}
		else if (conta.getStatus() != Status.ENCERRADA) {
			Status anterior = conta.getStatus();
			conta.setStatus(Status.ENCERRADA);
			printarMudanca(anterior, conta);
		}
	}
	
	public void bloquear(Conta conta) {
		if (conta.getStatus() == Status.BLOQUEADA) {
			System.out.println("A conta ja esta bloqueada");
		}
		else if (conta.getStatus() == Status.ENCERRADA) {
			System.out.println("A conta foi encerrada, abortando alteracao...");
		} else {
			Status anterior = conta.getStatus();
			conta.setStatus(Status.BLOQUEADA);
			printarMudanca(anterior, conta);
		}
	}
	
	public void desbloquear(Conta conta) {
		if (conta.getStatus() == Status.ATIVA) {
			System.out.println("A conta ja esta ativa");
		}
		else if (conta.getStatus() == Status.ENCERRADA) {
			System.out.println("A conta foi encerrada, abortando alteracao...");
		} else {
			Status anterior = conta.getStatus();
			conta.setStatus(Status.ATIVA);
			printarMudanca(anterior, conta);
		}
	}
	
	public void printarMudanca(Status anterior, Conta conta) {
		System.out.println("O status da conta " + conta.getNumero() + " foi alterado");
		System.out.println("Anterior: " + anterior);
		System.out.println("Novo: " + conta.getStatus());
		System.out.println();
	}
	
	
}
