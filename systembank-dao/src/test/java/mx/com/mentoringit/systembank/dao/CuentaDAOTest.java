package mx.com.mentoringit.systembank.dao;

import java.sql.SQLException;
import java.util.Random;

import junit.framework.Assert;
import mx.com.mentoringit.systembank.dao.impl.CuentaDAOImpl;
import mx.com.mentoringit.systembank.dao.interfaces.CuentaDAO;
import mx.com.mentoringit.systembank.dto.Cuenta;
import mx.com.mentoringit.systembank.dto.TipoCuenta;

import org.junit.Test;

public class CuentaDAOTest {

	private CuentaDAO cuentaDAO = new CuentaDAOImpl();
	
	private Random rand = new Random();
	
	private final int cuentaUno = rand.nextInt( Integer.MAX_VALUE ) + 1;
	
	private final int cuentaDos = rand.nextInt( Integer.MAX_VALUE ) + 1;
	
	@Test
	public void testAgregar() {
		Cuenta cuenta = new Cuenta();
		cuenta.setNumeroCuenta(cuentaUno);
		cuenta.setIdCliente(7);
		cuenta.setIdTipoCuenta(TipoCuenta.DEBITO.getId());		
		cuenta.setTipoCuenta(TipoCuenta.DEBITO);
		cuenta.setSaldo(1000);
		
		try {
			Assert.assertTrue(cuentaDAO.agregar(cuenta));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cuenta = new Cuenta();
		cuenta.setNumeroCuenta(cuentaDos);
		cuenta.setIdCliente(7);
		cuenta.setIdTipoCuenta(TipoCuenta.FONDOS.getId());		
		cuenta.setTipoCuenta(TipoCuenta.FONDOS);
		cuenta.setSaldo(1000);

		try {
			Assert.assertTrue(cuentaDAO.agregar(cuenta));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
