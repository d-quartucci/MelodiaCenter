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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/admin/AdminUtentiServlet")
public class AdminUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AdminUtentiServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
			
			HttpSession session = request.getSession(false);
			int adminId = ((Utente) session.getAttribute("utente")).getId();
			UtenteDAO uDAO =  new UtenteDAO(ds);
			
			//prendo la data del giorno corrente 
			LocalDate oggi = LocalDate.now();
			//prendo la data di inizio mese rispetto al giorno corrente
			LocalDate inizioMese = oggi.withDayOfMonth(1);
			ArrayList<Utente> utenti = uDAO.doRetrieveExcept(adminId);
			//setto gli attributi cosi da inserire nel form dei filtri
			//vaolori di date di default
			request.setAttribute("defaultIn", inizioMese.toString());
			request.setAttribute("defaultFin", oggi.toString());
			request.setAttribute("utenti", utenti);
			
			request.getRequestDispatcher("/admin/gestioneUtenti.jsp").forward(request, response);
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore Update: " + ex.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
