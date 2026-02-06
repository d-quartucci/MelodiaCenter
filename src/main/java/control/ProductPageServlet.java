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
import model.Ordine;
import model.Prodotto;
import model.Recensione;
import model.RecensioneUtente;
import model.RigaOrdine;
import model.Utente;
import model.dao.DesideraDAO;
import model.dao.OrdineDAO;
import model.dao.ProdottoDAO;
import model.dao.RecensioneDAO;
import model.dao.RigaOrdineDAO;
import model.dao.UtenteDAO;
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
		int idUser = 0;
		//Controllo se l'utente ha già scritto in passato una recensione per questo prodotto
		//Se ha già recensito il prodotto, non può creare una nuova recensione
		boolean haRecensito = false;
		
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
		
		//SEZIONE WISHLIST
		
		//Se l'utente ha fatto l'accesso ha anche la possibilità di inserire un oggetto nella wishlist
		//Qui controlliamo se il prodotto è già presente nella wishlist
		boolean inWishlist = false;
		//Controlliamo anche se l'utente è loggato, per mostrare o meno il pulsante di wishlist
		boolean isLogged = false;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("utente") != null) {
			isLogged = true;
			
			Utente user = (Utente) session.getAttribute("utente");
			idUser = user.getId();
			
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
		
		//SEZIONE PRODOTTO
		
		ProdottoDAO pDAO =  new ProdottoDAO(ds);
		try{
			Prodotto pBean = pDAO.doRetrieveByKey(idProd);
			if(pBean == null) {
				request.getSession().setAttribute("errorMessage", "Errore durante la creazione della pagina del prodotto: prodotto non trovato.");
	            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
	            return;
			}
			request.setAttribute("prodotto", pBean);
			
			//CONTROLLO CARRELLO
			
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
			
			//SEZIONE RECENSIONI
			
			RecensioneDAO rDAO = new RecensioneDAO(ds);
			ArrayList<Recensione> listaRecensioni = new ArrayList<>();
			listaRecensioni = rDAO.doRetrieveByProdottoID(pBean.getId());
			ArrayList<RecensioneUtente> recensioniUtente = new ArrayList<>();
			
			int tempUserId;
			UtenteDAO uDAO = new UtenteDAO(ds);
			//Ad ogni recensione del prodotto associo le informazioni dell'utente che l'ha scritta
			for(Recensione rec : listaRecensioni) {
				RecensioneUtente recensioneTemp = new RecensioneUtente();
				recensioneTemp.setRecensione(rec);
				tempUserId = rec.getUtenteId();
				Utente tempUser = uDAO.doRetrieveByKey(tempUserId);
				recensioneTemp.setUtente(tempUser);
				recensioniUtente.add(recensioneTemp);
			}
			request.setAttribute("listaRecensioni", recensioniUtente);
			
			//Se trovo una recensione con id dell'utente pari a quella dell'utente che ha fatto la richiesta vuol dire che
			//l'utente ha già scritto una recensione in passato per il prodotto di cui sta visualizzando la pagina
			for(Recensione rec : listaRecensioni) {
				if(isLogged && rec.getUtenteId() == idUser) {
					haRecensito = true;
					break;
				}
			}
			
			//Controllo se l'utente ha acquistato il prodotto in passato
			boolean haAcquistato = false;
			if(isLogged) {
				OrdineDAO oDAO = new OrdineDAO(ds);
				RigaOrdineDAO roDAO = new RigaOrdineDAO(ds);
				ArrayList<Ordine> ordineUtente = oDAO.doRetrieveByUtenteID(idUser);
				for(Ordine ord : ordineUtente) {
					ArrayList<RigaOrdine> righeOrdine = roDAO.doRetrieveByOrderID(ord.getId());
					for(RigaOrdine riga : righeOrdine) {
						if(riga.getProdottoId() == idProd) {
							haAcquistato = true;
							break;
						}
					}
					if(haAcquistato == true) {
						break;
					}
				}
			}

			boolean puoRecensire = isLogged && !haRecensito && haAcquistato;
			request.setAttribute("puoRecensire", puoRecensire);
			
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
