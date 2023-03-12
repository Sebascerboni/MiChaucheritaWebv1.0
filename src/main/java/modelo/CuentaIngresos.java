package modelo;

public class CuentaIngresos extends Cuenta {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double ingresos;
	
	

	public CuentaIngresos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CuentaIngresos(int numeroCuenta, String nombreCuenta) {
		super(numeroCuenta, nombreCuenta);
	}

	@Override
	public double getMonto() {
		// TODO Auto-generated method stub
		return ingresos;
	}

	@Override
	public void registrarEgreso(double valor) {
		ingresos += valor;
		
	}

	@Override
	public void registrarIngreso(double valor) {
		ingresos += valor;
	}
	
}
