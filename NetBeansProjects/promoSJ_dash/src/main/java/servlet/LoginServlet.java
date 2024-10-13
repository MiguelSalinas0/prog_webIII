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
import sesiones.ComerciosFacade;
import sesiones.PromocionesFacade;

/**
 *
 * @author elias
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @EJB
    private ComerciosFacade comercioF;
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
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Comercios comercio = comercioF.login(email, password);

            if (comercio != null) {
                // Almacenar el comercio en la sesión
                HttpSession session = request.getSession();
                session.setAttribute("comercio", comercio);

                // Consultar las promociones asociadas al comercio
                List<Promociones> promociones = promocionF.findPromocionesByComercio(comercio.getIdComercio());

                // Pasar las promociones a la vista
                request.setAttribute("promociones", promociones);

                request.getRequestDispatcher("vista/home.jsp").forward(request, response);
            } else {
                // Si las credenciales no son correctas
                System.out.println("Email o contraseña incorrectos");
                request.setAttribute("errorMessage", "Email o contraseña incorrectos");
                request.getRequestDispatcher("vista/login.jsp").forward(request, response);
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
