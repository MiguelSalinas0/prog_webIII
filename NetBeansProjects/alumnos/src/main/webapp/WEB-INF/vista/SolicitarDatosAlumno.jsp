<%-- 
    Document   : Agregar
    Created on : 25-oct-2010, 14:09:01
    Author     : Monti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo Alumno</h1>
        <form action="AgregarAlumno" method="post">
            <table>
                <tr><td>Registro:</td><td><input type="text" id = "registro"  name="registro" /></td></tr>
                <tr><td>Nombre</td><td><input type="text" id = "nombre" name="nombre" /></td></tr>
                <tr><td>Carrera</td><td><input type="text" id = "carrera" name="carrera" /></td></tr>
            </table>
            <input type="submit" id="crear" value="Crear" />
        </form>


        <a href="javascript:window.history.back();">&laquo; Volver atrás</a>


        <a href="./index.jsp" title="Ir la página anterior">Volver</a>
    </body>
</html>
