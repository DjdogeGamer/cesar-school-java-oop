package erObjetosParametros;

public class TesteParametros {
	void foo(double valor, Funcionario funcionario) {
		funcionario.salario = funcionario.salario + valor;
		valor = 2000;
	}
	void boo(double valor, Funcionario funcionario) {
		funcionario = new Funcionario("JOAO", 4000, null);
		valor = 20;
		foo(valor, funcionario);
	}
	void moo(int valor, Funcionario funcionario) {
		Funcionario funcionario2 = funcionario;
		funcionario2.cargo.nome = "PROGRAMADOR";
		foo(valor, funcionario2);
	}
}
