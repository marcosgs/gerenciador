package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns ="/FazTudo")
public class FazTudo extends HttpServlet{
	
	private static final long serialVersionUID = 6626322586019870362L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String tarefa = req.getParameter("tarefa");
		
		if (tarefa == null)
	        throw new IllegalArgumentException(
	                "Voc� esqueceu de passar a tarefa");
		
		String nomeDaClasse= "br.com.alura.gerenciador.web." + tarefa;
		
		try {
			Class<?> type = Class.forName(nomeDaClasse);
			Tarefa instancia = (Tarefa) type.newInstance();
			String pagina= instancia.executa(req,resp);
			RequestDispatcher disp = req.getRequestDispatcher(pagina); //Pega o Dispacher
			disp.forward(req, resp);
		}catch (Exception e) {
		    throw new ServletException(e);
		}
		
	}
}
