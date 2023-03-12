package modelo;

import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {
	private static List<Cuenta> cuentas = null;
	
	public Cuenta getCuenta(int id) {
		for (Cuenta cuenta : getCuentas()) {
			if(cuenta.getNumeroCuenta()==id) {
				return cuenta;
			}
		}
		return null;
	}

	public List<Cuenta> getCuentas() {
		if(cuentas == null) {
			cuentas = new ArrayList<Cuenta>();
			cuentas.add(new CuentaIngresos(1,"Nómina"));
			cuentas.add(new CuentaEgresos(2,"Regalo"));
			cuentas.add(new CuentaEgresos(5,"Universidad"));
			cuentas.add(new CuentaIngresoEgreso(3,"Banco"));
			cuentas.add(new CuentaIngresoEgreso(4,"Efectivo"));
		}
		return cuentas;
	}

		
	
}
