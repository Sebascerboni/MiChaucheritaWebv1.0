import { getDateRange, formatter } from './funciones.js';

function showInputDialog() {
    document.querySelector("#popup-layer").classList.toggle("hidden", false);
}

function hideInputDialog() {
    document.querySelector("#popup-layer").classList.toggle("hidden", true);
}


let form = document.querySelector("#form-transaccion");
form.elements["fecha"].value = formatter(new Date());
let formBusqueda = document.querySelector("#form-busqueda");

form.addEventListener('submit', (e) => {
	e.preventDefault();
	hideInputDialog();
	fetch(form.action, {
		method: form.method,
		body: new URLSearchParams(new FormData(form))
	}).then(response => response.json())
	.then(data => {
		llenarTabla(data);
	});
}, false);

formBusqueda.addEventListener('submit', (e) => {
	e.preventDefault();
	fetch(formBusqueda.action, {
		method: form.method,
		body: new URLSearchParams(new FormData(formBusqueda))
	}).then(response => response.json())
	.then(data => {
		llenarTabla(data);
	});
}, false);

function llenarTabla(data) {
	var table = document.querySelector("#table-transacciones");
		var content = `<tr>
                    <th>Id</th>
                    <th>Origen</th>
                    <th>Destino</th>
                    <th>Concepto</th>
                    <th>Monto</th>
                    <th>Fecha</th>
                </tr>`;
		data.forEach(transaccion => {
			content += `<tr>
                        <td>${transaccion.id}</td>
                        <td>${transaccion.origen.nombreCuenta}</td>
                        <td>${transaccion.destino.nombreCuenta}</td>
                        <td>${transaccion.concepto}</td>
                        <td>${transaccion.monto}</td>
                        <td>${transaccion.fecha}</td>
                    </tr>`;
		});
		table.innerHTML = content;
}
