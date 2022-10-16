package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Conta;


public class RepositorioConta {

	private static final int MAX_CONTAS = 1000;
	private static RepositorioConta instancia = null;
	
	private Conta[] vetorContas = new Conta[MAX_CONTAS];
	private int tamanho = 0;
	
	
	private RepositorioConta() {
		
	}
	
	public static RepositorioConta getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioConta();
		}
		return instancia; 
	}
	
	public boolean incluir(Conta conta) {
		if (buscarIndice(conta.getNumero()) != -1) {
			return false;
		} else if (tamanho == MAX_CONTAS - 1) {
			return false;
		} else {
			for (int i = 0; i < vetorContas.length; i++) {
				if (vetorContas[i] == null) {
					vetorContas[i] = conta; 
					break;
				}
			}
			tamanho++; 
			return true; 
		}
	}
	public boolean alterar(Conta conta) {
		int indice = buscarIndice(conta.getNumero()); 
		if (indice == -1) {
			return false;
		} else {
			vetorContas[indice] = conta;
			return true; 
		}
	}
	
	public Conta retornarConta(long codigo) {
		int indice = buscarIndice(codigo);
		if (indice == -1) {
			return null;
		} else {
			return vetorContas[indice];
		}
	}
	
	public boolean excluir(long codigo) {
		int indice = buscarIndice(codigo);
		if (indice == -1) {
			return false;
		} else {
			vetorContas[indice] = null;
			tamanho--;
			return true;
		}		
	}
	
	public int buscarIndice(long numero) {		
		for (int i = 0; i < vetorContas.length; i++) {
			if (vetorContas[i] != null && vetorContas[i].getNumero() == numero) {
				return i; 				
			}
		}
		return -1;
	}
}
