document.addEventListener("DOMContentLoaded", () => {

    console.log("bajas.js cargado");

    const botonesEliminar = document.querySelectorAll('.js-delete-vehicle');
    console.log("Botones encontrados:", botonesEliminar.length);

    botonesEliminar.forEach(btn => {

        btn.addEventListener('click', async function () {

            const id = this.dataset.id;
            console.log("Click en ID:", id);

            if (!confirm(`¿Eliminar una unidad del auto con ID ${id}?`))
                return;

            try {
                const respuesta = await fetch(`AdminBajas?id=${id}`, {
                    method: "DELETE"    
                });

                if (respuesta.ok) {
                    const stockEl = this.closest('.car-info').querySelector('.car-stock');
                    let existencias = parseInt(stockEl.textContent.replace(/\D/g, ''), 10);

                    existencias = existencias - 1;

                    if (existencias > 0) {
                        stockEl.textContent = `Disponibles: ${existencias}`;
                    } else {
                        this.closest('.car-card').remove();
                        alert("Se ha eliminado el último vehículo de este modelo.");
                    }

                } else {
                    alert("Error al eliminar el vehículo.");
                }

            } catch (e) {
                console.error("Error de conexión:", e);
                alert("Error de conexión.");
            }
        });

    });

});
