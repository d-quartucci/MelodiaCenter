package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Utente;
import model.dao.UtenteDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		
		//Creo il nuovo utente con i parametri dati dal form
		Utente u = new Utente();
		u.setNome(request.getParameter("nome"));
		u.setCognome(request.getParameter("cognome"));
		u.setEmail(request.getParameter("email"));
		u.setPassword(toHash(request.getParameter("password")));
		u.setRuolo("USER");
		u.setTelefono(request.getParameter("tel"));
		
		try {
			UtenteDAO uDAO = new UtenteDAO(ds);
			uDAO.doSaveOrUpdate(u); //Non verrà fatto mai l'update perché viene verificato per mezzo di JS che l'email non sia già stata utilizzata in precedenza 
			response.sendRedirect(request.getContextPath() + "/common/login.jsp");
		} catch(SQLException ex) {
			ex.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
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
