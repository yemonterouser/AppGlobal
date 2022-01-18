// Call the dataTables jQuery plugin
$(document).ready(function() {
           cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarNameUsuario();
});
function actualizarNameUsuario(){

document.getElementById('txt-name-user').outerHTML = localStorage.email;
}
//AQUI MOSTRAMOS LA LISTA DE LOS USUARIOS
async function cargarUsuarios(){ //con esta funcion mostrasmo todos los usuarios de la tabla #usuarios

   const request = await fetch('api/usuarios', {
       method: 'GET',
       headers: getHeaders()

     });
     const usuarios = await request.json();//aqui devuelve un listado de usuarios

     let listadoHtml = '';

     for(let usuario of usuarios){
     let botonEliminar = '<a onclick="eliminarUsuario('+ usuario.id +')" href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

     let usuariosHtml = '<tr><td>'+ usuario.id +'</td><td>'
                                   + usuario.nombre +'</td><td>'
                                   + usuario.apellido +'</td><td>'
                                   + usuario.email +'</td><td>'
                                   + botonEliminar +'</td></tr>';

     listadoHtml += usuariosHtml;
     }
     //console.log(usuarios);
    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
 }
//FUNCION PARA DEVOLVER LOS VALORES CARAGDOR EN TOKENS
function getHeaders(){

    return{
         'Accept': 'application/json',
         'Content-Type': 'application/json',
         'Authorization': localStorage.token

    };
}

//funcion eliminar usuario
async function eliminarUsuario(id){

    if(!confirm('¿Desea eliminar este usuario?')){
        return;
    }
    const request = await fetch('api/usuarios/' + id, {
           method: 'DELETE',
           headers: getHeaders()
    });

    location.reload()
}