package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/FilterServlet")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FilterServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		try{
			ProdottoDAO pDAO = new ProdottoDAO(ds);
			ArrayList<Prodotto> listaProdotti = pDAO.doRetrieveByFilters(request.getParameter("barraDiRicerca"), request.getParameter("ordinaPrezzo"), request.getParameter("categoria"), request.getParameter("prezzoMax"));
			request.setAttribute("prodotti", listaProdotti);
			request.getRequestDispatcher("/common/catalog.jsp").forward(request, response);
		} catch(SQLException ex) {
			ex.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
