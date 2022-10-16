package br.com.cesarschool.poo.entidades;

import java.time.LocalDate;

public class ContaPoupanca extends Correntista{

    private double taxaDeJuros; // Deve ser maior do que zero
    private int totalDepositos;

    public ContaPoupanca(
    		String cpf,
    		String nome,
    		double taxaDeJuros,
			long numero,
			Status status,
			LocalDate dataAbertura,
			TipoConta tipoConta
    		) {
        super(cpf, nome, numero, status, dataAbertura, tipoConta);
        this.taxaDeJuros = taxaDeJuros;
        this.totalDepositos = 0;

    }

    public double getTaxaDeJuros() {
        return taxaDeJuros;
    }

    public int getTotalDepositos() {
        return totalDepositos;
    }

    public void setTaxaDeJuros(double taxaDeJuros) {
        this.taxaDeJuros = taxaDeJuros;
    }

    public void setTotalDepositos(int totalDepositos) {
        this.totalDepositos = totalDepositos;
    }

    void pegarTaxaJuros() {

        if(taxaDeJuros <= 0) {

            System.out.println("Taxa inválida! Informe uma taxa maior que 0!");
        }

    }

}
