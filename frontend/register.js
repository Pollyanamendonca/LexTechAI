console.log("Register.js cargado correctamente");

const formulario = document.getElementById("registroForm");

formulario.addEventListener("submit", async function(event){

    event.preventDefault();

    const usuario = {

        nombre: document.getElementById("nombre").value,

        email: document.getElementById("email").value,

        password: document.getElementById("password").value

    };

    const respuesta = await fetch("http://localhost:8081/api/usuarios", {

        method: "POST",

        headers: {

            "Content-Type":"application/json"

        },

        body: JSON.stringify(usuario)

    });

    if(respuesta.ok){

        document.getElementById("mensaje").innerHTML =
        "✅ Usuario registrado correctamente";

        formulario.reset();

    }else{

        document.getElementById("mensaje").innerHTML =
        "❌ Error al registrar";

    }

});