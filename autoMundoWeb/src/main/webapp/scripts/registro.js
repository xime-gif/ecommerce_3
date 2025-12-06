document.addEventListener("DOMContentLoaded", () => {

    const form = document.querySelector(".formulario-registro");
    const boton = document.getElementById("btn-registrar");

    form.addEventListener("submit", (e) => {

        boton.disabled = true;
        boton.style.opacity = "0.6";
        boton.style.cursor = "not-allowed";
        
        boton.textContent = "Procesando...";

    });

});
