package erObjetosParametros;

public class MainFuncionario3 {
	public static void main(String[] args) {
		Cargo c1 = new Cargo();
		Cargo c2 = null;
		Funcionario f1 = new Funcionario("CARLOS", 1000, null);
		Funcionario f2 = new Funcionario("MARIA", 2000, null);
		Funcionario f3 = f2;
		System.out.println(c2);
		System.out.println(c1 == c2);
		c2 = new Cargo();
		System.out.println(c1 == c2);
		c1.nome = "ANALISTA";
		c2.nome = "ARQUITETO";
		System.out.println(f1 == f2);
		System.out.println(f1 != f2);
		System.out.println(f1 == f3);
		System.out.println(f2 != f3);
	}
}
