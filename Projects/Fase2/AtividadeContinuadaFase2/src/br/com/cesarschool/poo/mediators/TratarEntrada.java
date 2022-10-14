package br.com.cesarschool.poo.mediators;

import java.time.LocalDate;
import java.util.Scanner;

import br.com.cesarschool.poo.entidades.Status;

public class TratarEntrada {
	private static final Scanner SC = new Scanner(System.in);
	
	public static long pegarNumero() {
		long numero = -1L;
		do {
			System.out.print("Informe o numero da conta: ");
			numero = SC.nextLong();
			if (numero <= 0) {
				System.out.println("Informe um numero valido");
			}
			else if (numero == -1) {
				System.out.println("Abortando operacao...");
				return -1L;
			}
		} while (numero <= 0);
		return numero;
	}
	
	public static double pegarValor() {
		double valor = -1.0;
		do {
			System.out.print("Informe o valor: ");
			valor = SC.nextDouble();
			if (valor <= 0) {
				System.out.println("Informe um valor valido");
			}
			else if (valor == -1) {
				System.out.println("Abortando operacao...");
				return -1.0;
			}
		} while (valor <= 0);
		return valor;
	}
	
	public static LocalDate pegarData() {
		LocalDate dataInput = null;
		LocalDate hoje = LocalDate.now();
		LocalDate dataLimite = hoje.minusMonths(1);
		int dia, mes, ano;
		boolean invalidDataInput = true;
		
		while(invalidDataInput) {
			System.out.print("Dia: ");
			dia = SC.nextInt();
			System.out.print("Mes: ");
			mes = SC.nextInt();
			System.out.print("Ano: ");
			ano = SC.nextInt();
			dataInput = LocalDate.of(ano, mes, dia);
			
			if (dataInput.equals(hoje)) {
				invalidDataInput = false;
				//System.out.println("Validado!");
			} else if (dataInput.isAfter(dataLimite) && dataInput.isBefore(hoje)) {
				invalidDataInput = false;
				//System.out.println("Validado!");
			} else {
				System.out.println("Data invalida");
			}
		}
		return dataInput;
	}
	
	public static Status pegarStatus() {
		int numeroStatus = -1;
		Status status = null;
		do {
			System.out.print("Informe o status: ");
			numeroStatus = SC.nextInt();
			switch (numeroStatus) {
				case 1:
					status = Status.ATIVA;
					break;
				case 2: 
					status = Status.ENCERRADA;
					break;
				case 3:
					status = Status.BLOQUEADA;
					break;
				default:
					System.out.println("Status desconhecido");
					break;
			}
		} while (numeroStatus < 1 || numeroStatus > Status.values().length);
		return status;
	}
}
