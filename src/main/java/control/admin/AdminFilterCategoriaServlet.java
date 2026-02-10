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


@WebServlet("/admin/AdminFilterCategoriaServlet")
public class AdminFilterCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AdminFilterCategoriaServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try{
			CategoriaDAO cDAO = new CategoriaDAO(ds);
			String filtro = request.getParameter("filtroCtg");
			
			request.setAttribute("categorie", cDAO.doRetrieveAll());
			
			if(filtro == null)
				filtro = "0";
			ArrayList<Categoria> filtrate; 
			
			if(filtro.equals("0")) {
				filtrate = cDAO.doRetrieveAll();
			}else {
				filtrate = cDAO.doRetrieveByFilter(filtro);
				
			}
			
			request.setAttribute("ctgFiltrate", filtrate);
			
			request.getRequestDispatcher("/admin/gestioneCategorie.jsp").forward(request, response);
		} catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore: accesso alla gestione prodotti " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
