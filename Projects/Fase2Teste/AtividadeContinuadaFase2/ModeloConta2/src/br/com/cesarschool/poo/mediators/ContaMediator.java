package br.com.cesarschool.poo.mediators;

import java.time.LocalDate;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.StatusConta;
import br.com.cesarschool.poo.repositorios.RepositorioConta;

public class ContaMediator {
	
	private RepositorioConta repositorioConta = RepositorioConta.getInstancia();

	private static final String MENSAGEM_STATUS_INVALIDO = "Status informado e invalido";
	private static final String MENSAGEM_DATA_FALSA = "Data informada e falsa";
	private static final String MENSAGEM_DATA_INVALIDA = "Data informada e invalida";
	private static final String MENSAGEM_NUMERO_INVALIDO = "Numero informado e invalido";
	private static final String MENSAGEM_NUMERO_CONTA_EXISTENTE = "Numero informado ja esta em uso por outra conta";
	//private static final String MENSAGEM_CONTA_NAO_ENCONTRADA = "A conta informada nao pode ser encontrada";
	private static final String MENSAGEM_CONTA_NAO_INFORMADA = "A conta nao foi informada";
	private static final String MENSAGEM_OPERACAO_INVALIDA = "A operacao informada e invalida";
			
	public StatusValidacaoConta incluir(Conta conta) {
		StatusValidacaoConta status = validar(conta);
		if (status.isValido()) {
			boolean retornoRepositorio = repositorioConta.incluir(conta);
			if (!retornoRepositorio) {
				status.getCodigosErros()[0] = StatusValidacaoConta.NUMERO_CONTA_EXISTENTE;
				status.getMensagens()[0] = MENSAGEM_NUMERO_CONTA_EXISTENTE;
				status.setValido(false);
			}
		}				
		return status;
	}
	public StatusValidacaoConta alterar(Conta conta) {
		StatusValidacaoConta status = validar(conta);
		if (status.isValido()) {
			boolean retornoRepositorio = repositorioConta.alterar(conta);
			if (!retornoRepositorio) {
				status.getCodigosErros()[0] = StatusValidacaoConta.OPERACAO_INVALIDA;
				status.getMensagens()[0] = MENSAGEM_OPERACAO_INVALIDA;
				status.setValido(false);
			}
		}				
		return status;
	}	
	public boolean excluir(long codigo) {
		return repositorioConta.excluir(codigo);
	}	
	public Conta buscar(long numero) {
		return repositorioConta.retornarConta(numero);
	}
	public Conta mudarParaStatus(Conta conta, StatusConta statusNovo) {
		return new Conta(conta.getNumero(), statusNovo, conta.getDataAbertura());
	}
	public Conta mudarSaldo(Conta conta, OperacoesAlterar operacao, double valor) {
		double saldoAntigo = conta.getSaldo();
		double saldoNovo = saldoAntigo;
		if (operacao == OperacoesAlterar.CREDITAR) {
			saldoNovo = saldoAntigo += valor;
		} else if (operacao == OperacoesAlterar.DEBITAR) {
			saldoNovo = saldoAntigo -= valor;
		}
		Conta contaNova = new Conta(conta.getNumero(), conta.getStatus(), conta.getDataAbertura());
		contaNova.setSaldo(saldoNovo);
		return contaNova;
	}
	
	private StatusValidacaoConta validar(Conta conta) {
		int[] codigoStatus = new int[StatusValidacaoConta.QTD_SITUACOES_EXCECAO]; 
		String[] mensagensStatus = new String[StatusValidacaoConta.QTD_SITUACOES_EXCECAO];
		int contErros = 0;
		if (conta == null) {
			codigoStatus[contErros++] = StatusValidacaoConta.CONTA_NAO_INFORMADA;
			mensagensStatus[contErros] = MENSAGEM_CONTA_NAO_INFORMADA;			
		} else {
			if (!statusValido(conta)) {
				codigoStatus[contErros++] = StatusValidacaoConta.STATUS_INVALIDO;
				mensagensStatus[contErros] = MENSAGEM_STATUS_INVALIDO;
			}
			if (!dataReal(conta)) {
				codigoStatus[contErros++] = StatusValidacaoConta.DATA_FALSA;
				mensagensStatus[contErros] = MENSAGEM_DATA_FALSA;
			}
			if(!dataValida(conta)) {
				codigoStatus[contErros++] = StatusValidacaoConta.DATA_INVALIDA;
				mensagensStatus[contErros] = MENSAGEM_DATA_INVALIDA;
			}
			if (!numeroTamanhoValido(conta)) {
				codigoStatus[contErros++] = StatusValidacaoConta.NUMERO_INVALIDO;
				mensagensStatus[contErros] = MENSAGEM_NUMERO_INVALIDO;								
			}
		}		
		return new StatusValidacaoConta(codigoStatus, mensagensStatus, contErros == 0);		
	}	
	
	private boolean statusValido(Conta conta) {
		if (conta.getStatus() == null) {
			return false;
		}
		return true;
	}
	private boolean dataReal(Conta conta) {
		if (conta.getDataAbertura() == null) {
			return false;
		}
		return true;
	}
	private boolean dataValida(Conta conta) {
		LocalDate dataConta = conta.getDataAbertura();
		LocalDate hoje = LocalDate.now();
		LocalDate dataLimite = hoje.minusMonths(1);
		
		if (dataConta.equals(hoje)) {
			return true;
		}
		else if (dataConta.isAfter(dataLimite) && dataConta.isBefore(hoje)) {
			return true;
		}		
		return false;
	}
	private boolean numeroTamanhoValido(Conta conta) {
		if (conta.getNumero() <= 0) {
			return false;
		}
		return true;
	}
}
