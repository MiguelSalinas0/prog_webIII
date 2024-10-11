<%-- 
    Document   : ListarCarreras
    Created on : 11 oct 2024, 13:32:38
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Listado de Carreras</title>
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
        <h1>Listado de Carreras</h1>
        <hr>
        <table id="lista" border="1">
            <thead>
                <tr>
                    <th>ID Carrera</th>
                    <th>Nombre</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="carrera" items="${requestScope.carreras}">
                    <tr>
                        <td>${carrera.idcarrera}</td>
                        <td>${carrera.nombre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
