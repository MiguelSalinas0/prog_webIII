/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import entidades.Alumno;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import jakarta.persistence.RollbackException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.NotSupportedException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elias
 */

@WebServlet(name = "Manejador", 
            loadOnStartup = 1, //Para que el sevlet se  instancia e inicia cdo se depliega 
            urlPatterns = {"/SolicitarDatos",
                           "/AgregarAlumno",
                           "/Listar"}
            )

public class Manejador extends HttpServlet {
    
    @PersistenceUnit 
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, jakarta.transaction.NotSupportedException, jakarta.transaction.RollbackException {
            try {
                //response.setContentType("text/html;charset=UTF-8");
                String pathUsuario = request.getServletPath();
                System.out.println("path = "+ pathUsuario);	    
                String url = null;
                EntityManager em = null;
                switch (pathUsuario) {
                    case "/SolicitarDatos":  
                        url = "/WEB-INF/vista/" + pathUsuario + ".jsp";
                        break;
                    case "/AgregarAlumno":
                        
                        String registro= (String) request.getParameter("registro");
                        String nombre  = (String) request.getParameter("nombre");
                        String carrera  = (String) request.getParameter("carrera");
                        
                        Alumno a = new Alumno();
                        a.setRegistro(Integer.parseInt(registro));
                        a.setNombre(nombre);
                        //a.setCarrera(carrera);

                        utx.begin();
                            em = emf.createEntityManager();
                            em.persist(a);
                        utx.commit();
                        
                        url = "index.jsp";                      
                        break;
                    case "/Listar":
                        em = emf.createEntityManager();
                        Query q = em.createNamedQuery("Alumno.findAll");
                        List todos = q.getResultList();
                        request.setAttribute("lista",todos);
                        url = "/WEB-INF/vista/" + pathUsuario + ".jsp";
                        break;
                }
                
                // usa RequestDispatcher para reTransmitir el requerimiento
                try {
                    request.getRequestDispatcher(url).forward(request, response);
                } catch (ServletException | IOException ex) {
                }
                
            } catch (NotSupportedException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
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
        } catch (jakarta.transaction.NotSupportedException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (jakarta.transaction.RollbackException ex) {
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
        } catch (jakarta.transaction.NotSupportedException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (jakarta.transaction.RollbackException ex) {
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
