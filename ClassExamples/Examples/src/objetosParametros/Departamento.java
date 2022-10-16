package objetosParametros;

public class Departamento {
	String nome;
	double receita;
	Loja loja;
	
	public Departamento(String nome, double receita, Loja loja) {
		this.nome = nome;
		this.receita = receita;
		this.loja = loja;
	}
	void adicionarReceita(double valor) {
		receita += valor;
	}
}
