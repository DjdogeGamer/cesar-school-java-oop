package objetosParametros;

public class TesteParametrosDepto {
	void ahuuu(int valor, Departamento depto) {
		depto.receita = depto.receita - valor;
		valor = 1000;
	}
	void goo(float valor, Departamento depto) {
		Departamento depto2 = depto;
		depto2.loja.nome = "CARUARU";
		ahuuu((int)valor, depto2);
	}
	void doo(double valor, Departamento depto) {
		depto = new Departamento("Frutas", valor, new Loja());
		valor = 500;
		goo((float)valor, depto);
	}
}
