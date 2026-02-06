package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ordine;
import model.dao.OrdineDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/admin/AdminFilterOrdini")
public class AdminFilterOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminFilterOrdini() {
        super();
    }


	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
	
		try {
			//prendo i parametri dalla richiesta
			String dataIn = request.getParameter("dataIn"); 
			String dataFin= request.getParameter("dataFin");
			String ord = request.getParameter("ord");
			
			Timestamp dataInDate = null;
			Timestamp dataFinDate = null;
			
			//Controllo se l'admin ha inserito le date e creo l'oggetto Timestamp
			//Inserendo l'orario di inizio e fine giornata in modo da considerare tutto il ciclo giornaliero
			//Cosi facendo l'admin inserirà solo le date senza orario
	        if (dataIn != null && !dataIn.isEmpty()) {
	            dataInDate = Timestamp.valueOf(dataIn + " 00:00:00");
	        }

	        if (dataFin != null && !dataFin.isEmpty()) {
	            dataFinDate = Timestamp.valueOf(dataFin + " 23:59:59");
	        }
	        response.setContentType("application/json");
	        
	        OrdineDAO oDAO = new OrdineDAO(ds);
			ArrayList <Ordine> ordini = oDAO.doRetrieveByFilter(dataInDate, dataFinDate, ord);
			
			//Risposta JSON
			PrintWriter out = response.getWriter();
			JSONArray json = new JSONArray(ordini);
			out.print(json.toString());
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore di utilizzo dei filtriAdmin: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
