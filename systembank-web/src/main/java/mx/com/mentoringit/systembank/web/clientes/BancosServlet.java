package mx.com.mentoringit.systembank.web.clientes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mx.com.mentoringit.systembank.dto.Banco;

/**
 * Servlet implementation class BancosServlet
 */
public class BancosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BancosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writter = response.getWriter();		
		StringBuilder sb = new StringBuilder("{\"bancos\": [");
		for (Banco banco: Banco.values()){
			sb.append("{");
			sb.append("\"id\":\"");
			sb.append(banco.getId());
			sb.append("\",");
			
			sb.append("\"banco\":\"");
			sb.append(banco.getClave());
			sb.append("\"");
			
			sb.append("},");
		}	
		String out =sb.substring(0, sb.lastIndexOf(",")) + "]}";	
		writter.println(out);						
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
