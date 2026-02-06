package control;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import model.Utente;
import model.dao.UtenteDAO;

@WebServlet("/DoLoginServlet")
public class DoLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DoLoginServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = toHash(request.getParameter("password"));
		String ricordaEmail = request.getParameter("ricordaCheckbox");
		
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		UtenteDAO uDAO = new UtenteDAO(ds);
		try {
			Utente u = uDAO.doRetrieveByLogin(email, password);
			//Proviamo a fare il login
			if(u != null) {
				HttpSession sessione = request.getSession(); //Prendiamo la sessione per salvare le informazioni dell'utente
				sessione.setAttribute("utente", u);
				//Controllo se la checkbox per ricordare l'email sia stata spuntata
				if("on".equals(ricordaEmail)) {
					//Utilizzo encode perché l'email contiene caratteri speciali
					Cookie cookie = new Cookie("ricordaEmail", URLEncoder.encode(email, "UTF-8"));
					cookie.setMaxAge(60 * 60 * 24 * 30); //Sono 30 giorni
					cookie.setPath("/");
					cookie.setHttpOnly(true); //Per evitare utilizzi da JS
					response.addCookie(cookie); //Aggiungo il cookie alla risposta
				} else {
					//Se la checkbox non era selezionata, vuol dire che devo cancellare il cookie
					Cookie cookie = new Cookie("ricordaEmail", "");
					cookie.setMaxAge(0);
					cookie.setPath("/");
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
				}
				response.sendRedirect(request.getContextPath() + "/home");
			}
			else {
				request.setAttribute("error", "Email o password non validi!");
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore durante il login: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	private String toHash(String password) {
		String hashString = null;
		try {
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			hashString = "";
			for (int i = 0; i < hash.length; i++) {
				hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1, 3);
			}
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return hashString;
	}
}
