package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ProdottoDAO;
import model.dao.RigaOrdineDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

@WebServlet("/admin/DeleteProdotto")
public class AdminDeleteProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDeleteProdotto() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			DataSource ds = (DataSource) getServletContext().getAttribute("ds");
			
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			ProdottoDAO pDAO = new ProdottoDAO(ds);
			RigaOrdineDAO rDAO = new RigaOrdineDAO(ds);
			
			//Se il prodotto non esiste 
			if(pDAO.doRetrieveByKey(id)==null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
			//Se il prodotto esiste e si trova in una riga ordine rifiuto la richiesta di cancellazione
			else if(pDAO.doRetrieveByKey(id)!=null && rDAO.doRetrieveByProdottoID(id) != null){
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
			//Se il prodotto esiste e non è stato ordinato almeno una volta allora si può cancellare
			else {
				response.setStatus(HttpServletResponse.SC_OK);
				pDAO.doDeleteByKey(id);
			}
			
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
