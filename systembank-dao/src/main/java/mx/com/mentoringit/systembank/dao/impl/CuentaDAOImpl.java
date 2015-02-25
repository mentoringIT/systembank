package mx.com.mentoringit.systembank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.com.mentoringit.systembank.dao.ConnectionFactory;
import mx.com.mentoringit.systembank.dao.interfaces.CuentaDAO;
import mx.com.mentoringit.systembank.dto.Cuenta;
import mx.com.mentoringit.systembank.dto.TipoCuenta;

public class CuentaDAOImpl implements CuentaDAO {

	private Connection conexion;
	
	public boolean agregar(Cuenta cuenta) throws SQLException {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String insert = "INSERT INTO cuenta "
				+ "(numeroCuenta, idTipoCuenta, idCliente, saldo) "
				+ "values (?, ?, ?, ?)";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(insert);
		
		sentencia.setInt(1, cuenta.getNumeroCuenta());
		sentencia.setInt(2, cuenta.getIdTipoCuenta());
		sentencia.setInt(3, cuenta.getIdCliente());
		sentencia.setDouble(4, cuenta.getSaldo());
		
		if(!sentencia.execute()) {
			result = (sentencia.getUpdateCount() > 0);
		}
		
		ConnectionFactory.getInstancia().closeConnection();
		return result;
		
	}

	public boolean borrar(int cuentaId) throws SQLException {
		boolean result = false;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String delete = "DELETE FROM cuenta "
				+ "WHERE idCuenta = ?";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(delete);
		sentencia.setInt(1, cuentaId);
		if(!sentencia.execute()) {
			result = (sentencia.getUpdateCount() > 0);
		}
		ConnectionFactory.getInstancia().closeConnection();
		return result;
	}

	public Cuenta obtenerCuenta(int cuentaId) throws SQLException {
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM cuenta "				
				+ "WHERE idCuenta = ?";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(select);	
		sentencia.setInt(1, cuentaId);
		resultSet = sentencia.executeQuery();
		
		while(resultSet.next()){
			Cuenta cuenta = new Cuenta();
			cuenta.setId(resultSet.getInt("idCuenta"));
			cuenta.setNumeroCuenta(resultSet.getInt("numeroCuenta"));
			cuenta.setIdCliente(resultSet.getInt("idCliente"));
			cuenta.setIdTipoCuenta(resultSet.getInt("idTipoCuenta"));
			cuenta.setTipoCuenta(TipoCuenta.valueOf(resultSet.getInt("idTipoCuenta")));
			cuenta.setSaldo(resultSet.getInt("saldo"));
			return cuenta;
		}
		
		return null;
	}

	public List<Cuenta> obtenerCuentasCliente(int clienteId) throws SQLException {
		ResultSet resultSet;
		conexion = ConnectionFactory.getInstancia().getConnection();
		String select = "SELECT * FROM cuenta WHERE idCliente = ?";
		PreparedStatement sentencia;
		sentencia = conexion.prepareStatement(select);
		sentencia.setInt(1, clienteId);
		resultSet = sentencia.executeQuery();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		while(resultSet.next()){
			Cuenta cuenta = new Cuenta();
			cuenta.setId(resultSet.getInt("idCuenta"));
			cuenta.setNumeroCuenta(resultSet.getInt("numeroCuenta"));
			cuenta.setIdCliente(resultSet.getInt("idCliente"));
			cuenta.setIdTipoCuenta(resultSet.getInt("idTipoCuenta"));
			cuenta.setTipoCuenta(TipoCuenta.valueOf(resultSet.getInt("idTipoCuenta")));
			cuenta.setSaldo(resultSet.getInt("saldo"));
			cuentas.add(cuenta);
		}
		return cuentas;
	}

}
