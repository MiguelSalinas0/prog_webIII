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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sesiones.PromocionesFacade;

/**
 *
 * @author elias
 */
@WebServlet(name = "Ruteo",
        loadOnStartup = 1, //Para que el sevlet se  instancia e inicia cdo se depliega 
        urlPatterns = {
            "/Home",
            "/Perfil",
            "/Promociones",
            "/Estadisticas"
        }
)
public class Ruteo extends HttpServlet {

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

        String pathUsuario = request.getServletPath();
        String url;
        HttpSession session = request.getSession(false);
        Comercios comercio = (Comercios) (session != null ? session.getAttribute("comercio") : null);

        // Verifica la sesión y el comercio al principio
        if (session == null || comercio == null) {
            request.getRequestDispatcher("vista/login.jsp").forward(request, response);
            return;
        }

        switch (pathUsuario) {

            case "/Home":
                request.setAttribute("comercio", comercio);
                url = "vista/home.jsp";
                break;

            case "/Perfil":
                request.setAttribute("comercio", comercio);
                url = "vista/perfil.jsp";
                break;

            case "/Promociones":
                // Consultar las promociones asociadas al comercio
                List<Promociones> promociones = promocionF.findPromocionesByComercio(comercio.getIdComercio());
                request.setAttribute("promociones", promociones);
                url = "/vista/promociones.jsp";
                break;

            case "/Estadisticas":
                request.setAttribute("comercio", comercio);
                url = "vista/estadisticas.jsp";
                break;

            default:
                // Maneja una ruta no esperada
                url = "vista/error.jsp"; // O alguna página de error personalizada
                break;

        }
        // Transmite el requerimiento
        if (url != null) {
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(Ruteo.class.getName()).log(Level.SEVERE, null, ex);
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
