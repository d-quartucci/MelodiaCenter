package control.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Recensione;
import model.Utente;
import model.dao.RecensioneDAO;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Servlet implementation class CreaRecensione
 */
@WebServlet("/user/CreateRecensioneServlet")
public class CreateRecensioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateRecensioneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("utente");
		int idProd = 0;
		int voto = 0;
		try{
			idProd = Integer.parseInt(request.getParameter("prodottoId"));
			voto = Integer.parseInt(request.getParameter("voto"));
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore: ID dell'prodotto non valido.");
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		Recensione rBean = new Recensione();
		rBean.setUtenteId(user.getId());
		rBean.setProdottoId(idProd);
		rBean.setVoto(voto);
		rBean.setTesto(request.getParameter("recensioneInput"));
		rBean.setDataIns(new Date(System.currentTimeMillis()));
		
		RecensioneDAO rDAO = new RecensioneDAO(ds);
		try {
			rDAO.doSaveOrUpdate(rBean);
		} catch(SQLException ex) {
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore di creazione della recensione: " + ex);
			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		response.sendRedirect(request.getContextPath() + "/ProductPageServlet?prodottoId=" + idProd);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
