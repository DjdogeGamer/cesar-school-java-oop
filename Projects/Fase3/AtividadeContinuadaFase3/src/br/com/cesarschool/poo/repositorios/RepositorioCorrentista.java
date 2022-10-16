package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Correntista;

public class RepositorioCorrentista {
	
	private static final int SUCESSO = 0;
	private static final int ERRO_MAX_CORRENTISTAS = -1;
	private static final int CORRENTISTA_NAO_ACHADO = -1;
	//private static final int POSICAO_INVALIDA = -2;
	//private static final int POSICAO_OCUPADA = -3;
	private static final int ERRO_DESCONHECIDO = -4;
	
	private static RepositorioCorrentista repositorio_instance = null;
	
	private RepositorioCorrentista() {
		
	}

	public static RepositorioCorrentista getInstance() {
		if (repositorio_instance == null)
			repositorio_instance = new RepositorioCorrentista();
		
		return repositorio_instance;
	}
	
	private static final int MAX_CORRENTISTAS = 1024;
	private Correntista[] vetorCorrentistas = new Correntista[MAX_CORRENTISTAS];
	private int tamanho = 0;
	
	public int incluir(Correntista correntista) {
		if (tamanho == MAX_CORRENTISTAS) {
			System.out.println("Tamanho Máximo de Correntistas Atingidos");
			return ERRO_MAX_CORRENTISTAS;
		}
		for (int i = 0; i < vetorCorrentistas.length; i++) {
			if (vetorCorrentistas[i] == null) {
				vetorCorrentistas[i] = correntista;
				tamanho++;
				return SUCESSO;
			}
		}
		return ERRO_DESCONHECIDO;
	}
	
	public boolean temCPF(String cpf) {
		for (int i = 0; i < vetorCorrentistas.length; i++) {
			if (vetorCorrentistas[i] == null) {
				continue;
			}
			else if (vetorCorrentistas[i].getCpf().equals(cpf)) {
				System.out.println("O CPF informado ja existe");
				return true;
			}
		}
		return false;
	}
	
	public int buscar(String cpf) {
		for (int posicao = 0; posicao < vetorCorrentistas.length; posicao++) {
			if (vetorCorrentistas[posicao] == null) {
				continue;
			}
			else if (vetorCorrentistas[posicao].getCpf().equals(cpf)) {
				return posicao;
			}
		}
		System.out.println("O correntista " + cpf + " nao foi localizado");
		return CORRENTISTA_NAO_ACHADO;
	}
	
	public Correntista retornarCorrentista(String cpf) {
		int indice = buscar(cpf);
		if (indice < 0) {
			return null;
		}
		return vetorCorrentistas[indice];
	}
	
	public int alterar(String cpf) {
		int indice = buscar(cpf);
		if (indice < 0) {
			return CORRENTISTA_NAO_ACHADO;
		}
		return SUCESSO;
	}
	
	public int excluir(String cpf) {
		int indice = buscar(cpf);
		if (indice < 0) {
			return CORRENTISTA_NAO_ACHADO;
		}
		vetorCorrentistas[indice] = null;
		tamanho--;
		return SUCESSO;
	}
	
}
