package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

//Questa servlet ha il ruolo di controllare se in fase di registrazione viene inserita un email già esistente

@WebServlet("/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CheckEmailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		UtenteDAO uDAO = new UtenteDAO(ds);
		
		boolean esiste = false;
		try {
			esiste = uDAO.doRetrieveByEmail(email) != null; //Se la query al DB restituisce qualcosa, l'email è già stata registrata
		} catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore durante il controllo della password: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
		
		response.setContentType("text/plain");
		response.getWriter().write(String.valueOf(esiste));
	}

}
