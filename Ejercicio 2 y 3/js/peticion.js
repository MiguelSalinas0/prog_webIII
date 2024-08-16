// CONSULTAR TODOS LOS USUARIOS
$("#boton-usuarios").on("click", function () {
    getUsers()
})

function getUsers() {
    "use strict";
    $.get('https://reqres.in/api/users', function (respuesta) {
        var listaUsuarios = $("#lista-usuarios");
        $.each(respuesta.data, function (index, elemento) {
            listaUsuarios.append(
                '<div>' + '<p>' + elemento.first_name + ' ' + elemento.last_name + '</p>' + '<img src=' + elemento.avatar + '></img>' +
                '</div>'
            );
        });
    });
}


// CONSULTAR UN USUARIO
$("#boton-usuario").on("click", function () {
    var nroSocio = $("#nro-socio").val();

    if (nroSocio) {
        getUser(nroSocio);
    } else {
        alert("Por favor, ingresa un número de socio válido.");
    }
});

function getUser(nroSocio) {
    "use strict";
    $.get('https://reqres.in/api/users/' + nroSocio, function (respuesta) {
        var detalleUsuario = $("#detalle-usuario");
        detalleUsuario.empty();
        if (respuesta.data) {
            detalleUsuario.append(
                '<div>' +
                '<p>' + respuesta.data.first_name + ' ' + respuesta.data.last_name + '</p>' +
                '<img src="' + respuesta.data.avatar + '" alt="Avatar de ' + respuesta.data.first_name + '">' +
                '</div>'
            );
        } else {
            detalleUsuario.append('<p>Usuario no encontrado</p>');
        }
    }).fail(function () {
        alert("Error al realizar la petición. Por favor, intenta de nuevo.");
    });
}


// AGREGAR UN USUARIO
$(document).ready(function () {
    $("#form-nuevo-usuario").on("submit", function (event) {
        event.preventDefault();

        var nombre = $("#nombre").val();
        var apellido = $("#apellido").val();
        var avatar = $("#avatar").val();

        $.post('https://reqres.in/api/users', {
            nombre: nombre,
            apellido: apellido,
            avatar: avatar
        }, function (datos) {
            $("#respuesta-servidor").html(
                '<p>Usuario registrado con éxito:</p>' +
                '<p>ID: ' + datos.id + '</p>' +
                '<p>Nombre: ' + datos.nombre + ' ' + datos.apellido + '</p>' +
                '<p>Creado en: ' + datos.createdAt + '</p>'
            );
        }).fail(function () {
            $("#respuesta-servidor").html('<p>Error al registrar el usuario. Por favor, intenta de nuevo.</p>');
        });
    });
});