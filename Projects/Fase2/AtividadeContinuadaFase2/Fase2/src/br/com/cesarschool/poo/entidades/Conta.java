package br.com.cesarschool.poo.entidades;

import java.time.LocalDate;

public class Conta {
	//Erros de funcao
	private static final int SUCESSO = 0;
	private static final int VALOR_NEGATIVO = -1;
	private static final int CONTA_ENCERRADA = -2;
	private static final int CONTA_BLOQUEADA = -3;
	
	private long numero; // Deve ser maior do que zero e é único para cada conta
	private StatusConta status;
	private LocalDate dataAbertura; // Deve ser menor ou igual à data atual e maior do que a data atual -1 mes
	private double saldo = 0.0; // É inicializado com 0
	
	public Conta(long numero, StatusConta status, LocalDate dataAbertura) {
		super();
		this.numero = numero;
		this.status = status;
		this.dataAbertura = dataAbertura;
	}
	
	// Getters and Setters
	public StatusConta getStatus() {
		return status;
	}
	public void setStatus(StatusConta status) {
		this.status = status;
	}
	public long getNumero() {
		return numero;
	}
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public double getSaldo() {
		return saldo;
	}
	
	public int creditar(double valorCreditado) {
		if (valorCreditado < 0.0) {
			System.out.println("O valor informado de " + valorCreditado + " é negativo. Abortando operação...");
			return VALOR_NEGATIVO;
		}
		else if (status == StatusConta.ENCERRADA) {
			System.out.println("A conta " + numero + " foi encerrada. Abortando operação...");
			return CONTA_ENCERRADA;
		}
		saldo += valorCreditado;
		return SUCESSO;
	}
	
	public int debitar(double valorDebitado) {
		if (valorDebitado < 0.0) {
			System.out.println("O valor informado de " + valorDebitado + " é negativo. Abortando operação...");
			return VALOR_NEGATIVO;
		}
		else if (status == StatusConta.BLOQUEADA) {
			System.out.println("A conta " + numero + " foi bloqueada. Abortando operação...");
			return CONTA_BLOQUEADA;
		}
		saldo -= valorDebitado;
		return SUCESSO;
	}

	public EscoreConta calcularEscore() {
		double valor = 0;
		if (status == StatusConta.BLOQUEADA) {
			System.out.println("A conta está bloqueada");
			return EscoreConta.INDISPONIVEL;
		}
		else if (status == StatusConta.ENCERRADA) {
			valor = 0; 
		} else {
			LocalDate hoje = LocalDate.now();
			long tempoVida = hoje.toEpochDay() - dataAbertura.toEpochDay();
			valor = tempoVida*2.0 + saldo*3.0;
		}
		return selecionarEscore(valor);
	}
	
	public EscoreConta selecionarEscore(double valor) {
		if (valor < 5800) {
			return EscoreConta.BRONZE;
		}
		else if (valor >= 5800 && valor <= 13000) {
			return EscoreConta.PRATA;
		}
		else if (valor >= 13001 && valor <= 39000) {
			return EscoreConta.OURO;
		}
		return EscoreConta.DIAMANTE;
	}
	
}
