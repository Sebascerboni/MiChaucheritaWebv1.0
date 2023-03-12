package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EstadoCuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<CuentaIngresos> cuentasIngresos;
	private List<CuentaEgresos> cuentasEgresos;
	private List<CuentaIngresoEgreso> cuentasIngresoEgreso;
	
	public EstadoCuenta() {
		cuentasIngresoEgreso = new ArrayList<>();
		cuentasIngresos = new ArrayList<>();
		cuentasEgresos = new ArrayList<>();
	}
	
	public EstadoCuenta(List<Transaccion> transacciones) {
		this();
		HashMap<Integer, CuentaIngresos> mapCuentasIngresos = new HashMap<>();
		HashMap<Integer, CuentaEgresos> mapCuentasEgresos = new HashMap<>();
		
		//Cargar primero todas las cuentas existentes
		for(Cuenta c : new CuentaDAO().getCuentas()) {
			if(c instanceof CuentaIngresoEgreso) {
				cuentasIngresoEgreso.add((CuentaIngresoEgreso) c);
			}
			if(c instanceof CuentaIngresos) { //Ingresos en cero dado que se van a calcular con las transacciones
				CuentaIngresos cuenta = new CuentaIngresos();
				cuenta.setNumeroCuenta(c.getNumeroCuenta());
				cuenta.setNombreCuenta(c.getNombreCuenta());
				mapCuentasIngresos.put(cuenta.getNumeroCuenta(),cuenta);
			}
			if(c instanceof CuentaEgresos) {
				CuentaEgresos cuenta = new CuentaEgresos();
				cuenta.setNumeroCuenta(c.getNumeroCuenta());
				cuenta.setNombreCuenta(c.getNombreCuenta());
				mapCuentasEgresos.put(c.getNumeroCuenta(), cuenta);
			}
		}
		
		//Para cada transaccion 
		for(Transaccion t : transacciones) {
			Cuenta origen = mapCuentasIngresos.get(t.getOrigen().getNumeroCuenta());
			Cuenta destino = mapCuentasEgresos.get(t.getDestino().getNumeroCuenta());
			if(origen != null && !(origen instanceof CuentaIngresoEgreso)) {
				origen.registrarEgreso(t.getMonto());
			}
			if(destino != null && !(destino instanceof CuentaIngresoEgreso)) {
				destino.registrarIngreso(t.getMonto());
			}
			
		}
		for(Integer i : mapCuentasIngresos.keySet()) {
			cuentasIngresos.add(mapCuentasIngresos.get(i));
		}
		for(Integer i : mapCuentasEgresos.keySet()) {
			cuentasEgresos.add(mapCuentasEgresos.get(i));
		}
	}

	public List<CuentaIngresos> getCuentasIngresos() {
		return cuentasIngresos;
	}

	public void setCuentasIngresos(List<CuentaIngresos> cuentasIngresos) {
		this.cuentasIngresos = cuentasIngresos;
	}

	public List<CuentaEgresos> getCuentasEgresos() {
		return cuentasEgresos;
	}

	public void setCuentasEgresos(List<CuentaEgresos> cuentasEgresos) {
		this.cuentasEgresos = cuentasEgresos;
	}

	public List<CuentaIngresoEgreso> getCuentasIngresoEgreso() {
		return cuentasIngresoEgreso;
	}

	public void setCuentasIngresoEgreso(List<CuentaIngresoEgreso> cuentasIngresoEgreso) {
		this.cuentasIngresoEgreso = cuentasIngresoEgreso;
	}

	@Override
	public String toString() {
		return "{\"cuentasIngresos\":" + cuentasIngresos + ", \"cuentasEgresos\":" + cuentasEgresos
				+ ", \"cuentasIngresoEgreso\":" + cuentasIngresoEgreso + "}";
	}
	

}
