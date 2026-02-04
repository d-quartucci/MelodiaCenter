package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Desidera;
import model.Utente;
import model.dao.DesideraDAO;
import utils.DesideraKey;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

@WebServlet("/OperazioneWishlistServlet")
public class OperazioneWishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OperazioneWishlistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		String azione = request.getParameter("act");
		String param = request.getParameter("id");
		if(param == null) {
            response.sendError(400, "Errore: parametro mancante!");
            return;
		}
		
		int idProd;
		try {
			idProd = Integer.parseInt(param);
		} catch(NumberFormatException ex) {
			response.sendError(400, "Errore: parametro non valido!");
            return;
		}
		
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("utente");
		Desidera bean = new Desidera();
		DesideraKey beanKey = new DesideraKey(user.getId(), idProd);
		bean.setKey(beanKey);
		bean.setDataAggiunta(new Date(System.currentTimeMillis()));
		DesideraDAO dDAO = new DesideraDAO(ds);
		
		if("add".equals(azione)) {
			try {
				dDAO.doSaveOrUpdate(bean);
			} catch(SQLException ex) {
				response.sendError(500, "Errore nel database: " + ex.getMessage());
	            return;
			}
		}
		
		if("remove".equals(azione)) {
			try {
				dDAO.doDeleteByKey(beanKey);
			} catch(SQLException ex) {
				response.sendError(500, "Errore nel database: " + ex.getMessage());
	            return;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
