package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Cuenta;
import modelo.CuentaDAO;
import modelo.CuentaEgresos;
import modelo.CuentaIngresos;
import modelo.Transaccion;
import modelo.TransaccionDAO;

@WebServlet("/GestionarMovimientosController")
public class GestionarMovimientosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GestionarMovimientosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesarPeticion(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesarPeticion(request, response);
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") == null)? "inicioTransacciones" : request.getParameter("ruta");
		switch(ruta) {
			case "inicioTransacciones": 
				listarTransacciones(request, response);
				break;
			case "registroTransaccion":
				registrarTransaccion(request, response);
				break;
			case "listarTransaccion":
				listarTransacciones(request, response);
				break;
		}
	}
	
	private void registrarTransaccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Recibir datos
		String cuentaOrigen = request.getParameter("cuentaOrigen");
		String cuentaDestino = request.getParameter("cuentaDestino");
		String monto = request.getParameter("monto");
		String concepto = request.getParameter("concepto");
		double montoNumerico = Double.parseDouble(monto);
		String fecha = request.getParameter("fecha");
		boolean useApi = request.getParameter("usarApi") != null;
		//2. Llamar al modelo
		if(montoNumerico <= 0) {
			if(useApi) {
				response.setCharacterEncoding("UTF-8");
				response.sendError(400, "No se permiten montos negativos");
			}else {
				request.setAttribute("error", "No se permiten montos negativos");
				listarTransacciones(request, response);
			}
			return;
		}
		
		CuentaDAO modeloCuenta = new CuentaDAO();
		Cuenta origen = modeloCuenta.getCuenta(Integer.parseInt(cuentaOrigen));
		origen.registrarEgreso(montoNumerico);
		Cuenta destino = modeloCuenta.getCuenta(Integer.parseInt(cuentaDestino));
		destino.registrarIngreso(montoNumerico);
		TransaccionDAO modeloTransaccion = new TransaccionDAO();
		Transaccion transaccion = new Transaccion();
		transaccion.setOrigen(origen);
		transaccion.setDestino(destino);
		transaccion.setMonto(montoNumerico);
		transaccion.setConcepto(concepto);
		transaccion.setFecha(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		modeloTransaccion.registrarTransaccion(transaccion);
		//3. Llamar a la vista
		if(useApi) {
			response.sendRedirect("GestionarMovimientosController?usarApi=1");
		}else {
			response.sendRedirect("GestionarMovimientosController");
		}
		
		
		
	}
	private void listarTransacciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fechaInicioStr = request.getParameter("fechaInicial");
		String fechaFinalStr = request.getParameter("fechaFinal");
		boolean useApi = request.getParameter("usarApi") != null;
		
		TransaccionDAO modeloTransaccion = new TransaccionDAO();
		List<Transaccion> transacciones = new ArrayList<>();
		if(fechaInicioStr != null && fechaFinalStr != null) {
			LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate fechaFinal = LocalDate.parse(fechaFinalStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			transacciones = modeloTransaccion.getTransacciones(fechaInicio, fechaFinal);
		}else {
			transacciones = modeloTransaccion.getTransacciones();
		}
		
		CuentaDAO modeloCuenta = new CuentaDAO();
		List<Cuenta> cuentas = modeloCuenta.getCuentas();
		
		List<Cuenta> cuentasOrigen = new ArrayList<>();
		List<Cuenta> cuentasDestino = new ArrayList<>();
		
		for(Cuenta c: cuentas) {
			if(c instanceof CuentaIngresos) {
				cuentasOrigen.add(c);
			}else if(c instanceof CuentaEgresos) {
				cuentasDestino.add(c);
			}else {
				cuentasOrigen.add(c);
				cuentasDestino.add(c);
			}
		}
		
		if(useApi) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(transacciones);
			System.out.println(transacciones);
			return;
		}
				
		request.setAttribute("cuentasOrigen", cuentasOrigen);
		request.setAttribute("cuentasDestino", cuentasDestino);
		request.setAttribute("transacciones", transacciones);

		request.getRequestDispatcher("jsp/inicioTransacciones.jsp").forward(request, response);
	}

}
