package control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Prodotto;
import model.dao.ProdottoDAO;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.sql.DataSource;

/**
 * Servlet implementation class AdminNuovoProdottoServlet
 */
@WebServlet(value="/admin/NuovoProdotto", initParams = {
		@WebInitParam (name = "fileUpload", value= "images")//nome della cartella images
})
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, //2MB
	    maxFileSize = 1024 * 1024 * 5,      // 5MB
	    maxRequestSize = 1024 * 1024 * 10)	// 50MB)

public class AdminNuovoProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 static String SAVE_DIR = "";  

    public AdminNuovoProdottoServlet() {
        super();
    }
    
    public void init() {
    	SAVE_DIR = getServletConfig().getInitParameter("fileUpload") ; //La locazione di dove verrà salvato il file
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("ds");
		
		try {
			//La prima parte restituisce il percorso assoluto 
			//sul disco della cartella dove l'applicazione è stata deployed sul server Tomcat.
			//Con + SAVE_DIR costruisco il percorso per arrivare alla cartella images
			String savePath = request.getServletContext().getRealPath("") + SAVE_DIR;
			File fileDir = new File(savePath);
			
			//Se la cartella non esiste la crea
			if(!fileDir.exists()) {
				fileDir.mkdir();
			}
			//recupero il file dal form
			Part filePart = request.getPart("image");
			String fileName = filePart.getSubmittedFileName();
			
			//salvo l'immagine sul disco
			filePart.write(savePath + File.separator + fileName);
			System.out.println("L'immagine è stata salvata qui: " + savePath);
			
			String nome = request.getParameter("nome");
			String descr = request.getParameter("descr");
			int catg = Integer.parseInt(request.getParameter("categoria"));
			BigDecimal prezzo = new BigDecimal (request.getParameter("prezzo"));
		
			ProdottoDAO pDAO = new ProdottoDAO(ds);
			Prodotto prodotto = new Prodotto();
			
			prodotto.setNome(nome);
			prodotto.setDescrizione(descr);
			prodotto.setPrezzoAttuale(prezzo);
			prodotto.setCategoriaId(catg);
			prodotto.setImgSrc(fileName);
			prodotto.setAttivo(true);
			prodotto.setEvidenza(false);
			prodotto.setDataAggiunta(Timestamp.valueOf(LocalDateTime.now()));
		
			pDAO.doSaveOrUpdate(prodotto);

			response.sendRedirect(request.getContextPath() + "/admin/Prodotti");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			request.getSession().setAttribute("errorMessage", "Errore Update: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/common/error.jsp");
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
