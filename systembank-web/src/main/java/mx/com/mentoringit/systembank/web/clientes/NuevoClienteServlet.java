package mx.com.mentoringit.systembank.web.clientes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mx.com.mentoringit.systembank.dao.impl.ClienteDAOImpl;
import mx.com.mentoringit.systembank.dao.interfaces.ClienteDAO;
import mx.com.mentoringit.systembank.dto.Banco;
import mx.com.mentoringit.systembank.dto.Cliente;

/**
 * Servlet implementation class NuevoClienteServlet
 */
public class NuevoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteDAO clienteDAO = new ClienteDAOImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevoClienteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("<h1>Nuevo</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Cliente cliente = new Cliente();
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellidoPaterno(request.getParameter("apellidoPaterno"));
		cliente.setApellidoMaterno(request.getParameter("apellidoMaterno"));
		cliente.setEdad(Integer.parseInt(request.getParameter("edad")));
		
		Banco banco = Banco.valueOf(request.getParameter("banco")); 
		cliente.setBancoId(banco.getId());
		
		Map<String, Object> result = new HashMap<String, Object>(); 
		
		try {
			if(clienteDAO.agregar(cliente)) {
				result.put("success", true);
			}
		} catch (SQLException e) {
			result.put("success", false);
			result.put("error", e.getMessage());
			e.printStackTrace();
		}
		Gson gson = new Gson();
		writer.println(gson.toJson(result));
		
	}

}
