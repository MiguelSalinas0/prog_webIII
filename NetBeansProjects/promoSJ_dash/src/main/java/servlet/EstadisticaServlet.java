/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entidades.Comercios;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import sesiones.EstadisticasPromocionesFacade;

/**
 *
 * @author elias
 */
@WebServlet("/BarChartServlet")
public class EstadisticaServlet extends HttpServlet {

    @EJB
    private EstadisticasPromocionesFacade estadisticasF;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el comercio actual desde la sesión
        Comercios comercio = (Comercios) request.getSession().getAttribute("comercio");

        String tipoGrafico = request.getParameter("tipoGrafico");
        Integer idComercio = comercio.getIdComercio();

        switch (tipoGrafico) {
            case "totalCanjesPorPromocion":
                generarGraficoTotalCanjesPorPromocion(response, idComercio);
                break;
            case "canjesPorEstado":
                generarGraficoCanjesPorEstado(response, idComercio);
                break;
            case "rankingPromociones":
                generarGraficoRankingPromociones(response, idComercio);
                break;
            case "canjesPorFecha":
                generarGraficoCanjesPorFecha(response, idComercio);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo de gráfico no soportado.");
        }

    }

    public void generarGraficoTotalCanjesPorPromocion(HttpServletResponse response, Integer idComercio) throws IOException {
        List<Object[]> estadisticas = estadisticasF.obtenerTotalDeCanjesPorPromocion(idComercio);

        // Crear el dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] fila : estadisticas) {
            String titulo = (String) fila[1];
            Integer cantidadCanjes = ((Number) fila[2]).intValue();
            Integer visitas = ((Number) fila[3]).intValue();

            dataset.addValue(cantidadCanjes, "Canjes", titulo);
            dataset.addValue(visitas, "Visitas", titulo);
        }

        // Crear el gráfico
        JFreeChart barChart = ChartFactory.createBarChart(
                "Total de Canjes y Visitas por Promoción",
                "Promoción",
                "Cantidad",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Personalizar colores y estilos
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);

        // Cambiar los colores de las barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(79, 129, 189)); // Color para "Canjes"
        renderer.setSeriesPaint(1, new Color(192, 80, 77));  // Color para "Visitas"

        // Mostrar los valores en las barras
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);

        // Cambiar el tamaño de la imagen
        response.setContentType("image/png");
        try (OutputStream out = response.getOutputStream()) {
            ChartUtils.writeChartAsPNG(out, barChart, 600, 400); // Tamaño ajustado a 600x400
        }
    }

    private void generarGraficoCanjesPorEstado(HttpServletResponse response, Integer idComercio) throws IOException {
        List<Object[]> canjesPorEstado = estadisticasF.obtenerCanjesPorEstado(idComercio);

        // Crear el dataset para el gráfico de pastel
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Llenar el dataset con los datos de la consulta
        for (Object[] fila : canjesPorEstado) {
            String estado = (String) fila[0];
            Long total = ((Number) fila[1]).longValue();
            dataset.setValue(estado, total);
        }

        // Crear el gráfico de pastel
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Canjes por Estado",
                dataset,
                true, // leyenda
                true, // tooltips
                false // URLs
        );

        // Personalizar colores del gráfico de pastel
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("completado", new Color(67, 160, 71)); // Verde
        plot.setSectionPaint("pendiente", new Color(255, 193, 7));  // Amarillo
        plot.setSectionPaint("cancelado", new Color(229, 57, 53));  // Rojo

        // Mostrar los valores como porcentaje y agregar formato de número
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()
        ));
        plot.setBackgroundPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setLabelOutlinePaint(Color.GRAY);

        //Configurar el tipo de contenido para imagen PNG
        response.setContentType("image/png");
        try (OutputStream out = response.getOutputStream()) {
            ChartUtils.writeChartAsPNG(out, pieChart, 600, 400);
        }
    }

    public void generarGraficoRankingPromociones(HttpServletResponse response, Integer idComercio) throws IOException {
        List<Object[]> rankingPromociones = estadisticasF.obtenerRanking(idComercio);

        // Crear el dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] fila : rankingPromociones) {
            String titulo = (String) fila[1];
            Integer cantidadCanjes = ((Number) fila[2]).intValue();
            dataset.addValue(cantidadCanjes, "Canjes", titulo);
        }

        // Crear el gráfico
        JFreeChart barChart = ChartFactory.createBarChart(
                "Ranking de Promociones por Canjes",
                "Promoción",
                "Cantidad de Canjes",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Personalizar colores y estilos
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);

        // Mostrar los valores en las barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);

        // Configurar el tipo de contenido para imagen PNG
        response.setContentType("image/png");
        try (OutputStream out = response.getOutputStream()) {
            ChartUtils.writeChartAsPNG(out, barChart, 600, 400); // Tamaño ajustado a 600x400
        }
    }

    private void generarGraficoCanjesPorFecha(HttpServletResponse response, Integer idComercio) throws IOException {
        List<Object[]> canjesPorFecha = estadisticasF.obtenerPorFecha(idComercio);

        // Crear el dataset para el gráfico de líneas
        TimeSeries series = new TimeSeries("Canjes Efectivos");

        // Llenar el dataset con los datos de la consulta
        for (Object[] fila : canjesPorFecha) {
            int anio = ((Number) fila[0]).intValue();
            int mes = ((Number) fila[1]).intValue();
            long totalCanjes = ((Number) fila[2]).longValue();

            // Agregar el dato a la serie con año y mes
            series.add(new Month(mes, anio), totalCanjes);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        // Crear el gráfico de líneas
        JFreeChart lineChart = ChartFactory.createTimeSeriesChart(
                "Canjes Efectivos por Mes",
                "Fecha",
                "Total de Canjes",
                dataset,
                true, // leyenda
                true, // tooltips
                false // URLs
        );

        // Personalizar el gráfico
        XYPlot plot = lineChart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);

        // Configurar el formato de la fecha en el eje X (eje temporal)
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));  // Mostrar mes y año

        // Personalizar el color de la línea
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(79, 129, 189)); // Azul personalizado
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);

        // Escribir el gráfico en el flujo de salida
        response.setContentType("image/png");
        try (OutputStream out = response.getOutputStream()) {
            ChartUtils.writeChartAsPNG(out, lineChart, 600, 400);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
