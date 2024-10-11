<%-- 
    Document   : index
    Created on : 5 sept 2024, 17:13:03
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, java.util.List, paquete.Usuario" %>


<%
    // Inicializar la lista de visitantes en la sesión si no existe
    List<Usuario> visitantes = (List<Usuario>) session.getAttribute("visitantes");

    if (visitantes == null) {
        visitantes = new ArrayList<Usuario>();
        session.setAttribute("visitantes", visitantes);
    }

    // Manejar el formulario de entrada
    String nombreVisitante = request.getParameter("nombre");

    if (nombreVisitante != null && !nombreVisitante.trim().isEmpty()) {
        Usuario nuevoVisitante = new Usuario();
        nuevoVisitante.setNombre(nombreVisitante);
        visitantes.add(nuevoVisitante);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h4>Información del Autor</h4>
        <p>Autor del sitio: Miguel  Salinas</p>

        <h4>Ingresar Nombre del Visitante</h4>
        <form action="visitante.jsp" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
            <input type="submit" value="Agregar Visitante">
        </form>

                    <c:out value="Hello, JSTL!"/>

        <h4>Listado de Visitantes</h4>
        <ul>
            <%
                // Iterar sobre la lista de visitantes y mostrarlos en la página
                for (Usuario visitante : visitantes) {
            %>
                    <li><%= visitante.getNombre() %></li>
            <%
                }
            %>

            <c:forEach var="visitante" items="${sessionScope.visitantes}">
                <li>${visitante.nombre}</li>
            </c:forEach>

        </ul>

    </body>
</html>
