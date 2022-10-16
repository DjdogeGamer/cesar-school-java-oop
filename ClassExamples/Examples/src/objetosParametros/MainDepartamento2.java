package objetosParametros;

public class MainDepartamento2 {
	public static void main(String[] args) {
		Loja l1 = null;
		Loja l2 = null;
		double valor = 500;
		int valorInt = 700;
		TesteParametrosDepto tpd = new TesteParametrosDepto();
		Departamento d1 = new Departamento("Sorvetes", 8000, l2);
		Departamento d2 = new Departamento("Vinhos", 12000, l1);
		Departamento d3 = d1;
		System.out.println(d2.loja);
		d1.loja = new Loja();
		l1 = d1.loja;
		l2 = new Loja();
		System.out.println(d1.loja.nome);
		l1.nome = "REC PINA";
		l2.nome = "REC ANTIGO";
		d2.nome = "Verduras";
		d2.loja = l2;
		d3 = d2;
		System.out.println(l1.nome);
		System.out.println(d1.loja.nome);
		tpd.ahuuu(valorInt, d3);
		System.out.println(d3.receita);
		System.out.println(d1.receita);
		System.out.println(valorInt);
		tpd.doo(600.00, d1);
		System.out.println(d3.receita);
		tpd.goo((float)valor, d2);
		System.out.println(valor);
		System.out.println(d2.receita);
	}
}
