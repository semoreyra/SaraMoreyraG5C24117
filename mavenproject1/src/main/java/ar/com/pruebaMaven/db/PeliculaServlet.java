package ar.com.pruebaMaven.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;



//@WebServlet(name = "PeliculaServlet", urlPatterns = {"/PeliculaServlet"})
@WebServlet("/peliculas/*")
public class PeliculaServlet extends HttpServlet {

    private PeliculaService peliculaService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException
    {
       peliculaService = new PeliculaService();
       objectMapper = new ObjectMapper();
   
    }
    


protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
{
 /*me guarda donde estaba la url ubicada en ese momento*/
 String pathInfo= req.getPathInfo();
 try{
     if(pathInfo==null||pathInfo.equals("/")){
        List<Pelicula> peliculas = peliculaService.getAllpeliculas();
        String json=objectMapper.writeValueAsString(peliculas);
	resp.setContentType("application/json"); 
	resp.getWriter().write(json);
     }
     else{
        String[] pathParts = pathInfo.split("/"); 
        int id = Integer.parseInt(pathParts[1]);
        Pelicula pelicula = peliculaService.getpeliculaById(id);
        if (pelicula!=null)
          {
               String json=objectMapper.writeValueAsString(pelicula);
	       resp.setContentType("application/json"); 
	       resp.getWriter().write(json);


            }else{resp.sendError(HttpServletResponse.SC_NOT_FOUND);}
        
        
      }

     }       catch (SQLException | ClassNotFoundException ex) {
                throw new ServletException(ex); 
           // Logger.getLogger(PeliculaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


   
    }


 @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try 
        {
            Pelicula pelicula = objectMapper.readValue(req.getReader(), Pelicula.class);
            peliculaService.addPelicula(pelicula);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } 
        catch (SQLException | ClassNotFoundException e) 
        {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try 
        {
            Pelicula pelicula = objectMapper.readValue(req.getReader(), Pelicula.class);
            peliculaService.updatePelicula(pelicula);
            resp.setStatus(HttpServletResponse.SC_OK);
        } 
        catch (SQLException | ClassNotFoundException e) 
        {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String pathInfo = req.getPathInfo();
        try {
            	if (pathInfo != null && pathInfo.split("/").length > 1) 
            	{
	                int id = Integer.parseInt(pathInfo.split("/")[1]);
	                peliculaService.deletePelicula(id);
	                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            	} 
            	else 
            	{
            		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            	}
        	} 
        	catch (SQLException | ClassNotFoundException e) 
        	{
        			throw new ServletException(e);
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
 

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   

}
