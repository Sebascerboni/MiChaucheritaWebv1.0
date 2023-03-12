<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/base.css" media="all" />
<link rel="stylesheet" href="css/lib.css" media="all" />
<link rel="stylesheet" href="css/estadocuenta.css" media="all" />
<link rel="icon" href="icon.png" type="image/png">
<title>Estado de cuenta</title>
</head>
<body>
	<header class="flex-row jc-center ai-center">
    	<img src="icon.png" alt="logo" class="logo mr-2"/>
		<h1 class="my-5">Mi Chaucherita Web</h1>
	</header>
	<main class="w-100 flex-column ai-center">
		<div class="m-2">
	        <form action="EstadoCuentaController?ruta=mostrarEstado&usarApi=1" method="post" id="estado-form">
	            <input type="hidden" name="fechaInicial" id=""/>
	            <input type="hidden" name="fechaFinal" id=""/>
	            <select name="mes" id="" >
	            	<option value="1">Enero</option>
	            	<option value="2">Febrero</option>
	            	<option value="3">Marzo</option>
	            	<option value="4">Abril</option>
	            	<option value="5">Mayo</option>
	            	<option value="6">Junio</option>
	            	<option value="7">Julio</option>
	            	<option value="8">Agosto</option>
	            	<option value="9">Septiembre</option>
	            	<option value="10">Octubre</option>
	            	<option value="11">Noviembre</option>
	            	<option value="12">Diciembre</option>           	
	            </select>
	            <select name="anio" id="">
	            
	            </select>
	            <input type="submit" value="Consultar" class="action-btn"/>
	        </form>
    	</div>
    	<h2 class=""> Estado de Cuenta</h2>
		<div class="col-3 container-cuentas">
	        <div class="br-gray m-3 p-3 rounded-1 shadow">
	            <h2 class="jc-center flex-row">Cuentas de Entrada y Salida</h2>
	            <hr class="mb-3"/>
	            <div class="descripcion" id="cuentasIngresoEgreso">
	            <c:forEach items="${estadoCuenta.cuentasIngresoEgreso}" var="cuenta">
	                <div>
	                    <h3 class="txt-center flex-row">${cuenta.nombreCuenta}</h3>
	                    <p>Total: $${cuenta.monto}</p>
	                </div>                
	            </c:forEach>
	            </div>                
	        </div>
	    
	        <div class="br-gray m-3 p-3 rounded-1 shadow">
	            <h2 class="jc-center flex-row">Cuentas de Gastos</h2>
	            <hr class="mb-3"/>
	            <div class="descripcion" id="cuentasEgresos">
	            <c:forEach items="${estadoCuenta.cuentasEgresos}" var="cuenta">
	                <div>
	                    <h3 class="txt-center flex-row"">${cuenta.nombreCuenta}</h3>
	                    <p>Total: $${cuenta.monto}</p>
	                </div>          
	            </c:forEach>
	            </div>       
	        </div>
	
	        <div class="br-gray m-3 p-3 rounded-1 shadow">
				<h2 class="jc-center flex-row">Cuentas de Ingreso</h2>
	            <hr class="mb-3"/>
	            <div class="descripcion" id="cuentasIngresos">
	            <c:forEach items="${estadoCuenta.cuentasIngresos}" var="cuenta">
					<div>
	                    <h3 class="txt-center flex-row jc-center">${cuenta.nombreCuenta}</h3>
	                    <p class="">Total: $${cuenta.monto}</p>
	                </div>                 
	            </c:forEach>
	            </div>       
	        </div>		
		
		</div>
		<a href="inicio" class="m-5">Regresar</a>
	</main>
    <footer id="footer" class="p-5">
        <h3>Terminos y Condiciones</h3>
        <p class="my-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quidem, voluptas provident!
            Corrupti mollitia veritatis, pariatur eligendi autem rerum ipsum facere temporibus adipisci aut maxime
            necessitatibus officiis alias vel tenetur odio?</p>
        <p>Grupo 2 | Proyecto Mi Chaucherita WEB | <a href="https://www.epn.edu.ec" class="txt-color-white txt-plain">Escuela Politécnica
                Nacional</a></p>
    </footer>
    <script src="js/estadocuenta.js"></script>
</body>
</html>