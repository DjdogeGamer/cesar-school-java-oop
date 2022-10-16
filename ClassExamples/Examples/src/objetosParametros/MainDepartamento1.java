package objetosParametros;

public class MainDepartamento1 {
	public static void main(String[] args) {
		Loja loja = new Loja();
		Loja loja2 = new Loja();
		loja.nome = "REC SETUBAL";
		loja.cepEndereco = 51150002L;
		loja2.nome = "REC CENTRO";
		loja2.cepEndereco = 50000000L;
		Departamento d1 = new Departamento("Eletro", 60000.00, loja);
		Departamento d2 = new Departamento("Roupa", 10000.00, loja);
		Departamento d3 = d2;
		d1.adicionarReceita(5000);
		d3.nome = "Vestuario";
		System.out.println(d1.nome);
		System.out.println(d3.nome);
		System.out.println(d2.receita);
		d1.receita = 25000;
		d3.loja = loja2;
		d2.receita = d1.receita;
		d2.adicionarReceita(3000);
		System.out.println(d2.nome);
		System.out.println(d3.receita);
		System.out.println(d2.loja.nome);
		d3 = d1;
		System.out.println(d1.nome);
		System.out.println(d3.nome);
		System.out.println(d3.loja.nome);
	}
}
