package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.CarrelloItem;
import model.Prodotto;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

@WebServlet("/OperazioneCartServlet")
public class OperazioneCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OperazioneCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		HttpSession sessione = request.getSession(true);
		Carrello carrello = (Carrello) sessione.getAttribute("carrello");
		
		if(carrello == null) {
			carrello = new Carrello();
			sessione.setAttribute("carrello", carrello);
		}
		
		int quantita = Integer.parseInt(request.getParameter("q"));
		int idProd = Integer.parseInt(request.getParameter("id"));
		Prodotto prod = null;
		ProdottoDAO pDAO = new ProdottoDAO(ds); 
		try{
			prod = pDAO.doRetrieveByKey(idProd);
		} catch(SQLException ex) {
			ex.printStackTrace();
			response.sendRedirect("/common/error.jsp");
			return;
		}
		
		if(prod == null || quantita <= 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		CarrelloItem item = new CarrelloItem(prod, quantita);
		
		String azione = request.getParameter("act");
		
		if("add".equals(azione)) {
			carrello.addItem(item);
		} else if("rem".equals(azione)) {
			carrello.removeItem(item);
		} else if("mod".equals(azione)) {
			carrello.modificaQuantita(idProd, quantita);
		} else if("dec".equals(azione)) {
			carrello.decrementaQuantita(idProd);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
