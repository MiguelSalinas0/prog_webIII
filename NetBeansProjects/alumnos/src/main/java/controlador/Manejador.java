/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import entidades.Alumno;
import entidades.Carrera;
import entidades.Docente;
import entidades.Facultad;
import entidades.Materia;
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
import sesiones.DocenteFacade;
import sesiones.FacultadFacade;
import sesiones.MateriaFacade;
import sesiones.MateriaHasAlumnoFacade;

/**
 *
 * @author elias
 */
@WebServlet(name = "Manejador",
        loadOnStartup = 1, //Para que el sevlet se  instancia e inicia cdo se depliega 
        urlPatterns = {
            "/Docente",
            "/Facultad",
            "/SolicitarDatosAlumno",
            "/AgregarAlumno",
            "/Listar",
            "/ListarCarreras",
            "/ListarMaterias",
            "/ListarAlumnosMaterias",
            "/SolicitarDatosCarrera",
            "/RegistrarCarrera",
            "/SolicitarDatosMateria",
            "/RegistrarMateria",
            "/SolicitarDatosFacultad",
            "/RegistrarFacultad",
            "/SolicitarDatos"
        }
)

public class Manejador extends HttpServlet {

    @EJB
    private AlumnoFacade alumnoF;
    @EJB
    private CarreraFacade carreraF;
    @EJB
    private MateriaFacade materiaF;
    @EJB
    private FacultadFacade facultadF;
    @EJB
    private MateriaHasAlumnoFacade materiaAlumnoF;
    @EJB
    private DocenteFacade docenteF;

    @Override
    public void init() throws ServletException {
        // Almacena la lista de facultades en el contexto del Servlet
        getServletContext().setAttribute("facultades", facultadF.findAll());
        // Almacena la lista de docentes en el contexto del Servlet
        getServletContext().setAttribute("docentes", docenteF.findAll());

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, jakarta.transaction.NotSupportedException, jakarta.transaction.RollbackException {
        try {
            //response.setContentType("text/html;charset=UTF-8");
            String pathUsuario = request.getServletPath();
            System.out.println("path = " + pathUsuario);
            String url = null;
            switch (pathUsuario) {
                
                case "/Docente":
                    
                    Integer nroDocente = Integer.valueOf(request.getParameter("codigoDocen"));
                    request.setAttribute("alumnos", docenteF.findAlumnosByDocente(nroDocente));
                    url = "/WEB-INF/vista/ListarAlumnosDocente.jsp";
                    break;

                case "/Facultad":
                    
                    Integer nroFacultad = Integer.valueOf(request.getParameter("codigoFacu"));
                    Facultad miFacu = facultadF.find(nroFacultad);
                    getServletContext().setAttribute("facultad", miFacu);
                    request.setAttribute("carreras", carreraF.findCarrerasByFacultad(miFacu));
                    url = "/WEB-INF/vista/ListarCarreras.jsp";
                    break;

                case "/SolicitarDatosAlumno":

                    request.setAttribute("carreras", carreraF.findAll());
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

                case "/SolicitarDatosCarrera":

                    request.setAttribute("listaFacultades", facultadF.findAll());
                    url = "/WEB-INF/vista/" + pathUsuario + ".jsp";
                    break;

                case "/RegistrarCarrera":

                    String nombreC = (String) request.getParameter("nombre");
                    String facultadId = request.getParameter("facultad");

                    Facultad fac = facultadF.find(Integer.valueOf(facultadId));
                    Carrera c = new Carrera();

                    c.setNombre(nombreC);
                    c.setFacultadIdfacultad(fac);

                    carreraF.create(c);
                    url = "index.jsp";
                    break;

                case "/SolicitarDatosMateria":

                    url = "/WEB-INF/vista/" + pathUsuario + ".jsp";
                    break;

                case "/RegistrarMateria":

                    String nombreM = (String) request.getParameter("nombre");
                    Materia m = new Materia();
                    m.setNombre(nombreM);
                    materiaF.create(m);
                    url = "index.jsp";
                    break;

                case "/SolicitarDatosFacultad":

                    url = "/WEB-INF/vista/" + pathUsuario + ".jsp";
                    break;

                case "/RegistrarFacultad":

                    String nombreF = (String) request.getParameter("nombre");
                    Facultad f = new Facultad();
                    f.setNombre(nombreF);
                    facultadF.create(f);
                    url = "index.jsp";
                    break;

                case "/SolicitarDatos":
                    String numeroRegistro = request.getParameter("numeroRegistro");

                    if (numeroRegistro != null && !numeroRegistro.trim().isEmpty()) {
                        try {
                            int numeroRegistroInt = Integer.parseInt(numeroRegistro);

                            List<MateriaHasAlumno> materiasAlumno = materiaAlumnoF.findMateriasByRegistro(numeroRegistroInt);

                            if (materiasAlumno != null && !materiasAlumno.isEmpty()) {
                                request.setAttribute("materias", materiasAlumno);
                            } else {
                                request.setAttribute("error", "No se encontró ningún alumno con ese número de registro.");
                            }
                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "El número de registro debe ser un valor numérico válido.");
                        }
                    } else {
                        request.setAttribute("error", "Debe proporcionar un número de registro.");
                    }

                    url = "/WEB-INF/vista/SolicitarDatos.jsp";
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
