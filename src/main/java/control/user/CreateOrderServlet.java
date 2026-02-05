package control.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.CarrelloItem;
import model.Ordine;
import model.RigaOrdine;
import model.Utente;
import model.dao.OrdineDAO;
import model.dao.RigaOrdineDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.time.LocalDateTime;

import javax.sql.DataSource;

@WebServlet("/user/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateOrderServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		HttpSession session = request.getSession();
		Carrello cart = (Carrello) session.getAttribute("carrello");
		ArrayList<CarrelloItem> cartItems = cart.getListaItem();
		Utente user = (Utente) session.getAttribute("utente");
		
		Ordine order = new Ordine();
		order.setUtenteId(user.getId());
		order.setTotale(cart.getPrezzoTotale());
		order.setData(Timestamp.valueOf(LocalDateTime.now()));
		
		String via = request.getParameter("via");
		String civico = request.getParameter("civico");
		String citta = request.getParameter("citta");
		String provincia = request.getParameter("provincia");
		String cap = request.getParameter("cap");

		String indirizzo = via + " " + civico + ", " + citta + " (" + provincia + ") " + cap;
		order.setIndSpedizione(indirizzo);
		
		OrdineDAO oDAO = new OrdineDAO(ds);
		
		try {
			//Per "OrderDAO" doSaveOrUpdate salva anche l'id dell'ordine generato nel bean
			//Ci serve l'id dell'ordine per creare nella maniera corretta i bean RigaOrdine
			oDAO.doSaveOrUpdate(order); 
			
			//A questo punto creo i bean RigaOrdine e li inserisco nel DB
			RigaOrdineDAO roDAO = new RigaOrdineDAO(ds);
			//Per ogni elemento del carrello avremo una diversa RigaOrdine
			for(CarrelloItem item : cartItems) {
				RigaOrdine row = new RigaOrdine();
				row.setOrdineId(order.getId());
				row.setProdottoId(item.getProdotto().getId());
				row.setProdottoNome(item.getProdotto().getNome());
				row.setPrezzoAcq(item.getProdotto().getPrezzoAttuale().multiply(BigDecimal.valueOf(item.getQuantita())));
				row.setQuant(item.getQuantita());
				roDAO.doSaveOrUpdate(row);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore durante la creazione dell'ordine: " + ex.getMessage());
		    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		//Svuoto il carrello
		session.removeAttribute("carrello");
		response.sendRedirect(request.getContextPath() + "/user/terminato.jsp");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
