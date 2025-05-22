<%-- 
    Document   : editarPromocion
    Created on : 28 oct 2024, 15:09:33
    Author     : elias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css"/>
        <title>Editar Promoción</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">

                <!-- Sidebar -->
                <%@ include file="navbar.jsp" %>

                <!-- Contenido principal -->
                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="container mt-5">
                        <h2 class="mb-4">Editar Promoción</h2>
                        <form action="EditarPromocionServlet" method="post">
                            <!-- Campo oculto para enviar el ID de la promoción -->
                            <input type="hidden" id="idPromocion" name="idPromocion" value="${promocion.idPromocion}">

                            <div class="mb-3">
                                <label for="titulo" class="form-label">Título</label>
                                <input type="text" class="form-control" id="titulo" name="titulo" value="${promocion.titulo}" required>
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descripción</label>
                                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required>${promocion.descripcion}</textarea>
                            </div>
                            <div class="mb-3">
                                <label for="fechaInicio" class="form-label">Fecha de Inicio</label>
                                <input type="date" class="form-control" id="fechaInicio" name="fechaInicio" value="${fechaInicio}" required>
                            </div>
                            <div class="mb-3">
                                <label for="fechaFin" class="form-label">Fecha de Fin</label>
                                <input type="date" class="form-control" id="fechaFin" name="fechaFin" value="${fechaFin}" required>
                            </div>
                            <div class="mb-3">
                                <label for="estado" class="form-label">Estado</label>
                                <select class="form-select" id="estado" name="estado" required>
                                    <option value="1" ${promocion.estado ? 'selected' : ''}>Activa</option>
                                    <option value="0" ${!promocion.estado ? 'selected' : ''}>Inactiva</option>
                                </select>
                            </div>
                                
                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                            <a href="Promociones" class="btn btn-secondary">Cancelar</a>
                        </form>
                    </div>
                </main>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
