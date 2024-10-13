<%-- 
    Document   : SolicitarDatosFacultad
    Created on : 13 oct 2024, 15:19:12
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitar Datos Facultad</title>
    </head>
    <body>
        <h2>Registrar Nueva Facultad</h2>
        <form action="RegistrarFacultad" method="post">
            <label for="nombre">Nombre de la Facultad</label><br>
            <input type="text" id="nombre" name="nombre" required><br><br>
            <input type="submit" value="Registrar">
        </form>
        <a href="./index.jsp" title="Ir la página anterior">Volver</a>
    </body>
</html>
