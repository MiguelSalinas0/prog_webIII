<%-- 
    Document   : estadisticas
    Created on : 13 oct 2024, 17:16:28
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="css/style.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Estadísticas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <!-- Sidebar -->
                <%@ include file="navbar.jsp" %>

                <!-- Contenido principal -->
                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="welcome-header mb-3">
                        <h1 class="h2">Bienvenido, ${comercio.nombre}</h1>
                        <br>
                        <h3>Estadísticas</h3>
                    </div>

                </main>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
