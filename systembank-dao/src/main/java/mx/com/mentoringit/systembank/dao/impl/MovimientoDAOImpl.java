package mx.com.mentoringit.systembank.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.com.mentoringit.systembank.dao.ConnectionFactory;
import mx.com.mentoringit.systembank.dao.interfaces.MovimientoDAO;
import mx.com.mentoringit.systembank.dto.Movimiento;
import mx.com.mentoringit.systembank.dto.TipoMovimiento;

public class MovimientoDAOImpl implements MovimientoDAO {
	
	private Connection conexion;
	
	public boolean registrarMovimiento(Movimiento movimiento) throws SQLException {
		boolean result = false;
		PreparedStatement insertMovStmt, actualizaCtaStmt;
		TipoMovimiento tipoMovimiento;
		tipoMovimiento = TipoMovimiento.valueOf(movimiento.getIdTipoMovimiento());
		
		String insertMov = "INSERT INTO movimiento "
				+ "(fecha, monto, idCuenta, idTipoMovimiento) "
				+ "values (?, ?, ?, ?)";
		
		String actualizaCuenta = "UPDATE cuenta "
				+ "SET saldo = (saldo + ?) "				
				+ "WHERE idCuenta = ?";		
		
		conexion = ConnectionFactory.getInstancia().getConnection();		
		
		try {
			conexion.setAutoCommit(false);
			
			insertMovStmt = conexion.prepareStatement(insertMov);				
			insertMovStmt.setDate(1, movimiento.getFecha());		
			insertMovStmt.setDouble(2, movimiento.getMonto());
			insertMovStmt.setInt(3, movimiento.getIdCuenta());
			insertMovStmt.setInt(4, movimiento.getIdTipoMovimiento());
			
			actualizaCtaStmt = conexion.prepareStatement(actualizaCuenta);
			if(tipoMovimiento == TipoMovimiento.CARGO) {
				movimiento.setMonto(movimiento.getMonto() * -1);
			}
			actualizaCtaStmt.setDouble(1, movimiento.getMonto());
			actualizaCtaStmt.setInt(2, movimiento.getIdCuenta());
			
			insertMovStmt.execute();
			actualizaCtaStmt.execute();
			
			conexion.commit();
			result = true;
			
			ConnectionFactory.getInstancia().closeConnection();
		} catch (SQLException se) {
			conexion.rollback();
		}		
		
		ConnectionFactory.getInstancia().closeConnection();
		return result;
	}

	public List<Movimiento> obtenMovimientosCuenta(int cuentaId) throws SQLException {
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM movimiento "				
				+ "WHERE idCuenta = ?";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(select);	
		sentencia.setInt(1, cuentaId);
		resultSet = sentencia.executeQuery();
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		while(resultSet.next()){
			Movimiento movimiento = new Movimiento();
			movimiento.setId(resultSet.getInt("idMovimiento"));
			movimiento.setFecha(resultSet.getDate("fecha"));
			movimiento.setIdCuenta(resultSet.getInt("idCuenta"));
			movimiento.setIdTipoMovimiento(resultSet.getInt("idTipoMovimiento"));
			movimiento.setTipoMovimiento(TipoMovimiento.valueOf(resultSet.getInt("idTipoMovimiento")));
			movimiento.setMonto(resultSet.getDouble("monto"));
			movimientos.add(movimiento);
		}
		ConnectionFactory.getInstancia().closeConnection();
		return movimientos;
	}

	public List<Movimiento> obtenMovimientosFecha(int cuentaId, Date fecha) throws SQLException {
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM movimiento "				
				+ "WHERE idCeuenta = ? "
				+ "AND fecha = ?";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(select);
		sentencia.setInt(1, cuentaId);
		sentencia.setDate(2, fecha);
		resultSet = sentencia.executeQuery();
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		while(resultSet.next()){
			Movimiento movimiento = new Movimiento();
			movimiento.setId(resultSet.getInt("idMovimiento"));
			movimiento.setFecha(resultSet.getDate("fecha"));
			movimiento.setIdCuenta(resultSet.getInt("idCuenta"));
			movimiento.setIdTipoMovimiento(resultSet.getInt("idTipoMovimiento"));
			movimiento.setTipoMovimiento(TipoMovimiento.valueOf(resultSet.getInt("idTipoMovimiento")));
			movimiento.setMonto(resultSet.getDouble("monto"));
			movimientos.add(movimiento);
		}
		ConnectionFactory.getInstancia().closeConnection();
		return movimientos;
	}

}
