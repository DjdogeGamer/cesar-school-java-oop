package br.com.cesarschool.poo.mediators;

public enum OperacoesAlterar {
	ALTERAR_DATA(1, "DATA"), 
	ENCERRAR(2, "ENCERRAR"), 
	BLOQUEAR(3, "BLOQUEAR"),
	ATIVAR(4, "ATIVAR"),
	CREDITAR(5, "CREDITAR"),
	DEBITAR(6, "DEBITAR");
	
	private OperacoesAlterar(int codigo, String descricao) {
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
	
	public static OperacoesAlterar obterPorCodigo(int codigo) {
		for (OperacoesAlterar operacao : OperacoesAlterar.values()) {
			if (operacao.getCodigo() == codigo) {
				return operacao;
			}
		}
	return null;
	}
}