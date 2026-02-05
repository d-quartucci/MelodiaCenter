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

import javax.sql.DataSource;

@WebServlet("/user/ConsulenzaPageServlet")
public class ConsulenzaPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ConsulenzaPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("utente") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
		    return;
		}
		
		Utente user = (Utente) session.getAttribute("utente");
		
		int consId = 0;
		//Prendo l'id come parametro della richiesta GET, se non è valido
		try{
			consId = Integer.parseInt(request.getParameter("consId"));
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore: ID dell'prodotto non valido.");
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		
		
		Consulenza cBean = null;
		ConsulenzaDAO cDAO = new ConsulenzaDAO(ds);
		try {
			cBean = cDAO.doRetrieveByKey(consId);
			if(cBean == null) {
				session.setAttribute("errorMessage", "Errore: la richiesta non esiste.");
			    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
			    return;
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore di generazione della pagina.");
		    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		
		if(user.getId() == cBean.getUtenteId() || "ADMIN".equals(user.getRuolo())) {
			request.setAttribute("consulenza", cBean);
		} else {
			session.setAttribute("errorMessage", "Errore: non hai accesso a questa pagina!");
		    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		
		
		request.getRequestDispatcher("/user/consulenza.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
