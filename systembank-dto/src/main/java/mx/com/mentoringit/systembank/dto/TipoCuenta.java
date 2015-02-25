package mx.com.mentoringit.systembank.dto;

public enum TipoCuenta {

	DEBITO(1, "DEBITO", "Cuenta de Debito"),
	
	CREDITO(2, "CREDITO", "Cuenta de Credito"),
	
	FONDOS(3, "FONDOS", "Cuenta de Fondos");
	
	private TipoCuenta(int id, String clave, String descripcion){
		this.id = id;
		this.clave = clave;
		this.descripcion = descripcion;
	}
	
	public static TipoCuenta valueOf(int id) {
		for(TipoCuenta tipoCuenta : values()){
			if(tipoCuenta.getId() == id) {
				return tipoCuenta;
			}
		}
		return null;
	}
	
	private int id;
	
	private String clave;
	
	private String descripcion;

	public int getId() {
		return id;
	}

	public String getClave() {
		return clave;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	
}
