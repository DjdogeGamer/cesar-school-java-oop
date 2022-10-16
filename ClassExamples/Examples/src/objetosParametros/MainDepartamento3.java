package objetosParametros;

public class MainDepartamento3 {
	public static void main(String[] args) {
		Loja l1 = null;
		Loja l2 = null;
		Loja l3 = null;
		Departamento d1 = new Departamento("Limpeza", 1000, null);
		Departamento d2 = new Departamento("Brinquedos", 2000, null);
		Departamento d3 = d2;
		System.out.println(l2);
		System.out.println(l1 == l2);
		l1 = new Loja();
		System.out.println(l1 == l2);
		l1.nome = "REC TORRE";
		l3 = l2 = new Loja();
		l2.nome = "REC CDU";
		System.out.println(l3.nome);
		System.out.println(d1 != d2);
		System.out.println(d1 == d2);
		System.out.println(d1 == d3);
		System.out.println(d2 != d3);
		System.out.println(d2 == d3);
	}
}