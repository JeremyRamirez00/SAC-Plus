


// VOLVER AL LOGIN
function volver() {
    window.location.href = "index.html";
}

// REGISTRAR SOLICITUD
function registrar() {
    let nombre = document.getElementById("nombre").value;
    let fecha = document.getElementById("fecha").value;
    let equipo = document.getElementById("equipo").value;
    let observaciones = document.getElementById("observaciones").value;
    let estado = document.getElementById("estado").value;

    if (nombre === "" || fecha === "" || equipo === "" || estado === "") {
        alert("Por favor complete los campos obligatorios");
        return;
    }

    let tabla = document.getElementById("tabla").getElementsByTagName("tbody")[0];
    let fila = tabla.insertRow();

    fila.insertCell(0).innerText = nombre;
    fila.insertCell(1).innerText = fecha;
    fila.insertCell(2).innerText = equipo;
    fila.insertCell(3).innerText = observaciones;
    fila.insertCell(4).innerText = estado;

    limpiarFormulario();
}

function limpiarFormulario() {
    document.getElementById("nombre").value = "";
    document.getElementById("fecha").value = "";
    document.getElementById("equipo").value = "";
    document.getElementById("observaciones").value = "";
    document.getElementById("estado").value = "";
}

// SELECCIONAR FILA
let filaSeleccionada = null;

// Detectar clic en la tabla
document.getElementById("tabla").addEventListener("click", function (e) {
    e.stopPropagation();
    let fila = e.target.parentNode;

    let filas = document.querySelectorAll("#tabla tr");
    filas.forEach(f => f.classList.remove("seleccionada"));

    fila.classList.add("seleccionada");
    filaSeleccionada = fila;

    // Cargar datos al formulario
    document.getElementById("nombre").value = fila.cells[1].innerText;
    document.getElementById("fecha").value = fila.cells[2].innerText;
    document.getElementById("equipo").value = fila.cells[3].innerText;
    document.getElementById("observaciones").value = fila.cells[4].innerText;
    document.getElementById("estado").value = fila.cells[5].innerText;
});

document.body.addEventListener("click", function (e) {
    let tabla = document.getElementById("tabla");
    let formulario = document.querySelector(".form-container");

    // Si el clic NO está en tabla NI formulario
    if (!tabla.contains(e.target) && !formulario.contains(e.target)) {
        let filas = document.querySelectorAll("#tabla tr");
        filas.forEach(f => f.classList.remove("seleccionada"));

        filaSeleccionada = null;
    }
});

//ACTUALIZAR
function actualizar() {
    if (!filaSeleccionada) {
        alert("Seleccione una fila primero");
        return;
    }

    let id = filaSeleccionada.cells[0].innerText;

    let nombre = document.getElementById("nombre").value;
    let fecha = document.getElementById("fecha").value;
    let equipo = document.getElementById("equipo").value;
    let observaciones = document.getElementById("observaciones").value;
    let estado = document.getElementById("estado").value;

    fetch('/sacplus/actualizar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `id=${id}&nombre=${nombre}&fecha=${fecha}&equipo=${equipo}&observaciones=${observaciones}&estado=${estado}`
    })
    .then(() => {
        alert("Registro actualizado");
        cargarDatos();
    });
}

//FINALIZAR SOLICITUD
function finalizar() {
    if (!filaSeleccionada) {
        alert("Seleccione una fila primero");
        return;
    }

    let id = filaSeleccionada.cells[0].innerText;

    fetch('/sacplus/finalizar?id=' + id)
        .then(() => {
            alert("Estado actualizado");
            cargarDatos();
        });
}
// BUSCAR SOLICITUD
function buscar() {
    let nombre = document.getElementById("nombre").value;

    fetch('/sacplus/buscar?nombre=' + nombre)
        .then(response => response.text())
        .then(data => {

            let tabla = document.getElementById("tabla").getElementsByTagName("tbody")[0];
            tabla.innerHTML = "";

            let filas = data.split(";");

            filas.forEach(fila => {
                if (fila.trim() !== "") {

                    let datos = fila.split(",");

                    let nuevaFila = tabla.insertRow();

                    datos.forEach(dato => {
                        let celda = nuevaFila.insertCell();
                        celda.innerText = dato;
                    });
                }
            });
        });
}

function limpiarTodo() {
    // Limpiar formulario
    limpiarFormulario();

    // Quitar selección de tabla
    let filas = document.querySelectorAll("#tabla tr");
    filas.forEach(f => f.classList.remove("seleccionada"));

    filaSeleccionada = null;
}

function cargarDatos() {
    fetch('/sacplus/listar?nocache=' + new Date().getTime())
        .then(response => response.text())
        .then(data => {

            let tabla = document.querySelector("#tabla tbody");
            tabla.innerHTML = "";

            let filas = data.split(";");

            filas.forEach(fila => {
                if (fila.trim() !== "") {

                    let datos = fila.split(",");

                    let nuevaFila = tabla.insertRow();

                    datos.forEach(dato => {
                        let celda = nuevaFila.insertCell();
                        celda.innerText = dato;
                    });
                }
            });
        });
}