<%-- 
    Document   : SolicitarDatosMateria
    Created on : 13 oct 2024, 15:18:45
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitar Datos Materia</title>
    </head>
    <body>
        <h2>Registrar Nueva Materia</h2>
        <form action="RegistrarMateria" method="post">
            <label for="nombre">Nombre de la Materia:</label><br>
            <input type="text" id="nombre" name="nombre" required><br><br>
            <input type="submit" value="Registrar">
        </form>
        <a href="./index.jsp" title="Ir la página anterior">Volver</a>
    </body>
</html>
