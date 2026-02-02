package control;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import model.Utente;
import model.dao.UtenteDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = toHash(request.getParameter("password"));
		
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		UtenteDAO uDAO = new UtenteDAO(ds);
		try {
			Utente u = uDAO.doRetrieveByLogin(email, password);
			//Proviamo a fare il login
			if(u != null) {
				HttpSession sessione = request.getSession(); //Prendiamo la sessione per salvare le informazioni dell'utente
				sessione.setAttribute("utente", u);
				response.sendRedirect(request.getContextPath() + "/common/index.jsp");
			}
			else {
				request.setAttribute("error", "Email o password non validi!");
				request.getRequestDispatcher("/common/login.jsp").forward(request, response);
			}
		} catch(SQLException e) {
			e.printStackTrace();
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
