package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Consulenza;
import model.dao.ConsulenzaDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;


@WebServlet("/admin/FilterConsulenze")
public class AdminFilterConsulenzeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminFilterConsulenzeServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
	
		try {
			//prendo i parametri dalla richiesta
			String dataIn = request.getParameter("dataIn"); 
			String dataFin= request.getParameter("dataFin");
			String ord = request.getParameter("ordinaData");
			String stato = request.getParameter("ordinaStato");
			
			Timestamp dataInDate = null;
			Timestamp dataFinDate = null;
			
	        if (dataIn != null && !dataIn.isEmpty()) {
	            dataInDate = Timestamp.valueOf(dataIn + " 00:00:00");
	        }

	        if (dataFin != null && !dataFin.isEmpty()) {
	            dataFinDate = Timestamp.valueOf(dataFin + " 23:59:59");
	        }
	        
	        ConsulenzaDAO cDAO = new ConsulenzaDAO(ds);
			ArrayList <Consulenza> consulenze= cDAO.doRetrieveByFilter(dataInDate, dataFinDate, stato ,ord);
			
			request.setAttribute("defaultIn", dataIn);
			request.setAttribute("defaultFin", dataFin);
			request.setAttribute("consulenze", consulenze);
			request.setAttribute("ordinaStato", stato);
			request.setAttribute("ordinaData", ord);
			request.getRequestDispatcher("/admin/gestioneConsulenza.jsp").forward(request, response);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore di utilizzo dei filtriConsulenza: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
