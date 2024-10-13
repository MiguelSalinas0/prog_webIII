<%-- 
    Document   : SolicitarDatosCarrera
    Created on : 13 oct 2024, 15:19:00
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitar Datos Carrera</title>

    </head>
    <body>
        <h2>Registrar Nueva Carrera</h2>
        <form action="RegistrarCarrera" method="post">
            <label for="nombre">Nombre de la Carrera:</label><br>
            <input type="text" id="nombre" name="nombre" required><br><br>

            <label for="facultad">Seleccionar Facultad:</label><br>
            <select id="facultad" name="facultad" required>
                <option value="">Seleccionar...</option>
                <!-- Itera sobre la lista de facultades -->
                <c:forEach var="facultad" items="${listaFacultades}">
                    <option value="${facultad.idfacultad}">${facultad.nombre}</option>
                </c:forEach>
            </select><br><br>

            <input type="submit" value="Registrar">
        </form>
        <a href="./index.jsp" title="Ir la página anterior">Volver</a>
    </body>
</html>
