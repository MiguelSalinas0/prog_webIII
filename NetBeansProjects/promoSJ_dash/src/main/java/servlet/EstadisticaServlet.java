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
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.TextAnchor;
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

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] fila : estadisticas) {
            String titulo = (String) fila[1];
            Integer cantidadCanjes = ((Number) fila[2]).intValue();
            Integer visitas = ((Number) fila[3]).intValue();

            dataset.addValue(cantidadCanjes, "Canjes", titulo);
            dataset.addValue(visitas, "Visitas", titulo);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Total de Canjes y Visitas por Promoción",
                "Promoción",
                "Cantidad",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Estética del gráfico
        barChart.setBorderVisible(false);
        barChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));

        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setOutlineVisible(false);

        // Eje X: etiquetas rotadas y fuente mejorada
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0)
        );
        domainAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 12));

        // Eje Y: fuente también
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 12));

        // Renderer y colores
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(79, 129, 189)); // Canjes
        renderer.setSeriesPaint(1, new Color(192, 80, 77));  // Visitas

        // Etiquetas sobre las barras (más legibles)
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultPositiveItemLabelPosition(
                new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)
        );

        // Generar imagen
        response.setContentType("image/png");
        try (OutputStream out = response.getOutputStream()) {
            ChartUtils.writeChartAsPNG(out, barChart, 700, 450); // tamaño levemente más grande
        }
    }

    private void generarGraficoCanjesPorEstado(HttpServletResponse response, Integer idComercio) throws IOException {
        List<Object[]> canjesPorEstado = estadisticasF.obtenerCanjesPorEstado(idComercio);

        // Dataset para gráfico de pastel
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Object[] fila : canjesPorEstado) {
            String estado = (String) fila[0];
            Long total = ((Number) fila[1]).longValue();
            dataset.setValue(estado, total);
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Distribución de Canjes por Estado",
                dataset,
                true, // leyenda
                true, // tooltips
                false // URLs
        );

        // Estética del gráfico
        pieChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setLabelOutlinePaint(Color.GRAY);
        plot.setOutlineVisible(false);

        // Colores personalizados por estado
        plot.setSectionPaint("completado", new Color(67, 160, 71));  // Verde
        plot.setSectionPaint("pendiente", new Color(255, 193, 7));   // Amarillo
        plot.setSectionPaint("expirado", new Color(229, 57, 53));    // Rojo

        // Etiquetas con número y porcentaje
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}: {1} canjes ({2})",
                NumberFormat.getIntegerInstance(),
                NumberFormat.getPercentInstance()
        ));

        // Borde alrededor de cada sección
        for (var key : dataset.getKeys()) {
            plot.setSectionOutlinePaint((Comparable) key, Color.DARK_GRAY);
            plot.setSectionOutlineStroke((Comparable) key, new BasicStroke(1.0f));
        }
        plot.setSectionOutlinesVisible(true);

        // Explosión de sectores (resaltar secciones grandes)
        for (Object keyObj : dataset.getKeys()) {
            Comparable<?> key = (Comparable<?>) keyObj;
            plot.setSectionOutlinePaint(key, Color.DARK_GRAY);
            plot.setSectionOutlineStroke(key, new BasicStroke(1.0f));
        }
        plot.setSectionOutlinesVisible(true);

        // Mejorar la leyenda
        pieChart.getLegend().setItemFont(new Font("SansSerif", Font.PLAIN, 11));

        // Exportar imagen
        response.setContentType("image/png");
        try (OutputStream out = response.getOutputStream()) {
            ChartUtils.writeChartAsPNG(out, pieChart, 600, 450);
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
        CategoryAxis domainAxis = plot.getDomainAxis();

        // Rotar etiquetas del eje X
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0)
        );
        domainAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 12));

        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setOutlineVisible(false);

        // Personalizar renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);

        // Colorear barras con degradado (más atractivo visualmente)
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, new Color(93, 173, 226),
                0.0f, 0.0f, new Color(40, 116, 166));
        renderer.setSeriesPaint(0, gp);

        // Añadir bordes a las barras
        renderer.setDrawBarOutline(true);
        renderer.setDefaultOutlinePaint(Color.DARK_GRAY);
        renderer.setDefaultOutlineStroke(new BasicStroke(0.8f));

        // Título personalizado
        barChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));
        barChart.setBorderVisible(false);

        // Configurar el tipo de contenido para imagen PNG
        response.setContentType("image/png");
        try (OutputStream out = response.getOutputStream()) {
            ChartUtils.writeChartAsPNG(out, barChart, 600, 450);
        }
    }

    private void generarGraficoCanjesPorFecha(HttpServletResponse response, Integer idComercio) throws IOException {
        List<Object[]> canjesPorFecha = estadisticasF.obtenerPorFecha(idComercio);

        TimeSeries series = new TimeSeries("Canjes Efectivos");

        for (Object[] fila : canjesPorFecha) {
            int anio = ((Number) fila[0]).intValue();
            int mes = ((Number) fila[1]).intValue();
            long totalCanjes = ((Number) fila[2]).longValue();

            series.add(new Month(mes, anio), totalCanjes);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        JFreeChart lineChart = ChartFactory.createTimeSeriesChart(
                "Canjes Efectivos por Mes",
                "Fecha",
                "Total de Canjes",
                dataset,
                true, true, false
        );

        XYPlot plot = lineChart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setOutlineVisible(false);

        // Formato de eje de fechas
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        axis.setVerticalTickLabels(true);  // Mejora la legibilidad en espacios pequeños

        axis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 11));
        axis.setLabelFont(new Font("SansSerif", Font.BOLD, 12));

        // Personalizar líneas y puntos
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        renderer.setSeriesPaint(0, new Color(40, 116, 166)); // Azul
        renderer.setSeriesStroke(0, new BasicStroke(2.5f));
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0)); // Puntos redondos

        plot.setRenderer(renderer);

        // Personalizar el título
        lineChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));

        // Agregar número de canjes sobre los puntos
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
        renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.PLAIN, 10));
        renderer.setDefaultItemLabelPaint(Color.DARK_GRAY);

        // Exportar como imagen PNG
        response.setContentType("image/png");
        try (OutputStream out = response.getOutputStream()) {
            ChartUtils.writeChartAsPNG(out, lineChart, 700, 450); // Tamaño levemente ampliado
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
