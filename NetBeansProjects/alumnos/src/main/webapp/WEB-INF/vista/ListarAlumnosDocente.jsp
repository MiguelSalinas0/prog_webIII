<%-- 
    Document   : ListarAlumnosDocente
    Created on : 19 oct 2024, 14:20:22
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos que han rendido materias del docente</title>
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
        <h1>Listado de alumnos</h1>
        <table border="1">
            <tr>
                <th>Nombre de la materia</th>
                <th>Nombre del alumno</th>
                <th>Nota</th>
            </tr>
            <c:forEach var="alumno" items="${alumnos}">
                <tr>
                    <td>${alumno[0]}</td>
                    <td>${alumno[1]}</td>
                    <td>${alumno[2]}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
