let form = document.querySelector("#estado-form");
let monthElement = form.elements["mes"];
let yearElement = form.elements["anio"];
var currDate = new Date();

function getDateRange(month, year) {
	var startDate = new Date(year, month-1, 1);
	var endDate = new Date(year, month, 0);
	return {
		start: startDate,
		end: endDate
	}
}

function formatter(date) {
	var month = date.getMonth()+1;
	var day = date.getDate();
	return `${date.getFullYear()}-${(month <= 9)? "0"+month: month}-${(day <= 9)? "0"+day: day}`;
}


for(var i = 1900; i <= currDate.getFullYear(); i++) {
	var option = document.createElement("option");
	option.value = "" + i;
	option.textContent = "" + i; 
	yearElement.appendChild(option);	
}

function setSelectedDate() {
	var year = Number(yearElement.value);
	var month = Number(monthElement.value);
	var range = getDateRange(month, year);
	form.elements["fechaInicial"].value = formatter(range.start);
	form.elements["fechaFinal"].value = formatter(range.end);
}

yearElement.addEventListener('change', setSelectedDate, false);
monthElement.addEventListener('change', setSelectedDate, false);

monthElement.value = "" + (currDate.getMonth() + 1);
yearElement.value = "" + currDate.getFullYear();
setSelectedDate();


form.addEventListener('submit', (e) => {
	e.preventDefault();
	
	fetch(form.action, {
		method: form.method,
		body: new URLSearchParams(new FormData(form))
	}).then(response => response.json())
	.then(data => {
		cargarCuentas(data.cuentasEgresos,"#cuentasEgresos");
		cargarCuentas(data.cuentasIngresos,"#cuentasIngresos");
		cargarCuentas(data.cuentasIngresoEgreso,"#cuentasIngresoEgreso");
	});
	
}, false); 

function cargarCuentas(cuentas, id) {
	var content = '';
	cuentas.forEach(cuenta => {
		var monto = (cuenta.monto < 0)? `-$${Math.abs(cuenta.monto)}` : `$${cuenta.monto}`;
		content += `<h3>${cuenta.nombreCuenta}</h3><p>Total: ${monto}</p>`;
	});
	
	document.querySelector(id).innerHTML = content;
}

