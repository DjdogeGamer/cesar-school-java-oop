package br.com.cesarschool.poo.entidades;

public enum Status {
	ATIVA(1, "Ativa"), 
	ENCERRADA(2, "Encerrada"),
	BLOQUEADA(3, "Bloqueada");
	
	private int indice;
	private String classificacao;

	Status(int indice, String classificacao) {
		this.indice = indice;
		this.classificacao = classificacao;
	}
}
