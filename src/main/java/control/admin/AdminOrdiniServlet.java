package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ordine;
import model.dao.OrdineDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/admin/AdminOrdiniServlet")
public class AdminOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminOrdiniServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		OrdineDAO oDAO =  new OrdineDAO(ds);
		
		try {
			ArrayList<Ordine> ordini = oDAO.doRetrieveAll();
			//prendo la data del giorno corrente 
			LocalDate oggi = LocalDate.now();
			//prendo la data di inizio mese rispetto al giorno corrente
			LocalDate inizioMese = oggi.withDayOfMonth(1);

			//setto gli attributi cosi da inserire nel form dei filtri
			//vaolori di date di default
			request.setAttribute("defaultIn", inizioMese.toString());
			request.setAttribute("defaultFin", oggi.toString());
			request.setAttribute("ordini", ordini);
			
			request.getRequestDispatcher("/admin/gestioneOrdini.jsp").forward(request, response);
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore gestione ordini: " + ex.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
