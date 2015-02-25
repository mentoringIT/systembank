package mx.com.mentoringit.systembank.dto;

public enum TipoMovimiento {

	CARGO(1, "CARGO", "Cargo a la Cuenta"),
	
	ABONO(2, "ABONO", "Abono a la Cuenta");
	
	private TipoMovimiento(int id, String clave, String descripcion){
		this.id = id;
		this.clave = clave;
		this.descripcion = descripcion;
	}
	
	public static TipoMovimiento valueOf(int id) {
		for(TipoMovimiento tipoMovimiento : values()){
			if(tipoMovimiento.getId() == id) {
				return tipoMovimiento;
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
