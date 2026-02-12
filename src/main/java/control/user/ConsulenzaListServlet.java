package control.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Consulenza;
import model.Utente;
import model.dao.ConsulenzaDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/user/consulenzaList")
public class ConsulenzaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsulenzaListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		ArrayList<Consulenza> consulenzaList = new ArrayList<>();
		
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("utente");
		int idUser = user.getId();
		
		ConsulenzaDAO cDAO = new ConsulenzaDAO(ds);	
		try {
			consulenzaList = cDAO.doRetrieveByUserID(idUser);
		} catch(SQLException ex) {
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore di elaborazione della richiesta.");
		    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		
		request.setAttribute("consulenzaList", consulenzaList);
		
		request.getRequestDispatcher("/user/listaConsulenze.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
