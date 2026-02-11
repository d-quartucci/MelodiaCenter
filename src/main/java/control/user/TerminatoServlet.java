package control.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/user/TerminatoServlet")
public class TerminatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TerminatoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate oggi = LocalDate.now();
		
		LocalDate prossimaSettimana = oggi.plusWeeks(1);
		
		request.setAttribute("data", prossimaSettimana);
		
		request.getRequestDispatcher("/user/terminato.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}