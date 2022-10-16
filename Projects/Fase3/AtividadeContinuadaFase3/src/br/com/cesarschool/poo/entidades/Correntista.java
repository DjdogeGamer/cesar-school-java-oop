package br.com.cesarschool.poo.entidades;

public class Correntista {
	private String cpf;
	private String nome;
	
	
	Correntista(String cpf, String nome) {
	    super();
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
