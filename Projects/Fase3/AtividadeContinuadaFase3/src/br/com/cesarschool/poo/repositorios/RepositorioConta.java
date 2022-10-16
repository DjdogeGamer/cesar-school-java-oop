package br.com.cesarschool.poo.repositorios;

import java.time.LocalDate;

import br.com.cesarschool.poo.entidades.Conta;

public class RepositorioConta {
	private static final int SUCESSO = 0;
	private static final int ERRO_MAX_CONTAS = -1;
	private static final int CONTA_NAO_ACHADA = -1;
	//private static final int POSICAO_INVALIDA = -2;
	//private static final int POSICAO_OCUPADA = -3;
	private static final int ERRO_DESCONHECIDO = -4;
	
	private static RepositorioConta repositorio_instance = null; 
	
	private RepositorioConta() {
		
	}
	
	public static RepositorioConta getInstance() 
    { 
        if (repositorio_instance == null) 
        	repositorio_instance = new RepositorioConta(); 
  
        return repositorio_instance; 
    } 
		
	
	private static final int MAX_CONTAS = 1024;
	private Conta[] vetorContas = new Conta[MAX_CONTAS];
	private int tamanho = 0;

	
	public int getTamanho() {
		return tamanho;
	}

	public int incluir(Conta conta) {
		if (tamanho == MAX_CONTAS) {
			System.out.println("Tamanho máximo de contas atingido");
			return ERRO_MAX_CONTAS;
		}
		for(int i = 0; i < vetorContas.length; i++) {
			if (vetorContas[i] == null) {
				vetorContas[i] = conta;
				tamanho++;
				return SUCESSO;
			}
		}
		return ERRO_DESCONHECIDO;
	}
	
	public boolean temNumero(long numero) {
		for (int i = 0; i < vetorContas.length; i++) {
			if (vetorContas[i] == null) {
				continue;
			}
			else if (vetorContas[i].getNumero() == numero) {
				System.out.println("O numero informado ja existe");
				return true;
			}
		}
		return false;
	}
	
	public int buscar(long numero) { 
		for (int posicao = 0; posicao < vetorContas.length; posicao++) {
			if (vetorContas[posicao] == null) {
				continue;
			}
			else if (vetorContas[posicao].getNumero() == numero) {
				return posicao;
			}
		}
		System.out.println("A conta " + numero + " nao foi localizada");
		return CONTA_NAO_ACHADA;
	}
	
	public Conta retornarConta(long numero) {
		int indice = buscar(numero);
		if (indice < 0) {
			return null;
		}
		return vetorContas[indice];
	}
	
	public int alterar(long numero, LocalDate novaDataAbertura) {
		int indice = buscar(numero);
		if (indice < 0) {
			return CONTA_NAO_ACHADA;
		}
		vetorContas[indice].setDataAbertura(novaDataAbertura);
		return SUCESSO;
	}
	
	public int excluir(long numero) {
		int indice = buscar(numero);
		if (indice < 0) {
			return CONTA_NAO_ACHADA;
		}
		vetorContas[indice] = null;
		tamanho--;
		return SUCESSO;
	}
	
	
}
