console.log("LexTech AI iniciado correctamente.");
const pdf = document.getElementById("pdf");

if(pdf){

pdf.addEventListener("change",function(){

const archivo = pdf.files[0];

if(archivo){

document.getElementById("nombreArchivo").innerHTML=

archivo.name;

}

});

}