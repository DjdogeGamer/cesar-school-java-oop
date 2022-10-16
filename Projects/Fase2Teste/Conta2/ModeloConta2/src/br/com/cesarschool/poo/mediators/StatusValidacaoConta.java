package br.com.cesarschool.poo.mediators;

/**
 * @author An�nimo
 *
 * Classe na vers�o final da atividade continuada n�o ter� mais serventia!
 * 
 */
public class StatusValidacaoConta {
		 
	public static final int TIPO_NAO_PREENCHIDO = 1;
	public static final int CONTA_NAO_ENCONTRADA = 2;
	public static final int CONTA_NAO_INFORMADA = 3;
	public static final int STATUS_INVALIDO = 4;
	public static final int DATA_FALSA = 5;
	public static final int DATA_INVALIDA = 6;
	public static final int NUMERO_INVALIDO = 7;
	public static final int NUMERO_CONTA_EXISTENTE = 8;
	public static final int OPERACAO_INVALIDA = 9;
	public static final int QTD_SITUACOES_EXCECAO = 9;
	
	private int[] codigosErros;
	private String[] mensagens;
	private boolean valido;
	
	public StatusValidacaoConta(int[] codigosErros, String[] mensagens, boolean valido) {
		super();
		this.codigosErros = codigosErros;
		this.mensagens = mensagens;
		this.valido = valido;
	}
	
	public int[] getCodigosErros() {
		return codigosErros;
	}
	public String[] getMensagens() {
		return mensagens;
	}
	public boolean isValido() {
		return valido;
	}
	void setValido(boolean valido) {
		this.valido = valido;
	}	
}
