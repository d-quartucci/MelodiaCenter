package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Consulenza;
import model.dao.ConsulenzaDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

@WebServlet("/admin/RispostaConsulenzaServlet")
public class RispostaConsulenzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RispostaConsulenzaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		HttpSession session = request.getSession();
		String rispostaAdmin = request.getParameter("messaggioRisposta");
		
		
		int consId = Integer.parseInt(request.getParameter("consId"));
		
		ConsulenzaDAO cDAO = new ConsulenzaDAO(ds);
		Consulenza toUpdate = null;
		try {
			toUpdate = cDAO.doRetrieveByKey(consId);
			toUpdate.setIsAperto(false);
			toUpdate.setRispAdmin(rispostaAdmin);
			cDAO.doSaveOrUpdate(toUpdate);
		} catch(SQLException ex) {
			ex.printStackTrace();
			session.setAttribute("errorMessage", "Errore di elaborazione della richiesta.");
		    response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		    return;
		}
		
		//Faccio il refresh della pagina
		response.sendRedirect(request.getContextPath() + "/user/ConsulenzaPageServlet?consId=" + toUpdate.getId());
	}

}
