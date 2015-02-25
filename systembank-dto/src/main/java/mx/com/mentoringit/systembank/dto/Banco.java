package mx.com.mentoringit.systembank.dto;

public enum Banco {

	BANAMEX(1, "BANAMEX", "Banco Nacional de Mexico"),
	
	BANORTE(2, "BANORTE", "Banco del Norte"),
	
	SANTANDER(3, "SANTANDER", "Banco Santander - Serfin"),
	
	BANREGIO(4, "BANREGIO", "Banco Regional de Mexico"),
	
	BANCOMER(5, "BANCOMER", "BBVA Bancomer"),
	
	AZTECA(6, "AZTECA", "Banco Azteca");
	
	private Banco(int id, String clave, String descripcion){
		this.id = id;
		this.clave = clave;
		this.descripcion = descripcion;
	}
	
	public static Banco valueOf(int id) {
		for(Banco banco : values()){
			if(banco.getId() == id) {
				return banco;
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
