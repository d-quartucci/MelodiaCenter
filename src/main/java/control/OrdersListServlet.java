package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Ordine;
import model.Utente;
import model.dao.OrdineDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/OrdersListServlet")
public class OrdersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrdersListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("utente");
		OrdineDAO oDAO = new OrdineDAO(ds);
		ArrayList<Ordine> ordersList = new ArrayList<>();
		try {
			ordersList = oDAO.doRetrieveByUserID(user.getId());
		} catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore durante l'accesso alla lista ordini: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
		request.setAttribute("listaOrdini", ordersList);
		request.getRequestDispatcher("/user/listaOrdini.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
