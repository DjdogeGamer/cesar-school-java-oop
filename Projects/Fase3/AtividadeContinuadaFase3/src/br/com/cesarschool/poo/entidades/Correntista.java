package br.com.cesarschool.poo.entidades;

import java.time.LocalDate;

public class Correntista extends Conta{
	private String cpf;
	private String nome;
	
	
	public Correntista(
			String cpf,
			String nome,
			long numero,
			Status status,
			LocalDate dataAbertura,
			TipoConta tipoConta
			) {
	    super(numero, status, dataAbertura, tipoConta);
	    this.cpf = cpf;
	    this.nome = nome;
	
	}
	
	public String getCpf() {
	    return cpf;
	}
	
	public String getNome() {
	    return nome;
	}
	
	public void setCpf(String cpf) {
	    this.cpf = cpf;
	}
	
	public void setNome(String nome) {
	    this.nome = nome;
	}
}
