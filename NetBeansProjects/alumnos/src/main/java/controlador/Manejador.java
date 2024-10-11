/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import entidades.Alumno;
import entidades.Carrera;
import entidades.MateriaHasAlumno;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.NotSupportedException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sesiones.AlumnoFacade;
import sesiones.CarreraFacade;
import sesiones.MateriaFacade;
import sesiones.MateriaHasAlumnoFacade;

/**
 *
 * @author elias
 */
@WebServlet(name = "Manejador",
        loadOnStartup = 1, //Para que el sevlet se  instancia e inicia cdo se depliega 
        urlPatterns = {"/SolicitarDatos",
            "/AgregarAlumno",
            "/Listar",
            "/ListarCarreras",
            "/ListarMaterias",
            "/ListarAlumnosMaterias"}
)

public class Manejador extends HttpServlet {

    @EJB
    private AlumnoFacade alumnoF;
    @EJB
    private CarreraFacade carreraF;
    @EJB
    private MateriaFacade materiaF;
    @EJB
    private MateriaHasAlumnoFacade materiaAlumnoF;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, jakarta.transaction.NotSupportedException, jakarta.transaction.RollbackException {
        try {
            //response.setContentType("text/html;charset=UTF-8");
            String pathUsuario = request.getServletPath();
            System.out.println("path = " + pathUsuario);
            String url = null;
            switch (pathUsuario) {
                case "/SolicitarDatos":

                    url = "/WEB-INF/vista/" + pathUsuario + ".jsp";
                    break;

                case "/AgregarAlumno":

                    String registro = (String) request.getParameter("registro");
                    String nombre = (String) request.getParameter("nombre");
                    String carrera = (String) request.getParameter("carrera");

                    Alumno a = new Alumno();
                    a.setRegistro(Integer.valueOf(registro));
                    a.setNombre(nombre);

                    // buscar la carrera
                    Carrera carreraEntidad = carreraF.find(Integer.valueOf(carrera));
                    a.setCarreraIdcarrera(carreraEntidad);

                    alumnoF.create(a);
                    url = "index.jsp";
                    break;

                case "/Listar":

                    request.setAttribute("lista", alumnoF.findAll());
                    url = "/WEB-INF/vista/" + pathUsuario + ".jsp";
                    break;

                case "/ListarCarreras":

                    request.setAttribute("carreras", carreraF.findAll());
                    url = "/WEB-INF/vista/ListarCarreras.jsp";
                    break;

                case "/ListarMaterias":

                    request.setAttribute("materias", materiaF.findAll());
                    url = "/WEB-INF/vista/ListarMaterias.jsp";
                    break;

                case "/ListarAlumnosMaterias":
                    List<MateriaHasAlumno> alumnosConMaterias = materiaAlumnoF.findAlumnosConMaterias();
                    request.setAttribute("alumnosMaterias", alumnosConMaterias);
                    url = "/WEB-INF/vista/ListarAlumnosMaterias.jsp";
                    break;

            }

            // usa RequestDispatcher para reTransmitir el requerimiento
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (ServletException | IOException ex) {
            }

        } catch (NotSupportedException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (jakarta.transaction.NotSupportedException | jakarta.transaction.RollbackException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (jakarta.transaction.NotSupportedException | jakarta.transaction.RollbackException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
