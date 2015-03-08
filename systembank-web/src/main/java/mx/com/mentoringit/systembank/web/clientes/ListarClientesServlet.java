package mx.com.mentoringit.systembank.web.clientes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import mx.com.mentoringit.systembank.dao.impl.ClienteDAOImpl;
import mx.com.mentoringit.systembank.dao.interfaces.ClienteDAO;
import mx.com.mentoringit.systembank.dao.interfaces.CuentaDAO;
import mx.com.mentoringit.systembank.dto.Cliente;

/**
 * Servlet implementation class ListarClientesServlet
 */
public class ListarClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteDAO clienteDAO;
	private CuentaDAO cuentaDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		ApplicationContext context =
			WebApplicationContextUtils.getWebApplicationContext(getServletContext()); 
		clienteDAO = (ClienteDAO) context.getBean("ClienteDAO");		
		cuentaDAO = (CuentaDAO) context.getBean("CuentaDAO");
		
		List<Cliente> clientes = new ArrayList<Cliente>();		
		try {
			clientes = clienteDAO.listarClientes();			
			for(Cliente cliente: clientes){
				cliente.setCuentas(
						cuentaDAO.obtenerCuentasCliente(cliente.getId()));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("clientes", clientes);
		result.put("total", clientes.size());
		result.put("success", true);
		
		Gson convertidor = new Gson();
		String mapaJson = convertidor.toJson(result);
		writer.println(mapaJson);
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
