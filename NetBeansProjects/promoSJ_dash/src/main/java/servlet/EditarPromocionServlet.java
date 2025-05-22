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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import sesiones.PromocionesFacade;

/**
 *
 * @author elias
 */
@WebServlet(name = "EditarPromocionServlet", urlPatterns = {"/EditarPromocionServlet"})
public class EditarPromocionServlet extends HttpServlet {

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

        HttpSession session = request.getSession(false);
        Comercios comercio = (Comercios) (session != null ? session.getAttribute("comercio") : null);

        if (request.getMethod().equalsIgnoreCase("POST")) {

            try {

                // Obtener los datos de la promoción desde el formulario
                String idPromocion = request.getParameter("idPromocion");
                String titulo = request.getParameter("titulo");
                String descripcion = request.getParameter("descripcion");
                //String estado = request.getParameter("estado");
                boolean estado = "1".equals(request.getParameter("estado"));

                // Convertir los parámetros de fecha a java.sql.Date
                Date fechaInicio = Date.valueOf(request.getParameter("fechaInicio"));
                Date fechaFin = Date.valueOf(request.getParameter("fechaFin"));

                // Buscar la promoción y actualizar los valores
                Promociones promocion = promocionF.find(Integer.valueOf(idPromocion));
                if (promocion != null) {
                    promocion.setTitulo(titulo);
                    promocion.setDescripcion(descripcion);
                    promocion.setFechaInicio(fechaInicio);
                    promocion.setFechaFin(fechaFin);
                    promocion.setEstado(estado);

                    // Actualizar la promoción en la base de datos
                    promocionF.edit(promocion);

                    // Establecer un mensaje de éxito en la sesión
                    request.getSession().setAttribute("mensaje", "Los datos se han actualizado correctamente.");
                    request.getSession().setAttribute("tipoMensaje", "success");
                }

            } catch (Exception e) {
                // En caso de error, establecer un mensaje de error en la sesión
                request.getSession().setAttribute("mensaje", "Ocurrió un error al actualizar los datos. " + e.getMessage());
                request.getSession().setAttribute("tipoMensaje", "danger");
            }

            // Consultar las promociones asociadas al comercio
            List<Promociones> promociones = promocionF.findPromocionesByComercio(comercio.getIdComercio());
            request.setAttribute("promociones", promociones);
            // Redireccionar de vuelta a la página de promociones
            request.getRequestDispatcher("vista/promociones.jsp").forward(request, response);

            // Limpiar el mensaje después de la redirección
            request.getSession().removeAttribute("mensaje");
            request.getSession().removeAttribute("tipoMensaje");

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
