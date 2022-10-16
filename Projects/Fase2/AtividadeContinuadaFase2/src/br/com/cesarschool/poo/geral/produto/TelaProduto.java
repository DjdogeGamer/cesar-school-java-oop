package br.com.cesarschool.poo.geral.produto;

import java.util.Scanner;

/**
 * @author An�nimo
 *
 * Trata-se de uma implementa��o de tela prim�ria, para efeito did�tico e de demonstra��o simplificada
 * da arquitetura em camadas e do custo de implementa��o de uma interface visual, por mais simples que
 * seja!
 * 
 */
public class TelaProduto {
	
	private static final int CODIGO_DESCONHECIDO = -1;
	private static final Scanner ENTRADA = new Scanner(System.in);
	private RepositorioProduto repositorioProduto = new RepositorioProduto();
	
	public void executaTela() {
		while(true) {
			long codigo = CODIGO_DESCONHECIDO;
			imprimeMenuPrincipal();
			int opcao = ENTRADA.nextInt();
			if (opcao == 1) {
				processaInclusao();
			} else if (opcao == 2) {
				codigo = processaBusca();
				if (codigo != CODIGO_DESCONHECIDO) {
					processaAlteracao(codigo);
				} 
			} else if (opcao == 3) {
				codigo = processaBusca();
				if (codigo != CODIGO_DESCONHECIDO) {
					processaExclusao(codigo);
				}			
			} else if (opcao == 4) {
				processaBusca();
			} else if (opcao == 5) {
				System.out.println("Saindo do cadastro de produtos");
				System.exit(0);
			} else {
				System.out.println("Op��o inv�lida!!");
			}
		} 
	}
	
	private void imprimeMenuPrincipal() {		
		System.out.println("1- Incluir");
		System.out.println("2- Alterar");
		System.out.println("3- Excluir");
		System.out.println("4- Buscar");
		System.out.println("5- Sair");
		System.out.print("Digite a op��o: ");
	}	
	
	private void processaInclusao() {
		Produto produto = capturaProduto(CODIGO_DESCONHECIDO);
		String retornoValidacao = validar(produto);
		if (retornoValidacao == null) {
			boolean retornoRepositorio = repositorioProduto.incluir(produto);
			if (retornoRepositorio) {
				System.out.println("Produto inclu�do com sucesso!");
			} else {
				System.out.println("Erro na inclus�o de produto!");
			}
		} else {
			System.out.println(retornoValidacao);
		}		
	}
	
	private void processaAlteracao(long codigo) {
		Produto produto = capturaProduto(codigo);
		String retornoValidacao = validar(produto);
		if (retornoValidacao == null) {
			boolean retornoRepositorio = repositorioProduto.alterar(produto);
			if (retornoRepositorio) {
				System.out.println("Produto alterado com sucesso!");
			} else {
				System.out.println("Erro na altera��o de produto!");
			}
		} else {
			System.out.println(retornoValidacao);
		}		
	}
	
	private long processaBusca() {
		System.out.print("Digite o c�digo: ");
		long codigo = ENTRADA.nextLong();
		Produto produto = repositorioProduto.buscar(codigo);
		if (produto == null) {
			System.out.println("Produto n�o encontrado!");
			return CODIGO_DESCONHECIDO;
		} else {
			System.out.println("C�digo: " + produto.getCodigo());
			System.out.println("Nome: " + produto.getNome());
			System.out.println("Pre�o: " + produto.getPreco());
			System.out.println("Tipo: " + produto.getTipo().getDescricao());
			return codigo;
		}
	}
	
	private void processaExclusao(long codigo) {
		boolean retornoRepositorio = repositorioProduto.excluir(codigo);
		if (retornoRepositorio) {
			System.out.println("Produto exclu�do com sucesso!");
		} else {
			System.out.println("Erro na exclus�o de produto!");
		}
	}
	
	private Produto capturaProduto(long codigoDaAlteracao) {
		long codigo; 
		if (codigoDaAlteracao == CODIGO_DESCONHECIDO) {
			System.out.print("Digite o c�digo: ");
			codigo = ENTRADA.nextLong();			
		} else {
			codigo = codigoDaAlteracao;
		}
		System.out.print("Digite o nome: ");
		String nome = ENTRADA.next();
		System.out.print("Digite o pre�o: ");
		double preco = ENTRADA.nextDouble();
		System.out.print("Digite o tipo de produto (1, 2 ou 3): ");
		int codigoTipo = ENTRADA.nextInt();
		TipoProduto tipo = TipoProduto.obterPorCodigo(codigoTipo);
		return new Produto(codigo, nome, preco, tipo);
	}
	
	private String validar(Produto produto) {
		int validacaoNome = produto.validarNome();
		if (!produto.codigoValido()) {
			return "C�digo inv�lido!";
		} else if (validacaoNome == Produto.NOME_NAO_INFORMADO) {
			return "Nome n�o informado!";
		} else if (validacaoNome == Produto.NOME_MUITO_CURTO) {
			return "Nome muito curto!";
		} else if (!produto.precoValido()) {
			return "Pre�o inv�lido!";
		} else if (!produto.tipoPreechido()) {
			return "Tipo n�o preenchido!";
		} else {
			return null;
		}
	}
}
