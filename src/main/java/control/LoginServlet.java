package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		//Non manteniamo il messaggio di errore ogni volta che accedo alla pagina di login (per essere scrupolosi)
		if(session != null && session.getAttribute("errorLogin") != null) {
			request.setAttribute("errorLogin", session.getAttribute("errorLogin"));
			session.removeAttribute("errorLogin");
		}
		//Utilizzo un booleano per vedere se l'ultima volta è stato richiesto di ricordare l'email
		//In tal caso, quando verrà fatto accesso alla JSP, la checkbox sarà selezionata
		Boolean checked = false;
		
		//Controllo se l'utente ha già fatto login
		if(session != null && session.getAttribute("utente") != null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		} else {
			//Prendo l'email salvata nel cookie
			String email = null;
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie c : cookies) {
					if("ricordaEmail".equals(c.getName())) {
						//Nei cookie non possono essere ricordati determinati caratteri speciali (es. @), che vengono quindi codificati
						email = URLDecoder.decode(c.getValue(), "UTF-8"); 
					}
				}
			}
			if(email != null) {
				checked = true;
			}
			request.setAttribute("emailRicordata", email);
		}
		request.setAttribute("wasChecked", checked);
		request.getRequestDispatcher("/common/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
