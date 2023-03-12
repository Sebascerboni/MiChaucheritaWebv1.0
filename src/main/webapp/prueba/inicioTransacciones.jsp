<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="ISO-8859-1">
<title>Transacciones</title>
<link rel="stylesheet" href="prueba/estilo.css" media="all" />
</head>
<body>
	<a href="inicio">Regresar</a>
	<h1>Transacciones</h1>
	<h2>Movimientos</h2>
	<form action="GestionarMovimientosController?ruta=listarTransaccion" method="post">
		<fieldset>
			<label for="fechaInicial">Fecha inicial: </label>
			<input type="date" name="fechaInicial" id="" />
			<label for="fechaFinal">Fecha Final: </label>
			<input type="date" name="fechaFinal" id="" />
			<input type="submit" value="Consultar" />
		</fieldset>
	</form>

	<table>
		<tr>
			<th>Id</th>
			<th>Origen</th>
			<th>Destino</th>
			<th>Concepto</th>
			<th>Monto</th>
			<th>Fecha</th>
		</tr>
		
		<c:forEach items="${transacciones}" var="transaccion">
			<tr>
				<td>${transaccion.id}</td>
				<td>${transaccion.origen.nombreCuenta}</td>
				<td>${transaccion.destino.nombreCuenta}</td>
				<td>${transaccion.concepto}</td>
				<td>${transaccion.monto}</td>
				<td>${transaccion.fecha}</td>
			</tr>
		</c:forEach>
	</table>

	<h2>Realizar transacción</h2>
	<form action="GestionarMovimientosController?ruta=registroTransaccion" method="post">
		<fieldset>
			<label for="cuentaOrigen">Cuenta Origen: </label>
			<select name="cuentaOrigen" required>
				<c:forEach items="${cuentasOrigen}" var="cuenta">
					<option value="${cuenta.numeroCuenta}">${cuenta.nombreCuenta}</option>
				</c:forEach>
			</select><br />
			<label for="cuentaDestino">Cuenta Destino: </label>
			<select name="cuentaDestino" required>
				<c:forEach items="${cuentasDestino}" var="cuenta">
					<option value="${cuenta.numeroCuenta}">${cuenta.nombreCuenta}</option>
				</c:forEach>
			</select><br />
			<label for="monto">Monto: </label>
			<input type="number" name="monto" id="" min="0" required/><br />
			<label for="concepto">Concepto: </label>
			<input type="text" name="concepto" id="" required/><br />
			<label for="fecha">Fecha: </label>
			<input type="date" name="fecha" id="" required/><br />
			<input type="submit" value="Guardar" />
		</fieldset>
	</form>


</body>
</html>