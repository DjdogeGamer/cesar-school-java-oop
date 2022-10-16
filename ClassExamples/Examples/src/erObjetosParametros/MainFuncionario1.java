package erObjetosParametros;

public class MainFuncionario1 {
	public static void main(String[] args) {
		Cargo cargo = new Cargo();
		cargo.nome = "ANALISTA";
		cargo.salarioBase = 1000.00;
		Funcionario f1 = new Funcionario("Eduardo", 1000.00, cargo);
		Funcionario f2 = new Funcionario("Carla", 2000.00, cargo);
		Funcionario f3 = f1;
		f1.aumentarSalario(20);
		f3.nome = "Marcus";
		System.out.println(f1.nome);
		System.out.println(f3.nome);
		System.out.println(f1.salario);
		f2.salario = 2500;
		System.out.println(f2.nome);
		System.out.println(f2.salario);
		f2 = f1;
		System.out.println(f2.nome);
		System.out.println(f2.salario);
		System.out.println(f3.cargo.nome);
	}
}
