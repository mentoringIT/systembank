package mx.com.mentoringit.systembank.web.clientes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import mx.com.mentoringit.systembank.dao.impl.ClienteDAOImpl;
import mx.com.mentoringit.systembank.dao.impl.CuentaDAOImpl;
import mx.com.mentoringit.systembank.dao.interfaces.ClienteDAO;
import mx.com.mentoringit.systembank.dao.interfaces.CuentaDAO;
import mx.com.mentoringit.systembank.dto.Cliente;
import mx.com.mentoringit.systembank.dto.Cuenta;

/**
 * Servlet implementation class ReporteClientesServlet
 */
public class ReporteClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClienteDAO clienteDAO;
		CuentaDAO cuentaDAO;
		
		ApplicationContext context = 
			WebApplicationContextUtils.getWebApplicationContext(getServletContext());	
		clienteDAO = (ClienteDAO) context.getBean("ClienteDAO");
		cuentaDAO = (CuentaDAO) context.getBean("CuentaDAO");
		
		ServletOutputStream output = response.getOutputStream();
		File reporte, imagen;
		byte[] bytes = null;
		
		reporte = new File ( getServletConfig().getServletContext().getRealPath("/jasper/clientes.jasper") );
		List<Cliente> clientes = null;
		List<Cuenta> cuentas = null;
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		InputStream inputStream;
		imagen = new File ( getServletConfig().getServletContext().getRealPath("/imagenes/logo.jpg") );
		inputStream = new FileInputStream(imagen); 
		
		mapa.put("fecha", new Date());
		mapa.put("imagen", inputStream);
		
		try {
			
			clientes = clienteDAO.listarClientes();
			for(Cliente cliente : clientes){
				cliente.setCuentas(
					cuentaDAO.obtenerCuentasCliente(cliente.getId())
					);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			bytes = JasperRunManager.runReportToPdf(
					reporte.getPath(), 
					mapa,
					new JRBeanCollectionDataSource(clientes)
					);
			
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment");
			response.setContentLength(bytes.length);
			
			output.write(bytes, 0, bytes.length);
			output.flush();
			output.close();
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
