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
import sesiones.ComerciosFacade;

/**
 *
 * @author elias
 */
@WebServlet("/actualizarPerfil")
public class ActualizarPerfilServlet extends HttpServlet {

    @EJB
    private ComerciosFacade comercioFacade;

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
            try {
                // Obtener los datos enviados desde el formulario
                String nombre = request.getParameter("nombre");
                String direccion = request.getParameter("direccion");
                String telefono = request.getParameter("telefono");
                String email = request.getParameter("email");
                String descripcion = request.getParameter("descripcion");
                String horarios = request.getParameter("horarios");
                String password = request.getParameter("password");

                // Obtener el comercio actual desde la sesión
                Comercios comercio = (Comercios) request.getSession().getAttribute("comercio");

                // Actualizar los datos del comercio
                comercio.setNombre(nombre);
                comercio.setDireccion(direccion);
                comercio.setTelefono(telefono);
                comercio.setEmail(email);
                comercio.setDescripcion(descripcion);
                comercio.setHorarios(horarios);
                comercio.setPassword(password);

                // Guardar los cambios en la base de datos
                comercioFacade.edit(comercio);

                // Establecer un mensaje de éxito en la sesión
                request.getSession().setAttribute("mensaje", "Los datos se han actualizado correctamente.");
                request.getSession().setAttribute("tipoMensaje", "success");

            } catch (Exception e) {
                // En caso de error, establecer un mensaje de error en la sesión
                request.getSession().setAttribute("mensaje", "Ocurrió un error al actualizar los datos.");
                request.getSession().setAttribute("tipoMensaje", "danger");
            }

            // Redirigir de nuevo al perfil con los datos actualizados
            request.getRequestDispatcher("vista/perfil.jsp").forward(request, response);

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
