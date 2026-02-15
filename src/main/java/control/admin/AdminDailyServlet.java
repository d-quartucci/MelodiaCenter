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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;


@WebServlet("/admin/Daily")
public class AdminDailyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminDailyServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		OrdineDAO oDAO =  new OrdineDAO(ds);
		UtenteDAO uDAO =  new UtenteDAO(ds);
		
		try{
		
			LocalDate oggi = LocalDate.now();
			Date sqlDate = Date.valueOf(oggi);
			
			ArrayList<Ordine> ordini = oDAO.doRetrieveByDate(sqlDate);
			ArrayList<Utente> utente = uDAO.doRetrieveByDate(sqlDate);
			
			request.setAttribute("utentiOggi", utente);
			request.setAttribute("ordiniOggi", ordini);
			
			request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
			
		} catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore di accesso alla dashboard: " + ex.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
