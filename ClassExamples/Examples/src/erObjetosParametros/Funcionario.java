package erObjetosParametros;

public class Funcionario {
	String nome;
	double salario;
	Cargo cargo;
	Funcionario(String nome, double salario, Cargo cargo) {
	this.nome = nome;
	this.salario = salario;
	this.cargo = cargo;
	}
	void aumentarSalario(double percentual) {
	salario = (1 + percentual/100)*salario;
	}
}
