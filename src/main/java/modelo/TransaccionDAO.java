package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO {
	private static List<Transaccion> transacciones;
	
	public Transaccion getTransaccion(int id) {
		return null;
	}
	
	public List<Transaccion> getTransacciones() {
		
		if(transacciones == null) {
			transacciones = new ArrayList<>();
			//1 - 3 Nomina a banco
			transacciones.add(realizarTransaccion(1,1,3,1000,"Pago de nómina de mi trabajo", LocalDate.of(2022, 12, 31)));
			//3 - 4 Banco a Universidad
			transacciones.add(realizarTransaccion(2,3,5,100,"Libro \"Contabilidad básica\"", LocalDate.of(2023, 1, 16)));
			//3 - 4 Banco a Efectivo
			transacciones.add(realizarTransaccion(3,3,4,400,"Traspaso entre cuentas", LocalDate.of(2023, 1, 20)));
			//4 - 2 Efectivo a regalo
			transacciones.add(realizarTransaccion(4,4,2,350,"Teléfono Celular para mamá", LocalDate.of(2023, 1, 20)));
		}
		return transacciones;
	}
	
	private Transaccion realizarTransaccion(int id, int origen, int destino, double monto, String concepto, LocalDate fecha) {
		CuentaDAO modeloCuenta = new CuentaDAO();
		Cuenta origenC = modeloCuenta.getCuenta(origen);
		origenC.registrarEgreso(monto);
		Cuenta destinoC = modeloCuenta.getCuenta(destino);
		destinoC.registrarIngreso(monto);
		return new Transaccion(id, origenC, destinoC, concepto, monto,fecha);
		
	}
	
	public List<Transaccion> getTransacciones(LocalDate inicio, LocalDate fin) {
		List<Transaccion> rangoTransacciones = new ArrayList<>(); 
		for(Transaccion t : getTransacciones()) {
			if(t.getFecha().isEqual(inicio) || t.getFecha().isEqual(fin) || 
					(t.getFecha().isAfter(inicio) && t.getFecha().isBefore(fin))) {
				rangoTransacciones.add(t);
			}
		}
		return rangoTransacciones;
	}
	
	public void updateTransaccion(Transaccion transaccion) {
		
	}
	
	public void registrarTransaccion(Transaccion transaccion) {
		int maxId = 0;
		for(Transaccion t : getTransacciones()) {
			if (t.getId() > maxId) {
				maxId = t.getId();
			}
		}
		transaccion.setId(maxId + 1);
		getTransacciones().add(transaccion);
	}

}
