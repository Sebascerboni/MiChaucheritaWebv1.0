package modelo;

import java.io.Serializable;

public abstract class Cuenta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numeroCuenta;
	private String nombreCuenta;
	
	
	
	public Cuenta() {
		
	}

	public Cuenta(int numeroCuenta, String nombreCuenta) {
		this.numeroCuenta = numeroCuenta;
		this.nombreCuenta = nombreCuenta;
	}
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	abstract public double getMonto();
	abstract public void registrarEgreso(double valor);
	abstract public void registrarIngreso(double valor);

	@Override
	public String toString() {
		return "{\"numeroCuenta\":\"" + this.numeroCuenta + "\","
				+ "\"nombreCuenta\":\"" + this.nombreCuenta + "\","
				+ "\"monto\":" + this.getMonto()
				+ "}";
	}
	
	
	
}
