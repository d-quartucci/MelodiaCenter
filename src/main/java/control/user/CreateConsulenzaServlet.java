package control.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Consulenza;
import model.Prodotto;
import model.Utente;
import model.dao.ConsulenzaDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;


@WebServlet("/user/CreateConsulenzaServlet")
public class CreateConsulenzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateConsulenzaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("utente");
		int idUser = user.getId();
		
		int idProd;
		try{
			idProd = Integer.parseInt(request.getParameter("idProd"));
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore: l'id del prodotto non è valido.");
		    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		
		ProdottoDAO pDAO = new ProdottoDAO(ds);
		Prodotto pBean = null;
		try {
			pBean = pDAO.doRetrieveByKey(idProd);
			if(pBean == null) {
				session.setAttribute("errorMessage", "Errore: prodotto non trovato.");
			    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
			    return;
			}
			request.setAttribute("prodotto", pBean);
		} catch(SQLException ex){
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore di elaborazione della richiesta.");
		    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		
		//Per ricordare per quale prodotto è stata fatta la richiesta, alteriamo il messaggio
		String userMessage = "(Riguardo il prodotto: \"" + pBean.getNome() + "\")\n" + request.getParameter("messaggioConsulenza");
		
		Consulenza consulBean = new Consulenza();
		consulBean.setIsAperto(true);
		consulBean.setMessUtente(userMessage);
		consulBean.setUtenteId(idUser);
		consulBean.setRispAdmin("Ti risponderemo al più presto!");
		consulBean.setDataRichiesta(new Date(System.currentTimeMillis()));
		ConsulenzaDAO cDAO = new ConsulenzaDAO(ds);
		try {
			cDAO.doSaveOrUpdate(consulBean);
		} catch (SQLException ex) {
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore di generazione della richiesta di consulenza.");
		    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		request.setAttribute("consulenza", consulBean);
		
		response.sendRedirect(request.getContextPath() + "/user/consulenzaList");
	}

}
