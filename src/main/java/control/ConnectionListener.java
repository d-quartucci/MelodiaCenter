package control;
import javax.naming.Context;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ConnectionListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce){
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/MelodiaCenterDB");
			
			ServletContext context = sce.getServletContext();
			context.setAttribute("ds", ds);
			
			System.out.println("DataSource inizializzato con successo.\n");
		} catch(NamingException ex) {
			System.out.println("Errore di naming nell'inizializzazione del DataSource.\n");
			ex.printStackTrace();
		} catch(Exception ex) {
			System.out.println("Errore generico di inizializzazione del DataSource.\n");
			ex.printStackTrace();
		}
	}
}
