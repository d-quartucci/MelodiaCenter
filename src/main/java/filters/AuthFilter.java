package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public AuthFilter() {
        super();
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		Utente u = null;
		HttpSession sessione = httpRequest.getSession(false);
		if(sessione != null) {
			u = (Utente) sessione.getAttribute("utente");
		}
		
		String path = httpRequest.getServletPath();
		
		//Controllo per le pagine user
		if(path.contains("/user/") && u == null) {
			sessione = httpRequest.getSession();
			sessione.setAttribute("errorLogin", "Effettua l'accesso per continuare!");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			return;
		} 
		
		//Controllo per le pagine admin
		if(path.contains("/admin/") && (u == null || !"ADMIN".equals(u.getRuolo()))) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
			return;
		}
		
		chain.doFilter(request, response);
	}
}
