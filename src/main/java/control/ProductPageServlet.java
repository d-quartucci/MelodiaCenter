package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.CarrelloItem;
import model.Desidera;
import model.Prodotto;
import model.Utente;
import model.dao.DesideraDAO;
import model.dao.ProdottoDAO;
import utils.DesideraKey;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/ProductPageServlet")
public class ProductPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		if(request.getParameter("prodottoId") == null) {
			request.getSession().setAttribute("errorMessage", "Errore durante la creazione della pagina del prodotto: ID prodotto non valido.");
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
            return;
		}
		
		int idProd;
		try {
			idProd = Integer.parseInt(request.getParameter("prodottoId"));
		} catch(NumberFormatException ex) {
			request.getSession().setAttribute("errorMessage", "Errore durante la creazione della pagina del prodotto: ID prodotto non valido.");
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
            return;
		}
		
		//Se l'utente ha fatto l'accesso ha anche la possibilità di inserire un oggetto nella wishlist
		//Qui controlliamo se il prodotto è già presente nella wishlist
		boolean inWishlist = false;
		//Controlliamo anche se l'utente è loggato, per mostrare o meno il pulsante di wishlist
		boolean isLogged = false;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("utente") != null) {
			isLogged = true;
			Utente user = (Utente) session.getAttribute("utente");
			int idUser = user.getId();
			DesideraDAO dDAO = new DesideraDAO(ds);
			try{
				Desidera dBean = (Desidera) dDAO.doRetrieveByKey(new DesideraKey(idUser, idProd));
				//Se  ho trovato un match nel db, l'utente desidera il prodotto
				if(dBean != null) {
					inWishlist = true;
				}
			} catch(SQLException ex) {
				ex.printStackTrace();
				request.getSession().setAttribute("errorMessage", "Errore durante la creazione della pagina del prodotto: " + ex.getMessage());
	            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
	            return;
			}
		}
		request.setAttribute("inWishlist", inWishlist);
		request.setAttribute("isLogged", isLogged);
		
		
		ProdottoDAO pDAO =  new ProdottoDAO(ds);
		try{
			//Informazioni sul prodotto da passare alla JSP
			Prodotto pBean = pDAO.doRetrieveByKey(idProd);
			if(pBean == null) {
				request.getSession().setAttribute("errorMessage", "Errore durante la creazione della pagina del prodotto: prodotto non trovato.");
	            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
	            return;
			}
			request.setAttribute("prodotto", pBean);
			//Controllo se il prodotto si trova già nel carrello per notificare l'utente
			boolean inCart = false;
			if(session != null) {
				Carrello cart = (Carrello) session.getAttribute("carrello");
				if(cart != null) {
					ArrayList<CarrelloItem> itemsList = cart.getListaItem();
					for(CarrelloItem item : itemsList) {
						if(item.getProdotto().getId() == idProd) {
							inCart = true;
							break;
						}
					}
				}
			}
			request.setAttribute("inCart", inCart);
			
			request.getRequestDispatcher("/common/product.jsp").forward(request, response);
		} catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore durante la creazione della pagina del prodotto: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
            return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
