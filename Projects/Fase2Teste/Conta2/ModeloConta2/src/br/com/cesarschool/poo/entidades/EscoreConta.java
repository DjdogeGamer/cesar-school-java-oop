package br.com.cesarschool.poo.entidades;

public enum EscoreConta {
	INDISPONIVEL(1, "Indisponivel"), 
	BRONZE(2, "Bronze"), 
	PRATA(3, "Prata"), 
	OURO(4, "Ouro"), 
	DIAMANTE(5, "Diamante");
	
	private EscoreConta(int codigo, String descricao) {
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
	
	public static EscoreConta obterPorCodigo(int codigo) {
		for (EscoreConta escoreConta : EscoreConta.values()) {
			if (escoreConta.getCodigo() == codigo) {
				return escoreConta;
			}
		}
	return null;
	}
}
