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
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.json.JSONObject;

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
		
		String azione = request.getParameter("act");
		
		//La quantità per tutte le operazioni è sempre "1", eccetto per l'operazione di modifica diretta della quantità
		int quantita = 1;
		if("mod".equals(azione)) {
			try{
				quantita = Integer.parseInt(request.getParameter("q"));
			} catch(NumberFormatException ex) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			if(quantita <= 0) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		
		int idProd;
		try {
			idProd = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException ex) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Prodotto prod = null;
		ProdottoDAO pDAO = new ProdottoDAO(ds); 
		try{
			prod = pDAO.doRetrieveByKey(idProd);
		} catch(SQLException ex) {
			ex.printStackTrace();
			response.sendRedirect(request.getContextPath() +"/common/error.jsp");
			return;
		}
		//Se è stata fatta una richiesta per un prodotto non valido --> errore
		if(prod == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		CarrelloItem item = new CarrelloItem(prod, quantita);
		
		if("add".equals(azione)) {
			carrello.addItem(item);
		} else if("rem".equals(azione)) {
			carrello.removeItem(item);
		} else if("mod".equals(azione)) {
			carrello.modificaQuantita(idProd, quantita);
		} else if("dec".equals(azione)) {
			carrello.decrementaQuantita(idProd);
		}
		
		//Aggiorna il carrello in sessione
		sessione.setAttribute("carrello", carrello);
		
		//Risposta JSON
		response.setContentType("application/json");
		BigDecimal totale = carrello.getPrezzoTotale();
		JSONObject json = new JSONObject();
		json.put("totale", totale.toPlainString());
		
		response.getWriter().write(json.toString());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
