<%-- 
    Document   : ListarAlumnosMaterias
    Created on : 11 oct 2024, 13:46:54
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Listado de Alumnos con Materias</title>
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
        <h1>Listado de Alumnos con Materias</h1>
        <hr>
        <table id="lista" border="1">
            <thead>
                <tr>
                    <th>Registro Alumno</th>
                    <th>Nombre Alumno</th>
                    <th>Nombre Materia</th>
                    <th>Nota</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="relacion" items="${requestScope.alumnosMaterias}">
                    <tr>
                        <td>${relacion.alumno.registro}</td>
                        <td>${relacion.alumno.nombre}</td>
                        <td>${relacion.materia.nombre}</td>
                        <td>${relacion.nota}</td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </body>
</html>

