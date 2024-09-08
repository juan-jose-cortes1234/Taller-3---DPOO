package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente{
	public static final String NATURAL = "Natural";
	private String nombre;
	//private int ID;
	public ClienteNatural(String nombre) {
		this.nombre = nombre;
		
		
		
		
	}
	public String getIdentificador() {
		return String.valueOf(this.nombre.hashCode());
		//return null;
	}
	public String getTipoCliente() {
		return this.NATURAL;
	}

}
