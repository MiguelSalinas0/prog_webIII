<%-- 
    Document   : estadisticas
    Created on : 13 oct 2024, 17:16:28
    Author     : elias
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Estadísticas</title>

        <link rel="stylesheet" href="css/style.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            .grafico {
                max-width: 100%;
                height: auto;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <!-- Sidebar -->
                <%@ include file="navbar.jsp" %>

                <!-- Contenido principal -->
                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="welcome-header mb-4">
                        <h1 class="h2">Bienvenido, ${comercio.nombre}</h1>
                        <h3>Estadísticas</h3>
                    </div>

                    <!-- Gráficos -->
                    <div class="row g-4 pb-5">
                        <!-- Gráfico 1 -->
                        <div class="col-12 col-lg-6">
                            <div class="card">
                                <div class="card-body text-center">
                                    <img class="grafico" loading="lazy" 
                                         src="<%= request.getContextPath()%>/BarChartServlet?tipoGrafico=totalCanjesPorPromocion" 
                                         alt="Total de Canjes por Promoción" />
                                </div>
                            </div>
                        </div>

                        <!-- Gráfico 2 -->
                        <div class="col-12 col-lg-6">
                            <div class="card">
                                <div class="card-body text-center">
                                    <img class="grafico" loading="lazy" 
                                         src="<%= request.getContextPath()%>/BarChartServlet?tipoGrafico=canjesPorEstado" 
                                         alt="Canjes por Estado" />
                                </div>
                            </div>
                        </div>

                        <!-- Gráfico 3 -->
                        <div class="col-12 col-lg-6">
                            <div class="card">
                                <div class="card-body text-center">
                                    <img class="grafico" loading="lazy" 
                                         src="<%= request.getContextPath()%>/BarChartServlet?tipoGrafico=rankingPromociones" 
                                         alt="Ranking de Promociones" />
                                </div>
                            </div>
                        </div>

                        <!-- Gráfico 4 -->
                        <div class="col-12 col-lg-6">
                            <div class="card">
                                <div class="card-body text-center">
                                    <img class="grafico" loading="lazy" 
                                         src="<%= request.getContextPath()%>/BarChartServlet?tipoGrafico=canjesPorFecha" 
                                         alt="Canjes por Fecha" />
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
