<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/base.css" />
    <link rel="stylesheet" href="css/lib.css" />
    <link rel="stylesheet" href="css/transacciones-home.css" />
    <link rel="icon" href="icon.png" type="image/png">
    <title>Movimientos</title>
    
</head>

<body class="p-relative">
	
    <header class="flex-row jc-center ai-center">
    	<img src="icon.png" alt="logo" class="logo mr-2"/>
		<h1 class="my-5">Mi Chaucherita Web</h1>
	</header>
    <main class="w-100 flex-column ai-center px-4 mb-4">
        <div class="br-gray rounded-1 p-3 m-3 shadow w-100">
            <h2 class="mb-2">Consultar movimientos</h2>
            <form action="GestionarMovimientosController?ruta=listarTransaccion&usarApi=1" method="post" id="form-busqueda">
                    <label for="fechaInicial">Fecha inicial: </label>
                    <input type="date" name="fechaInicial" id="" />
                    <label for="fechaFinal">Fecha Final: </label>
                    <input type="date" name="fechaFinal" id="" />
                    <input type="submit" value="Consultar" class="action-btn"/>
            </form>
        </div>
        <div class="br-gray rounded-1 p-3 shadow flex-column w-100" id="table-transaccion">
            
            <div>
                <h2 class="mb-2">Movimientos realizados entre cuentas</h2> 
                <button class="btn my-2 action-btn" id="btn-realizar-mov" onclick="showInputDialog()">Realizar movimiento</button>
            </div>
            <table cellspacing="0" class="w-100" id="table-transacciones">
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
        </div>
        <a href="inicio" class="m-3">Regresar</a>
    </main>

    <div class="popup-layer hidden" id="popup-layer">
        <div class="br-gray rounded-1 p-3 m-3 shadow flex-column w-fit bg-white ai-center">
            <h2 class="mb-2">Realizar movimiento</h2>
            <form action="GestionarMovimientosController?ruta=registroTransaccion&usarApi=1" method="post" class="w-fit" id="form-transaccion">
                    <div class="w-fit flex-column ai-center">
                        <div class="m-1">
                            <label for="cuentaOrigen">Cuenta Origen: </label>
                            <select name="cuentaOrigen" required>
                                <c:forEach items="${cuentasOrigen}" var="cuenta">
                                    <option value="${cuenta.numeroCuenta}">${cuenta.nombreCuenta}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="m-1">
                            <label for="cuentaDestino">Cuenta Destino: </label>
                            <select name="cuentaDestino" required>
                                <c:forEach items="${cuentasDestino}" var="cuenta">
                                    <option value="${cuenta.numeroCuenta}">${cuenta.nombreCuenta}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="m-1">
                            <label for="monto">Monto: </label>
                            <input type="number" name="monto" id="" min="0.0" step="0.01" required placeholder="Ej: 150.35"/>
                        </div>
                        <div class="m-1">
                            <label for="fecha">Fecha: </label>
                            <input type="date" name="fecha" id="" required/>
                        </div>
                        <div class="m-1 flex-column ai-center">
                            <label for="concepto">Concepto: </label>
                            <input type="text" name="concepto" id="" required class="w-100" placeholder="Ej: Pago de luz"/>
                        </div>
                        <div class="flex-row">
	                        <input type="submit" value="Guardar" class="mx-1 action-btn"/>	             
                        </div>
                    </div>           
            </form>
            <button class="btn mt-1 action-btn cancel" onclick="hideInputDialog()">Cancelar</button>
        </div>
    </div>
    
	<footer id="footer">
        <h3>Terminos y Condiciones</h3>
        <p class="my-2">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quidem, voluptas provident!
            Corrupti mollitia veritatis, pariatur eligendi autem rerum ipsum facere temporibus adipisci aut maxime
            necessitatibus officiis alias vel tenetur odio?</p>
        <p>Grupo 2 | Proyecti Mi Chaucherita WEB | <a href="https://www.epn.edu.ec" class="txt-color-white txt-plain">Escuela Politécnica
                Nacional</a></p>
    </footer>
    <script src="js/transacciones.js" type="module"></script>
    <script>
	    function showInputDialog() {
	        document.querySelector("#popup-layer").classList.toggle("hidden", false);
	    }
	
	    function hideInputDialog() {
	        document.querySelector("#popup-layer").classList.toggle("hidden", true);
	    }
    </script>
</body>
</html>