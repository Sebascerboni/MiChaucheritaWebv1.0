package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.EstadoCuenta;
import modelo.Transaccion;
import modelo.TransaccionDAO;

/**
 * Servlet implementation class EstadoCuentaController
 */
@WebServlet("/EstadoCuentaController")
public class EstadoCuentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EstadoCuentaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") == null)? "inicioEstadoCuenta" : request.getParameter("ruta");
		switch(ruta) {
			case "inicioEstadoCuenta":
				mostrarInicio(request, response);
				break;
			case "mostrarEstado":
				mostrarEstado(request, response);
			
		}
	}
	
	private void mostrarInicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Obtener valores
		//2. Llamar al modelo
		//3. LLamar a la vista
		request.setAttribute("estadoCuenta", null);
		request.getRequestDispatcher("jsp/inicioEstadoCuenta.jsp").forward(request, response);
		
	}
	private void mostrarEstado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Obtener valores
		String fechaInicioStr = request.getParameter("fechaInicial");
		String fechaFinalStr = request.getParameter("fechaFinal");
		boolean useApi = request.getParameter("usarApi") != null;
		
		LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate fechaFinal = LocalDate.parse(fechaFinalStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//2. Llamar al modelo
		
		TransaccionDAO modeloTransaccion = new TransaccionDAO();
		List<Transaccion> transacciones = modeloTransaccion.getTransacciones(fechaInicio, fechaFinal);
		EstadoCuenta estadoCuenta = new EstadoCuenta(transacciones);
		//3. LLamar a la vista
		if(useApi) {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(estadoCuenta);
		}else {
			request.setAttribute("estadoCuenta", estadoCuenta);
			request.getRequestDispatcher("jsp/inicioEstadoCuenta.jsp").forward(request, response);
		}
	}

}
