package br.com.cesarschool.poo.entidades;

public enum StatusConta {
	ATIVA(1, "Ativa"), 
	BLOQUEADA(2, "Bloqueada"), 
	ENCERRADA(3, "Encerrada");
	
	private StatusConta(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	private int codigo;
	private String descricao;
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static StatusConta obterPorCodigo(int codigo) {
		for (StatusConta statusConta : StatusConta.values()) {
			if (statusConta.getCodigo() == codigo) {
				return statusConta;
			}
		}
	return null;
	}
}
