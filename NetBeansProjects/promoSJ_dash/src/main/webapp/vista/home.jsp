<%-- 
    Document   : home
    Created on : 12 oct 2024, 12:47:11
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="css/style.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - Comercio</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <!-- Sidebar -->
                <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-dark">
                    <div class="position-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link active" href="home.jsp">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="infoComercio.jsp">Información del Comercio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="promociones.jsp">Promociones</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="crearPromocion.jsp">Crear Nueva Promoción</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="estadisticas.jsp">Estadísticas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="LogoutServlet">Cerrar Sesión</a>
                            </li>
                        </ul>
                    </div>
                </nav>

                <!-- Contenido principal -->
                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="welcome-header mb-3">
                        <h1 class="h2">Bienvenido, ${comercio.nombre}</h1>
                    </div>

                    <!-- Listar promociones del comercio -->
                    <div>
                        <h4>Tus Promociones Actuales</h4>
                        <c:forEach var="promocion" items="${promociones}">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">${promocion.titulo}</h5>
                                    <p class="card-text">${promocion.descripcion}</p>
                                    <a href="editarPromocion.jsp?id=${promocion.idPromocion}" class="btn btn-primary">Editar</a>
                                    <a href="eliminarPromocionServlet?id=${promocion.idPromocion}" class="btn btn-danger">Eliminar</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </main>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
