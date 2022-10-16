package br.com.cesarschool.poo.entidades;

import java.time.LocalDate;

import br.com.cesarschool.poo.telas.Escore;

public class Conta {
	//Erros de funcao
	
	private long numero; // Deve ser maior do que zero e é único para cada conta
	private Status status;
	private LocalDate dataAbertura; // Deve ser menor ou igual à data atual e maior do que a data atual -1 mes
	private double saldo = 0.0; // É inicializado com 0
	
	public Conta(long numero, Status status, LocalDate dataAbertura) {
		super();
		this.numero = numero;
		this.status = status;
		this.dataAbertura = dataAbertura;
	}
	
	// Getters and Setters
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public long getNumero() {
		return numero;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
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
	
	
	public Escore calcularEscore() {
		double valor = 0;
		if (status == Status.BLOQUEADA) {
			System.out.println("A conta está bloqueada");
			return Escore.INDISPONIVEL;
		}
		else if (status == Status.ENCERRADA) {
			valor = 0; 
		} else {
			LocalDate hoje = LocalDate.now();
			long tempoVida = hoje.toEpochDay() - dataAbertura.toEpochDay();
			valor = tempoVida*2.0 + saldo*3.0;
		}
		return selecionarEscore(valor);
	}
	
	public Escore selecionarEscore(double valor) {
		if (valor < 5800) {
			return Escore.BRONZE;
		}
		else if (valor >= 5800 && valor <= 13000) {
			return Escore.PRATA;
		}
		else if (valor >= 13001 && valor <= 39000) {
			return Escore.OURO;
		}
		return Escore.DIAMANTE;
	}
	
}
