<%-- 
    Document   : perfil
    Created on : 13 oct 2024, 16:23:04
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="css/style.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Perfil</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">

                <!-- Sidebar -->
                <%@ include file="navbar.jsp" %>

                <!-- Contenido principal -->
                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="container mt-5">
                        <div class="welcome-header mb-3">
                            <h1 class="h2">Bienvenido, ${comercio.nombre}</h1>
                            <br>
                            <h3>Gestión del Perfil</h3>
                        </div>

                        <!-- Mostrar el mensaje si existe -->
                        <c:if test="${not empty sessionScope.mensaje}">
                            <div class="alert alert-${sessionScope.tipoMensaje} alert-dismissible fade show" role="alert">
                                ${sessionScope.mensaje}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>

                        <!-- Formulario para editar datos del comercio -->
                        <form action="actualizarPerfil" method="POST">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre del Comercio</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" value="${comercio.nombre}" required>
                            </div>

                            <div class="mb-3">
                                <label for="direccion" class="form-label">Dirección</label>
                                <input type="text" class="form-control" id="direccion" name="direccion" value="${comercio.direccion}" required>
                            </div>

                            <div class="mb-3">
                                <label for="telefono" class="form-label">Teléfono</label>
                                <input type="text" class="form-control" id="telefono" name="telefono" value="${comercio.telefono}" required>
                            </div>

                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" value="${comercio.idUsuario.email}" required>
                            </div>

                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descripción</label>
                                <textarea class="form-control" id="descripcion" name="descripcion" rows="3">${comercio.descripcion}</textarea>
                            </div>

                            <div class="mb-3">
                                <label for="horarios" class="form-label">Horarios</label>
                                <input type="text" class="form-control" id="horarios" name="horarios" value="${comercio.horarios}" required>
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>

                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                        </form>
                    </div>
                </main>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
