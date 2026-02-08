package control.admin;

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

@WebServlet("/admin/AdminProdottiServlet")
public class AdminProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminProdottiServlet() {
        super();
    }

//Mostra TUTTI i prodotti nel database
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		ProdottoDAO pDAO = new ProdottoDAO(ds);
		CategoriaDAO cDAO = new CategoriaDAO(ds);
		
		try {
			ArrayList<Prodotto> prodotti = pDAO.doRetrieveAll();
			ArrayList<Categoria>categorie = cDAO.doRetrieveAll();
			
			request.setAttribute("categorie", categorie);
			request.setAttribute("prodotti", prodotti);
			request.getRequestDispatcher("/admin/gestioneProdotti.jsp").forward(request, response);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore di accesso al gestore ordini: " + ex.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}

//Prende i dati dal form, li salva nel DATABASE e passa la logica per mostrala viene rendirizzata alla servlet stessa che effettua la GET
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
		
			String nome = request.getParameter("nome");
			String descr = request.getParameter("descr");
			int catg = Integer.parseInt(request.getParameter("categoria"));
			BigDecimal prezzo = new BigDecimal (request.getParameter("prezzo"));
		
			ProdottoDAO pDAO = new ProdottoDAO(ds);
			Prodotto prodotto = new Prodotto();
		
			prodotto.setNome(nome);
			prodotto.setDescrizione(descr);
			prodotto.setPrezzoAttuale(prezzo);
			prodotto.setCategoriaId(catg);
			prodotto.setAttivo(true);
			prodotto.setEvidenza(false);
		
			pDAO.doSaveOrUpdate(prodotto);
			
			ArrayList<Prodotto>	prodotti = pDAO.doRetrieveAll();		
			request.setAttribute("prodotti", prodotti);
			response.sendRedirect(request.getContextPath() + "/admin/AdminProdottiServlet");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore Update: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}

}
