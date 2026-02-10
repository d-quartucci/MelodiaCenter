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

@WebServlet("/admin/AdminUpdateCategorieServlet")
public class AdminUpdateCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminUpdateCategorieServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
			
			String nome = request.getParameter("nome");
			String descr = request.getParameter("descr");
			int id = Integer.parseInt(request.getParameter("id"));
			
			CategoriaDAO cDAO = new CategoriaDAO(ds);
			Categoria categoria = cDAO.doRetrieveByKey(id);
			
			categoria.setNome(nome);
			categoria.setDescr(descr);

			cDAO.doSaveOrUpdate(categoria);
			
			// RISPOSTA PER AJAX:
		    response.setStatus(HttpServletResponse.SC_OK);
		    response.getWriter().write("OK");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore Update: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
