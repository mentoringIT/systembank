package mx.com.mentoringit.systembank.dao;

import java.sql.Date;
import java.sql.SQLException;

import junit.framework.Assert;
import mx.com.mentoringit.systembank.dao.impl.MovimientoDAOImpl;
import mx.com.mentoringit.systembank.dao.interfaces.MovimientoDAO;
import mx.com.mentoringit.systembank.dto.Movimiento;
import mx.com.mentoringit.systembank.dto.TipoMovimiento;

import org.junit.Test;

public class MovimientoDAOTest {

	private MovimientoDAO movimientoDAO = new MovimientoDAOImpl();
	
	@Test
	public void testCargo() {
		
		Movimiento movimiento = new Movimiento();
		movimiento.setIdCuenta(8);
		movimiento.setFecha(new Date(new java.util.Date().getTime()));
		movimiento.setIdTipoMovimiento(TipoMovimiento.CARGO.getId());
		movimiento.setTipoMovimiento(TipoMovimiento.CARGO);
		movimiento.setMonto(300);
		
		try {
			Assert.assertTrue(movimientoDAO.registrarMovimiento(movimiento));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAbono() {
		
		Movimiento movimiento = new Movimiento();
		movimiento.setIdCuenta(9);
		movimiento.setFecha(new Date(new java.util.Date().getTime()));
		movimiento.setIdTipoMovimiento(TipoMovimiento.ABONO.getId());
		movimiento.setTipoMovimiento(TipoMovimiento.ABONO);
		movimiento.setMonto(250);
		
		try {
			Assert.assertTrue(movimientoDAO.registrarMovimiento(movimiento));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
