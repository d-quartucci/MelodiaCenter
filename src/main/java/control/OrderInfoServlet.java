package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Ordine;
import model.RigaOrdine;
import model.Utente;
import model.dao.OrdineDAO;
import model.dao.RigaOrdineDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

/**
 * Servlet implementation class OrderInfoServlet
 */
@WebServlet("/OrderInfoServlet")
public class OrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		int idOrderRequested = 0; //Se resta 0, non verrà trovato alcun ordine
		ArrayList<RigaOrdine> rows = new ArrayList<>();
		
		Utente user = null;
		HttpSession sessione = request.getSession(false);
		if(sessione != null) {
			user = (Utente) sessione.getAttribute("utente");
		}
		
		//Prendo l'id come parametro della richiesta GET, se non è valido
		try{
			idOrderRequested = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			sessione.setAttribute("errorMessage", "Errore: ID dell'ordine non valido.");
		    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		
		OrdineDAO oDAO = new OrdineDAO(ds);
		try {
			Ordine orderRequested = oDAO.doRetrieveByKey(idOrderRequested);
			//Se l'ordine non è valido, errore
			if (orderRequested == null) {
				sessione.setAttribute("errorMessage", "Errore: ordine non valido.");
			    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
			    return;
			}
			
			//L'ordine potrà essere visto solo dall'utente che l'ha fatto e dagli amministratori
			if(user.getId() == orderRequested.getUtenteId() || "ADMIN".equals(user.getRuolo())) {
				//Rendo disponibile alla JSP il prezzo totale e l'id dell'ordine per cui è stata fatta la richiesta
				request.setAttribute("totaleOrdine", orderRequested.getTotale());
				request.setAttribute("idOrdine", request.getParameter("id"));
				
				//Prendo tutte le righe associate all'ordine
				RigaOrdineDAO roDAO = new RigaOrdineDAO(ds);
				rows = roDAO.doRetrieveByOrderID(orderRequested.getId());
				request.setAttribute("righeOrdine", rows);
				request.getRequestDispatcher("/user/ordine.jsp").forward(request, response);
			} else {
				//Se l'ordine non è dell'utente, non potrà essere visualizzato
				sessione.setAttribute("errorMessage", "Errore: non hai accesso a questa pagina!");
			    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
			    return;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setAttribute("errorMessage", "Errore durante l'accesso all'ordine: " + ex.getMessage());
			request.getRequestDispatcher("/common/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
