package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Prodotto;
import model.dao.ProdottoDAO;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.sql.DataSource;

@WebServlet("/admin/AdminUpdateProdottiServlet")
public class AdminUpdateProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminUpdateProdottiServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
			
			String nome = request.getParameter("nome");
			String descr = request.getParameter("descr");
			int id = Integer.parseInt(request.getParameter("id"));
			BigDecimal prezzo = new BigDecimal (request.getParameter("prezzo"));
			Boolean attivo = Boolean.parseBoolean(request.getParameter("attivo"));
			Boolean evidenza = Boolean.parseBoolean(request.getParameter("evidenza"));
			
			ProdottoDAO pDAO = new ProdottoDAO(ds);
			Prodotto prodotto = pDAO.doRetrieveByKey(id);
			
			prodotto.setNome(nome);
			prodotto.setDescrizione(descr);
			prodotto.setPrezzoAttuale(prezzo);
			prodotto.setAttivo(attivo);
			prodotto.setEvidenza(evidenza);
			
			pDAO.doSaveOrUpdate(prodotto);
			
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
