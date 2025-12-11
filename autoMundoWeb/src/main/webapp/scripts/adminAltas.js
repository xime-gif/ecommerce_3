
const ventanaModal = document.getElementById('imageSelectorModal');
const botonCerrar = document.getElementById('closeModal');
const tituloModal = document.getElementById('modalTitle');
const botonAlternarVista = document.getElementById('toggleViewButton');


const botonesSeleccionarArchivo = document.querySelectorAll('.btn-select-file');

const opcionesImagen = document.querySelectorAll('.modal-img-option');
const gridVehiculos = document.getElementById('vehicleImagesGrid');
const gridIconos = document.getElementById('iconImagesGrid');


let campoDestinoActual = null;



botonesSeleccionarArchivo.forEach(boton => {
    boton.addEventListener('click', function () {
        const idCampoDestino = this.dataset.fieldTarget;
        const tipoSeleccion = this.dataset.type;


        document.querySelectorAll('.modal-img-option').forEach(opcion => opcion.classList.remove('selected-image'));


        campoDestinoActual = document.getElementById(idCampoDestino);


        if (tipoSeleccion === 'vehicle') {
            gridVehiculos.style.display = 'flex';
            gridIconos.style.display = 'none';
            botonAlternarVista.textContent = 'Cambiar a Iconos';
            tituloModal.textContent = 'Seleccionar Imagen Principal';
        } else { // tipoSeleccion === 'icon'
            gridVehiculos.style.display = 'none';
            gridIconos.style.display = 'flex';
            botonAlternarVista.textContent = 'Cambiar a Imágenes de Vehículo';
            tituloModal.textContent = 'Seleccionar Icono';
        }


        ventanaModal.style.display = 'block';
    });
});

botonCerrar.addEventListener('click', () => {
    ventanaModal.style.display = 'none';
});
window.addEventListener('click', (evento) => {
    if (evento.target === ventanaModal) {
        ventanaModal.style.display = 'none';
    }
});


botonAlternarVista.addEventListener('click', () => {

    if (gridVehiculos.style.display === 'flex') {
        gridVehiculos.style.display = 'none';
        gridIconos.style.display = 'flex';
        botonAlternarVista.textContent = 'Cambiar a Imágenes de Vehículo';
    } else {
        gridVehiculos.style.display = 'flex';
        gridIconos.style.display = 'none';
        botonAlternarVista.textContent = 'Cambiar a Iconos';
    }
});


opcionesImagen.forEach(opcion => {

    opcion.addEventListener('click', function () {
        if (campoDestinoActual) {
            const urlImagen = this.dataset.url;
            const idCampoOculto = campoDestinoActual.id;


            campoDestinoActual.value = urlImagen;


            const elementoVisualRuta = document.getElementById('display-' + idCampoOculto);

            if (elementoVisualRuta) {
                elementoVisualRuta.value = urlImagen;
            }


            ventanaModal.style.display = 'none';
        }
    });


    opcion.addEventListener('click', function () {

        document.querySelectorAll('.modal-img-option').forEach(opt => opt.classList.remove('selected-image'));

        this.classList.add('selected-image');
    });
});


const formulario = document.querySelector('.form-alta');
formulario.addEventListener('submit', (evento) => {
    const campoImagenPrincipal = document.getElementById('selectedImageURL');

    if (campoImagenPrincipal.value === "") {
        evento.preventDefault();
        alert("Por favor, selecciona una imagen principal para el vehículo.");
    }
});