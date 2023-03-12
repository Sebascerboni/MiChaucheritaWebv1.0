package modelo;

public class CuentaIngresoEgreso extends Cuenta{
	private double egresos;
	private double ingresos;
	
	public CuentaIngresoEgreso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CuentaIngresoEgreso(int numeroCuenta, String nombreCuenta) {
		super(numeroCuenta, nombreCuenta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getMonto() {
		// TODO Auto-generated method stub
		return ingresos - egresos;
	}

	@Override
	public void registrarEgreso(double valor) {
		egresos += valor;
		
	}

	@Override
	public void registrarIngreso(double valor) {
		ingresos += valor;
	}

}
