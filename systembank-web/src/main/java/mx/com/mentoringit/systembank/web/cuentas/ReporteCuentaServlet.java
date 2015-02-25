package mx.com.mentoringit.systembank.web.cuentas;

import java.io.File;
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

import mx.com.mentoringit.systembank.dao.interfaces.ClienteDAO;
import mx.com.mentoringit.systembank.dao.interfaces.CuentaDAO;
import mx.com.mentoringit.systembank.dao.interfaces.MovimientoDAO;
import mx.com.mentoringit.systembank.dto.Cliente;
import mx.com.mentoringit.systembank.dto.Cuenta;
import mx.com.mentoringit.systembank.dto.Movimiento;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



/**
 * Servlet implementation class ReporteCuentaServlet
 */
public class ReporteCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteCuentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		CuentaDAO cuentaDAO = (CuentaDAO) context.getBean("CuentaDAO");
		MovimientoDAO movimientoDAO = (MovimientoDAO) context.getBean("MovimientoDAO");
	    ClienteDAO clienteDAO = (ClienteDAO) context.getBean("ClienteDAO");
		
		int cuentaId = Integer.parseInt(request.getParameter("cuenta"));
		Cuenta cuenta;
		Cliente cliente;
		List<Movimiento> movimientos = null;
		File reporte;
		InputStream imagen;		
		Map<String, Object> mapa = new HashMap<String, Object>();	
		ServletOutputStream output = response.getOutputStream();
		byte[] bytes = null;
		
		reporte = context.getResource("/jasper/cuenta.jasper").getFile(); 
		imagen = context.getResource("/imagenes/logo.jpg").getInputStream();
		
		mapa.put("fecha", new Date());
		mapa.put("imagen", imagen);
		
		try {
			cuenta = cuentaDAO.obtenerCuenta(cuentaId);
			cliente = clienteDAO.obtenerCliente(cuenta.getIdCliente());
			movimientos = movimientoDAO.obtenMovimientosCuenta(cuentaId);
			
			String titular = cliente.getApellidoPaterno() 
					+ " " + cliente.getApellidoMaterno()
					+ " " + cliente.getNombre();
			
			mapa.put("saldo", cuenta.getSaldo());
			mapa.put("cuenta", cuenta.getNumeroCuenta());
			mapa.put("titular", titular);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			bytes = JasperRunManager.runReportToPdf(
					reporte.getPath(), 
					mapa,
					new JRBeanCollectionDataSource(movimientos)
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
