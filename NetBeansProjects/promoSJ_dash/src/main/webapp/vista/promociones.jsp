<%-- 
    Document   : promociones
    Created on : 13 oct 2024, 16:36:19
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
        <title>Promociones</title>
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
                            <h3>Gestión de Promociones</h3>
                        </div>

                        <!-- Mostrar el mensaje si existe -->
                        <c:if test="${not empty sessionScope.mensaje}">
                            <div class="alert alert-${sessionScope.tipoMensaje} alert-dismissible fade show" role="alert">
                                ${sessionScope.mensaje}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>

                        <!-- Botón para crear una nueva promoción -->
                        <div class="mb-3">
                            <a href="CrearPromocion" class="btn btn-success">Crear Nueva Promoción</a>
                        </div>

                        <!-- Listar promociones del comercio -->
                        <div>
                            <h4>Tus Promociones Actuales</h4>
                            <c:forEach var="promocion" items="${promociones}">
                                <div class="card mb-3">
                                    <div class="card-body">
                                        <h5 class="card-title">${promocion.titulo}</h5>
                                        <p class="card-text">${promocion.descripcion}</p>
                                        <a href="EditarPromocion?id=${promocion.idPromocion}" class="btn btn-primary">Editar</a>
                                        <a href="EliminarPromocionServlet?id=${promocion.idPromocion}" class="btn btn-danger">Eliminar</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
