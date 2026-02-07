package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Categoria;
import model.Prodotto;
import model.Utente;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		HttpSession session = request.getSession(false);
		//Controllo se l'utente è loggato, in tal caso passo alla servlet le informazioni sull'utente
		if(session != null && session.getAttribute("utente") != null) {
			Utente user = (Utente) session.getAttribute("utente");
			request.setAttribute("utente", user);
		}
		
		//Creo la lista dei prodotti in evidenza
		ArrayList<Prodotto> listaInEvidenza = new ArrayList<>();
		//Creo la lista dei prodotti più venduti
		ArrayList<Prodotto> listaBestSellers = new ArrayList<>();
		//Creo la lista delle categorie
		ArrayList<Categoria> listaCategorie = new ArrayList<>();
		
		ProdottoDAO pDAO = new ProdottoDAO(ds);
		CategoriaDAO cDAO = new CategoriaDAO(ds);
		try {
			listaInEvidenza = pDAO.doRetrieveInEvidenza();
			listaBestSellers = pDAO.doRetrieveBestSellers();
			listaCategorie = cDAO.doRetrieveAll(); 
		} catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore durante il caricamento della home! Ci scusiamo per l'inconveniente.");
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
		
		request.setAttribute("listaInEvidenza", listaInEvidenza);
		request.setAttribute("listaBestSellers", listaBestSellers);
		request.setAttribute("listaCategorie", listaCategorie);
		
		request.getRequestDispatcher("/common/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
