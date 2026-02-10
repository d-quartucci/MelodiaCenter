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

import javax.sql.DataSource;

@WebServlet("/admin/AdminNuovaCategoriaServlet")
public class AdminNuovaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminNuovaCategoriaServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
			String nome = request.getParameter("nome");
			String descr = request.getParameter("descr");
			
			CategoriaDAO cDAO = new CategoriaDAO(ds);
			Categoria categoria = new Categoria();
			
			categoria.setNome(nome);
			categoria.setDescr(descr);
			
			cDAO.doSaveOrUpdate(categoria);
			
			response.sendRedirect(request.getContextPath() + "/admin/AdminCategorieServlet");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore gestione categorie: " + ex.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
