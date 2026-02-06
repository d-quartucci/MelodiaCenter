package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.json.JSONArray;

@WebServlet("/admin/AdminFilterUtenti")
public class AdminFilterUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AdminFilterUtenti() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		HttpSession session = request.getSession(false);
		int adminId = ((Utente) session.getAttribute("utente")).getId();
		
		try {
			//prendo i parametri dalla richiesta
			String dataIn = request.getParameter("dataIn"); 
			String dataFin= request.getParameter("dataFin");
			String ord = request.getParameter("ordinaData");
			
			Timestamp dataInDate = null;
			Timestamp dataFinDate = null;
			
			//Controllo se l'admin ha inserito le date e creo l'oggetto Timestamp
			//Inserendo l'orario di inizio e fine giornata in modo da considerare tutto il ciclo giornaliero
			//Cosi facendo l'admin inserirà solo le date senza orario
	        if (dataIn != null && !dataIn.isEmpty()) {
	            dataInDate = Timestamp.valueOf(dataIn + " 00:00:00");
	        }

	        if (dataFin != null && !dataFin.isEmpty()) {
	            dataFinDate = Timestamp.valueOf(dataFin + " 23:59:59");
	        }
	        
	        UtenteDAO uDAO = new UtenteDAO(ds);
			ArrayList <Utente> utenti = uDAO.doRetrieveByFilter(adminId,dataInDate, dataFinDate, ord);
			
			request.setAttribute("utenti", utenti);
			request.getRequestDispatcher("/admin/CorpoTabellaUtenti.jsp").forward(request,response);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore di utilizzo dei filtriAdmin: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
