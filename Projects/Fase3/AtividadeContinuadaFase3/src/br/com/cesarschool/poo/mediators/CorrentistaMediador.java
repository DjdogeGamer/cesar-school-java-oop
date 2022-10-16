package br.com.cesarschool.poo.mediators;

import java.util.InputMismatchException;

import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.repositorios.RepositorioCorrentista;

public class CorrentistaMediador {
	
	private static final int SUCESSO = 0;
	private static final int CORRENTISTA_NAO_EXISTE = -1;
	private static final int CORRENTISTA_INVALIDO = -2;
	private static final int CPF_INVALIDO = -3;
	
	
	private RepositorioCorrentista repositorioCorrentista = RepositorioCorrentista.getInstance();
	
	public int incluir(Correntista correntista) {
		return repositorioCorrentista.incluir(correntista);
	}
	public int alterar(String cpf) {
		return repositorioCorrentista.alterar(cpf);
	}
	public int buscar(String cpf) {
		return repositorioCorrentista.buscar(cpf);
	}
	public int excluir(String cpf) {
		return repositorioCorrentista.excluir(cpf);
	}
	
	public int validar(Correntista correntista) {
		if(correntista == null) {
			return CORRENTISTA_NAO_EXISTE;
		} else {
			if (validaCPF(correntista.getCpf()) != SUCESSO) {
				return CORRENTISTA_INVALIDO;
			}
			
			if (correntista.getNome().equals(null)) {
				return CORRENTISTA_INVALIDO;
			}
		}
		return SUCESSO;
	}
	
    public int validaCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") ||
		    CPF.equals("11111111111") ||
		    CPF.equals("22222222222") || CPF.equals("33333333333") ||
		    CPF.equals("44444444444") || CPF.equals("55555555555") ||
		    CPF.equals("66666666666") || CPF.equals("77777777777") ||
		    CPF.equals("88888888888") || CPF.equals("99999999999") ||
		    (CPF.length() != 11))
		    return CPF_INVALIDO;
		
		char dig10, dig11;
		int sm, i, r, num, peso;
		
		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
		// Calculo do 1o. Digito Verificador
		    sm = 0;
		    peso = 10;
		    for (i=0; i<9; i++) {
		// converte o i-esimo caractere do CPF em um numero:
		// por exemplo, transforma o caractere '0' no inteiro 0
		// (48 eh a posicao de '0' na tabela ASCII)
		    num = (int)(CPF.charAt(i) - 48);
		    sm = sm + (num * peso);
		    peso = peso - 1;
		    }
		
		    r = 11 - (sm % 11);
		    if ((r == 10) || (r == 11))
		        dig10 = '0';
		    else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
		
		// Calculo do 2o. Digito Verificador
		    sm = 0;
		    peso = 11;
		    for(i=0; i<10; i++) {
		    num = (int)(CPF.charAt(i) - 48);
		    sm = sm + (num * peso);
		    peso = peso - 1;
		    }
		
		    r = 11 - (sm % 11);
		    if ((r == 10) || (r == 11))
		         dig11 = '0';
		    else dig11 = (char)(r + 48);
		
		// Verifica se os digitos calculados conferem com os digitos informados.
		    if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		         return SUCESSO;
		    else return CPF_INVALIDO;
		        } catch (InputMismatchException erro) {
		        return CPF_INVALIDO;
		    }
		}
}
