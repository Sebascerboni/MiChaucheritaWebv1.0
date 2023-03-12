<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Estado de Cuenta</title>
</head>
<body>
	<h1>Estado de Cuenta</h1>
	<a href="index.html">Regresar</a>
	<form action="EstadoCuentaController?ruta=mostrarEstado" method="post">
		<fieldset>
			<input type="date" name="fechaInicial" id="" />
			<input type="date" name="fechaFinal" id="" />
			<input type="submit" value="Consultar" />
		</fieldset>
	</form>
	<div id="estado-container">
		<div>
			<c:forEach items="${estadoCuenta.cuentasIngresos}" var="cuenta">
				<div>
					<h3>${cuenta.nombreCuenta}</h3>
					<p>${cuenta.monto}</p>
				</div>
			</c:forEach>
		</div>
		<div>
			<c:forEach items="${estadoCuenta.cuentasEgresos}" var="cuenta">
				<div>
					<h3>${cuenta.nombreCuenta}</h3>
					<p>${cuenta.monto}</p>
				</div>
			</c:forEach>
		</div>
		<div>
			<c:forEach items="${estadoCuenta.cuentasIngresoEgreso}" var="cuenta">
				<div>
					<h3>${cuenta.nombreCuenta}</h3>
					<p>${cuenta.monto}</p>
				</div>
			</c:forEach>
		</div>
	</div>


</body>
</html>