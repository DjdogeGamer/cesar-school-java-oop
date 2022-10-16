package ProvaPOOAV1;

public class Eletronico {
	private final String marca;
	private boolean estimarConsumo;
	private final double potencia;
	
	Eletronico(String marca, boolean estimarConsumo, double potencia) {
		this.marca = marca;
		this.estimarConsumo = estimarConsumo;
		this.potencia = potencia;
	}
	
	public String getMarca() {
		return marca;
	}
	
	boolean getEstimarConsumo() {
		return estimarConsumo;
	}
	
	void setEstimarConsumo(boolean estimarConsumo) {
		this.estimarConsumo = estimarConsumo;
	}
	
	protected double getPotencia() {
		return potencia;
	}
}
