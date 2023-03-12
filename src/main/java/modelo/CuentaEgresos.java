package modelo;

public class CuentaEgresos extends Cuenta {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double egresos;
	
	public CuentaEgresos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CuentaEgresos(int numeroCuenta, String nombreCuenta) {
		super(numeroCuenta, nombreCuenta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getMonto() {
		// TODO Auto-generated method stub
		return -egresos;
	}

	@Override
	public void registrarEgreso(double valor) {
		egresos += valor;
		
	}

	@Override
	public void registrarIngreso(double valor) {
		egresos += valor;
	}
	
}
