/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entidades.Comercios;
import entidades.Promociones;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import sesiones.PromocionesFacade;

/**
 *
 * @author elias
 */
public class CrearPromocionServlet extends HttpServlet {

    @EJB
    private PromocionesFacade promocionF;

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

        if (request.getMethod().equalsIgnoreCase("POST")) {

            // Obtener el comercio actual desde la sesión
            Comercios comercio = (Comercios) request.getSession().getAttribute("comercio");

            // Establece el formato de fecha que debe coincidir con el input de tipo date en el formulario
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Obtiene los parámetros del formulario
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String fechaInicioStr = request.getParameter("fechaInicio");
            String fechaFinStr = request.getParameter("fechaFin");
            String estado = request.getParameter("estado");

            try {
                // Convierte las fechas de inicio y fin de String a Date
                Date fechaInicio = dateFormat.parse(fechaInicioStr);
                Date fechaFin = dateFormat.parse(fechaFinStr);

                // Crea una nueva instancia de Promocion
                Promociones promocion = new Promociones();
                promocion.setIdComercio(comercio);
                promocion.setTitulo(titulo);
                promocion.setDescripcion(descripcion);
                promocion.setFechaInicio(fechaInicio);
                promocion.setFechaFin(fechaFin);
                promocion.setEstado(estado);

                // Guarda la promoción en la base de datos utilizando el facade
                promocionF.create(promocion);

                // Redirige a la página de promociones después de guardar
                response.sendRedirect("Promociones");

            } catch (IOException | ParseException e) {
                request.setAttribute("error", "Error al crear la promoción.");
                request.getRequestDispatcher("vista/crearPromocion.jsp").forward(request, response);
            }
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
