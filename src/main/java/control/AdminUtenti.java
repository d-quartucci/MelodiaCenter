package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/AdminUtenti")
public class AdminUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AdminUtenti() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		UtenteDAO uDAO =  new UtenteDAO(ds);
		
		try {
			ArrayList<Utente> utenti = uDAO.doRetrieveAll();
			request.setAttribute("utenti", utenti);
			request.getRequestDispatcher("/admin/gestioneUtenti.jsp").forward(request, response);
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore di accesso al gestore ordini: " + ex.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
