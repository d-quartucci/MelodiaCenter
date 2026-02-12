package control.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.CarrelloItem;
import model.Desidera;
import model.Utente;
import model.WishlistItem;
import model.dao.DesideraDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

@WebServlet("/user/wishlist")
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WishlistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) request.getServletContext().getAttribute("ds");
		DesideraDAO dDAO = new DesideraDAO(ds);
		ProdottoDAO pDAO = new ProdottoDAO(ds);
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("utente");
		
		ArrayList<Desidera> wishlist = new ArrayList<>();
		//Utilizzo una classe ausiliaria con la quale posso ricordare le informazioni dei prodotti nella wishlist
		ArrayList<WishlistItem> wishlistItems = new ArrayList<>();
		try {
			wishlist = dDAO.doRetrieveByUserID(user.getId());
			//Ad ogni elemento nella wishlist associo le informazioni del prodotto, ottenendo un WishlistItem
			for(Desidera des : wishlist) {
				WishlistItem item = new WishlistItem();
				item.setDesidera(des);
				item.setProdotto(pDAO.doRetrieveByKey(des.getKey().getSecond()));
				wishlistItems.add(item);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore durante l'accesso alla wishlist: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
		request.setAttribute("wishlist", wishlistItems);
		request.getRequestDispatcher("/user/wishlist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
