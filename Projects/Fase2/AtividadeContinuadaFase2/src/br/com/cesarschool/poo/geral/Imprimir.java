package br.com.cesarschool.poo.geral;

import java.time.format.DateTimeFormatter;

public class Imprimir {
	public static void opcoes() {
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
	
	public static void conta(Conta conta) {
		System.out.println("Numero: " + conta.getNumero());
		System.out.println("Escore: " + conta.calcularEscore());
		System.out.println("Status: " + conta.getStatus());
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = conta.getDataAbertura().format(formatador);
		System.out.println("Data de abertura: "+ data);
		System.out.printf("Saldo: R$%.2f\n", conta.getSaldo());
	}
}
