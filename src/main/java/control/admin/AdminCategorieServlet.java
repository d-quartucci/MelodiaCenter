package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categoria;
import model.dao.CategoriaDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/admin/Categorie")
public class AdminCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminCategorieServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
			CategoriaDAO cDAO = new CategoriaDAO(ds);
			ArrayList<Categoria>categorie = cDAO.doRetrieveAll();
			
			request.setAttribute("ctgFiltrate", categorie);
			request.setAttribute("categorie", categorie);
			request.getRequestDispatcher("/admin/gestioneCategorie.jsp").forward(request, response);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore gestione categorie: " + ex.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
