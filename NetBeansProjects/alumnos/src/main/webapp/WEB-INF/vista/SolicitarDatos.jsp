<%-- 
    Document   : SolicitarDatos
    Created on : 17 oct 2024, 18:35:45
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Materias de Alumno</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                margin: 0;
                padding: 20px;
            }
            h1 {
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #ddd;
            }
        </style>
    </head>
    <body>
        <h2>Consultar Materias de Alumno</h2>
        <form action="SolicitarDatos" method="post">
            <input type="text" name="numeroRegistro" placeholder="Ingrese número de registro">
            <input type="submit" value="Buscar">
        </form>


        <c:if test="${not empty materias}">
            <h3>Materias cursadas:</h3>
            <p>Total materias encontradas: ${materias.size()}</p> <!-- Agregado para depurar -->
            <table border="1">
                <tr>
                    <th>Materia</th>
                    <th>Nota</th>
                    <th>Fecha</th>
                </tr>
                <c:forEach var="materia" items="${materias}">
                    <tr>
                        <td>${materia.materia.nombre}</td>
                        <td>${materia.nota}</td>
                        <td>${materia.materiaHasAlumnoPK.fecha}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty materias}">
            <p>${error}</p>
        </c:if>
    </body>
</html>
