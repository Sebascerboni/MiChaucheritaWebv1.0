<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/base.css" media="all" />
<link rel="stylesheet" href="css/inicio.css" media="all" />
<link rel="stylesheet" href="css/lib.css" media="all" />
<link rel="icon" href="icon.png" type="image/png">
<title>Inicio</title>
</head>
<body>
    <header class="flex-row jc-center ai-center">
    	<img src="icon.png" alt="logo" class="logo mr-2"/>
		<h1 class="my-5">Mi Chaucherita Web</h1>
	</header>
	<main class="w-100 flex-column ai-center mb-5">
		<div class="col-2 w-fit">
	        <div class="card shadow">
	        	<h2 class="m-1">Movimientos</h2>
	        	<div class="card-icon">
		        	<a href="GestionarMovimientosController">
		        		<img src="img/transaccion2.jpg" alt="transaccion">
		        	</a>
	        	</div>
	        	<p class="txt-justify">En este apartado será capaz de realizar una transacción hacia cualquier cuenta que desee.
	               Además, podrá ver el historial de las transacciones realizadas en un rango de fechas específico.</p>
	        </div>
	        <div class="card shadow">
	            <h2 class="m-1">Estado de Cuenta</h2>
	            <div class="card-icon">
	            	<a href="EstadoCuentaController">
	            		<img src="img/estadoCuenta.jpg" alt="transaccion">
	            	</a>
	            </div>
	            <p class="txt-justify">En este apartado será capaz de generar el estado de cuenta de un mes y año específico. 
	            Podrá revisar el estado actual de sus cuentas</p>
	        </div>
    	</div>
	</main>       
    <footer id="footer" class="p-5">
        <h3>Terminos y Condiciones</h3>
        <p class="my-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quidem, voluptas provident!
            Corrupti mollitia veritatis, pariatur eligendi autem rerum ipsum facere temporibus adipisci aut maxime
            necessitatibus officiis alias vel tenetur odio?</p>
        <p>Grupo 2 | Proyecti Mi Chaucherita WEB | <a href="https://www.epn.edu.ec" class="txt-color-white txt-plain">Escuela Politécnica
                Nacional</a></p>
    </footer>

</body>
</html>