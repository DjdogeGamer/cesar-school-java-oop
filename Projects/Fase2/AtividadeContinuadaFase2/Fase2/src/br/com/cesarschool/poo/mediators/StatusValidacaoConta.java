package br.com.cesarschool.poo.mediators;

public class StatusValidacaoConta {
	
	private int[] codigosErros;
	private String[] mensagensErros;
	private boolean valido;
	
	public StatusValidacaoConta(int[] codigosErros, String[] mensagensErros, boolean valido) {
		super();
		this.codigosErros = codigosErros;
		this.mensagensErros = mensagensErros;
		this.valido = valido;
	}
	
	public int[] getCodigosErros() {
		return codigosErros;
	}
	public String[] getMensagens() {
		return mensagensErros;
	}
	public boolean isValido() {
		return valido;
	}
	void setValido(boolean valido) {
		this.valido = valido;
	}	
}
