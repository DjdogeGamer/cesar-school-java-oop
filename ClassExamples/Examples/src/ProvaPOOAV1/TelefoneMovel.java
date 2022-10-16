package ProvaPOOAV1;

public class TelefoneMovel extends Eletronico {
	private final double capacidadeBateria;
	
	protected TelefoneMovel(String marca, boolean estimarConsumo, double potencia, double capacidadeBateria) {
		super(marca, estimarConsumo, potencia);
		this.capacidadeBateria = capacidadeBateria;
	}
	
	double getCapacidadeBateria() {
		return capacidadeBateria;
	}
	
	double calcularTempoEstimadoUso (double[] fatoresPonderacaoConsumo) {
		double tempoEstimadoUso;
		if (getEstimarConsumo() == false) {
			tempoEstimadoUso = 0;
		}
		else {
			double media = 0;
			if (fatoresPonderacaoConsumo == null || fatoresPonderacaoConsumo.length == 0) {
				media = 0.87;
			}
			else {
				double total = 0;
				double tamanho = fatoresPonderacaoConsumo.length;
				for (int i = 0; i < tamanho; i++) {
					total += fatoresPonderacaoConsumo[i];
				}
				media = total/tamanho;
			}
			tempoEstimadoUso = getCapacidadeBateria() * getPotencia() * media;
		}
		return tempoEstimadoUso;
	}
}
