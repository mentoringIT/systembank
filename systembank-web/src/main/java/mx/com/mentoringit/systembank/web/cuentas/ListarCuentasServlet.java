package mx.com.mentoringit.systembank.web.cuentas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import mx.com.mentoringit.systembank.dao.interfaces.CuentaDAO;
import mx.com.mentoringit.systembank.dto.Cuenta;

/**
 * Servlet implementation class ListarCuentasServlet
 */
public class ListarCuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CuentaDAO cuentaDAO; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarCuentasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		ApplicationContext context =
			WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		cuentaDAO = (CuentaDAO)context.getBean("CuentaDAO");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		int clienteId = Integer.parseInt(request.getParameter("clienteId").toString());
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			cuentas = cuentaDAO.obtenerCuentasCliente(clienteId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		result.put("cuentas", cuentas);
		result.put("total", cuentas.size());
		result.put("success", true);
	
		Gson convertidor = new Gson();
		writer.println(convertidor.toJson(result));
		writer.close();
	}

}
