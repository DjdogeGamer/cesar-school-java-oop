package br.com.cesarschool.poo.telas;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.StatusConta;
import br.com.cesarschool.poo.mediators.ContaMediator;
import br.com.cesarschool.poo.mediators.OperacoesAlterar;
import br.com.cesarschool.poo.mediators.OperacoesFinanceiras;
import br.com.cesarschool.poo.mediators.StatusValidacaoConta;


public class TelaConta {
	
	private static final String DIGITE_O_NUMERO = "Digite o numero: ";
	private static final String CONTA_NAO_ENCONTRADA = "Conta nao encontrada!";
	private static final int NUMERO_DESCONHECIDO = -1;
	
	private static final Scanner SC = new Scanner(System.in);
	private ContaMediator contaMediator = new ContaMediator(); 
	
	public void executaTela() {
		while(true) {
			long numero = NUMERO_DESCONHECIDO;
			imprimeMenuPrincipal();
			int opcao = SC.nextInt();
			if (opcao == 1) {
				processaInclusao();
			} else if (opcao >= 2 && opcao <= 5) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					OperacoesAlterar operacao = OperacoesAlterar.obterPorCodigo(opcao-1);
					processaAlteracao(numero, operacao);
				} 
			} else if (opcao == 6) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					processaExclusao(numero);
				}			
			} else if (opcao == 7) {
				processaBusca();
			} else if (opcao == 8) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					OperacoesAlterar operacao = OperacoesAlterar.obterPorCodigo(5);
					processaAlteracao(numero, operacao);
				} 
			} else if (opcao == 9) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					OperacoesAlterar operacao = OperacoesAlterar.obterPorCodigo(6);
					processaAlteracao(numero, operacao);
				} 
			} else if (opcao == 10) {
				System.out.println("Saindo... ");
				System.exit(0);
			} else {
				System.out.println("Opcao invalida!!");
			}
		} 
	}

	private void imprimeMenuPrincipal() {		
		System.out.println();		
		System.out.println("1- Incluir");
		System.out.println("2- Alterar");
		System.out.println("3- Encerrar");
		System.out.println("4- Bloquear");
		System.out.println("5- Ativar");
		System.out.println("6- Excluir");
		System.out.println("7- Buscar");
		System.out.println("8- Creditar");
		System.out.println("9- Debitar");
		System.out.println("10- Sair");
		System.out.print("Digite a opcao: ");
	}
	
	private void processaMensagensErroValidacao(StatusValidacaoConta status) {
		String[] mensagensErro = status.getMensagens();
		System.out.println("Problemas ao incuir/alterar Conta:");
		for (String mensagemErro : mensagensErro) {
			if (mensagemErro != null) {
				System.out.println(mensagemErro);
			} 
		}
	}
	
	private void processaInclusao() {
		Conta conta = capturaConta(NUMERO_DESCONHECIDO);
		StatusValidacaoConta status = contaMediator.incluir(conta);
		if (status.isValido()) { 
			System.out.println("Conta incluida com sucesso!");
		} else {
			processaMensagensErroValidacao(status);			
		}
	}
	
	private int processaAlteracao(long numero, OperacoesAlterar operacao) {
		Conta conta = contaMediator.buscar(numero);
		if (conta == null) {
			System.out.println(CONTA_NAO_ENCONTRADA);
			return NUMERO_DESCONHECIDO;
		}
		Conta alterada = null;
		if (operacao == OperacoesAlterar.ALTERAR_DATA) {
			LocalDate novaDataAbertura = pegarDataAbertura();
			if (novaDataAbertura == null) {
				System.out.println("Erro em data");
				return -1;
			}
			alterada = new Conta(conta.getNumero(), conta.getStatus(), novaDataAbertura);
			imprimirConta(alterada);
		} else if (operacao == OperacoesAlterar.ENCERRAR) {
			if (conta.getStatus() != StatusConta.ENCERRADA) {
				alterada = contaMediator.mudarParaStatus(conta, StatusConta.ENCERRADA);
			}
		} else if (operacao ==  OperacoesAlterar.BLOQUEAR) {
			if (conta.getStatus() == StatusConta.BLOQUEADA) {
				System.out.println("A conta ja esta bloqueada");
			}
			else if (conta.getStatus() == StatusConta.ENCERRADA) {
				System.out.println("A conta foi encerrada, abortando alteracao...");
			} else {
				alterada = contaMediator.mudarParaStatus(conta, StatusConta.BLOQUEADA);
			}
		} else if (operacao ==  OperacoesAlterar.ATIVAR) {
			if (conta.getStatus() == StatusConta.ATIVA) {
				System.out.println("A conta ja esta ativa");
			}
			else if (conta.getStatus() == StatusConta.ENCERRADA) {
				System.out.println("A conta foi encerrada, abortando alteracao...");
			} else {
				alterada = contaMediator.mudarParaStatus(conta, StatusConta.ATIVA);
			}
		} else if (operacao == OperacoesAlterar.CREDITAR || operacao == OperacoesAlterar.DEBITAR) {
			System.out.printf("Informe valor creditado: ");
			double valor = SC.nextDouble();
			if (valor < 0) {
				System.out.println("Erro, valor negativo");
				return -1;
			}
			alterada = contaMediator.mudarSaldo(conta, operacao, valor);
			System.out.println();
			imprimirConta(alterada);
		}
		
		StatusValidacaoConta status = contaMediator.alterar(alterada);
		if (status.isValido()) { 
			System.out.println("Conta alterada com sucesso!");
		} else {
			processaMensagensErroValidacao(status);		
		}
		return 0;
	}
	
	private long processaBusca() {
		System.out.print(DIGITE_O_NUMERO);
		long numero = SC.nextLong();
		Conta conta = contaMediator.buscar(numero);
		if (conta == null) {
			System.out.println(CONTA_NAO_ENCONTRADA);
			return NUMERO_DESCONHECIDO;
		} else {
			imprimirConta(conta);
			return numero;
		}
	}
	
	private void imprimirConta(Conta conta) {
		System.out.println("Numero da Conta: " + conta.getNumero());
		System.out.printf("Saldo: R$%,.2f\n", conta.getSaldo());
		System.out.println("Escore: " + conta.calcularEscore());
		System.out.println("Status: " + conta.getStatus());
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = conta.getDataAbertura().format(formatador);
		System.out.println("Data de Abertura: " + data);
	}
	
	private void processaExclusao(long codigo) {
		boolean retornoRepositorio = contaMediator.excluir(codigo);
		if (retornoRepositorio) {
			System.out.println("Conta excluida com sucesso!");
		} else {
			System.out.println(CONTA_NAO_ENCONTRADA);
		}
	}
	
	private Conta capturaConta(long codigoDaAlteracao) {
		long numero; 
		if (codigoDaAlteracao == NUMERO_DESCONHECIDO) {
			System.out.print(DIGITE_O_NUMERO);
			numero = SC.nextLong();			
		} else {
			numero = codigoDaAlteracao;
		}
		System.out.print("Digite o status da conta (1, 2 ou 3): ");
		int statusContaCodigo = SC.nextInt();
		StatusConta status = StatusConta.obterPorCodigo(statusContaCodigo);
		LocalDate dataAbertura = pegarDataAbertura();
		return new Conta(numero, status, dataAbertura);
	}
	private LocalDate pegarDataAbertura() {
		int dia, mes, ano;
		System.out.println("Digite o dia: ");
		dia = SC.nextInt();
		System.out.println("Digite o mes: ");
		mes = SC.nextInt();
		System.out.println("Digite o ano: ");
		ano = SC.nextInt();
		
		if (verificarValoresDatas(dia, mes, ano)) {
			YearMonth mesInformado = YearMonth.of(ano, mes);
			int qtdDiasNoMes = mesInformado.lengthOfMonth();
			if (dia <= qtdDiasNoMes) {
				return LocalDate.of(ano, mes, dia);
			}
		}
		return null;
	}
	
	private boolean verificarValoresDatas(int dia, int mes, int ano) {
		if (dia <= 0) {
			return false;
		}
		else if (mes > 12 || mes < 0) {
			return false;
		} else if(ano < 0) {
			return false;
		}
		return true;
	}
}
