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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/admin/Prodotti")
public class AdminProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
    public AdminProdottiServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
			ProdottoDAO pDAO = new ProdottoDAO(ds);
			CategoriaDAO cDAO = new CategoriaDAO(ds);
			ArrayList<Prodotto> prodotti = pDAO.doRetrieveAll();
			ArrayList<Categoria>categorie = cDAO.doRetrieveAll();
			
			request.setAttribute("categorie", categorie);
			request.setAttribute("prodotti", prodotti);
			request.getRequestDispatcher("/admin/gestioneProdotti.jsp").forward(request, response);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore gestione prodotti: " + ex.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
