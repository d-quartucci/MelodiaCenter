package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categoria;
import model.Prodotto;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
//Viene chiamata quando l'utente accede alla pagina catalogo
@WebServlet("/CatalogServlet")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CatalogServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try{
			ProdottoDAO pDAO = new ProdottoDAO(ds);
			ArrayList<Prodotto> listaProdotti = pDAO.doRetrieveAll();
			
			//Trovo il prezzo massimo tra tutti i prodotti per avere lo slider con max dinamico
			BigDecimal maxPrice = BigDecimal.ZERO;
			BigDecimal temp;
			for(Prodotto prod : listaProdotti) {
				temp = prod.getPrezzoAttuale();
				if(temp.compareTo(maxPrice) > 0) {
					maxPrice = temp;
				}
			}
			request.setAttribute("prezzoMax", maxPrice.intValue());
			
			//Serve a creare le opzioni nella select per il filtro della categoria
			CategoriaDAO cDAO = new CategoriaDAO(ds);
			ArrayList<Categoria> listaCategorie = cDAO.doRetrieveAll();
			request.setAttribute("listaCategorie", listaCategorie);
			
			//Leggo i filtri nel caso in cui ci siano, altrimenti sono inseriti valori di default
			String ricerca = request.getParameter("barraDiRicerca"); 
			if(ricerca == null)
				ricerca = "";
			String ordine = request.getParameter("ordinaPrezzo"); 
			if(ordine == null)
				ordine = "prezzoCrescente";
			String categoria = request.getParameter("categoria");
			if(categoria == null)
				categoria = "0";
			String prezzoMaxFiltro = request.getParameter("prezzoFiltro");
			if(prezzoMaxFiltro == null)
				prezzoMaxFiltro = maxPrice.toPlainString();
			
			//Applico i filtri
			listaProdotti = pDAO.doRetrieveByFilters(ricerca, ordine, categoria, prezzoMaxFiltro);
			request.setAttribute("listaProdotti", listaProdotti);
			
			request.getRequestDispatcher("/common/catalog.jsp").forward(request, response);
		} catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore di accesso al catalogo: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
