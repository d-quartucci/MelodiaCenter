package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


@WebServlet("/admin/DeleteCategoria")
public class AdminDeleteCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDeleteCategoria() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
			int idCtg = Integer.parseInt(request.getParameter("id"));
		
			ProdottoDAO pDAO = new ProdottoDAO(ds);
			CategoriaDAO cDAO = new CategoriaDAO(ds);
			
			ArrayList<Prodotto> prodotti = pDAO.doRetrieveAll();
			
			//Se la categoria non esiste 
			if(cDAO.doRetrieveByKey(idCtg)==null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
			//altrimenti se esiste
			else if(cDAO.doRetrieveByKey(idCtg)!=null) {
				//Cerco per ogni prodotto nel DataBase
				for(Prodotto p : prodotti){
					int idCtgProd = p.getCategoriaId();//id categoria del prodotto
	
					if(idCtgProd == idCtg) { //se l'id della categoria è uguale all'id della categoria del prodotto
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
						return;
					}
				}
			}
			cDAO.doDeleteByKey(idCtg);
			response.setStatus(HttpServletResponse.SC_OK);
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore cancellazione del prodotto: " + ex.getMessage());
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
