package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ordine;
import model.Utente;
import model.dao.OrdineDAO;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.json.JSONArray;

@WebServlet("/admin/AdminUpdateUtenteServlet")
public class AdminUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminUpdateUtenteServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
			String idUtente = request.getParameter("id");
			String campo = request.getParameter("campo");
			String valore = request.getParameter("valore");
			
			int id = Integer.parseInt(idUtente);
			
			UtenteDAO uDAO = new UtenteDAO(ds);
			Utente utente = uDAO.doRetrieveByKey(id);
			
			if(utente != null) {
				if(campo.equals("email")) {
					
					utente.setEmail(valore);
				}	
		
				else if(campo.equals("ruolo")) {
					
					utente.setRuolo(valore);
					
				} else {
			        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			        return;
			    }
			
				uDAO.doSaveOrUpdate(utente);
				
				//Invia messaggio e codice di stato alla JS positivo
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else {
				//Invia messaggio e codice di stato alla JS negativo
				response.sendError(HttpServletResponse.SC_NOT_FOUND); 
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore Update: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
