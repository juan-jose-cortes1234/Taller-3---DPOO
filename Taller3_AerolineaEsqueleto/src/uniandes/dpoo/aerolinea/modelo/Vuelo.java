package uniandes.dpoo.aerolinea.modelo;

public class Vuelo {
	private String fecha;
	private Avion avion;
	private Map<String, Tiquete> tiquetes;
	private Ruta ruta;
	
	public Vuelo(Ruta ruta, String vuelo, Avion avion) {
		this.fecha = vuelo;
		this.avion = avion;
		this.ruta = ruta;
		
	}
	public Avion getAvion() {
		return this.avion;
	}
	public String getFecha() {
		return this.fecha;
	}
	public Ruta getRuta() {
		return this.ruta;
		
	}
	public Collection<Tiquete> getTiquetes(){
		return this.tiquetes;
	}
	@Override
	public equals(Object obj) {
		Vuelo otroVuelo = (Vuelo) obj;
		if (this.avion.getNombre().equals(otroVuelo.getAvion().getNombre())) {
			if (this.fecha.equals(otroVuelo.getFecha())){
				if (this.ruta.equals(otroVuelo.getRuta())) {
					
				}
			}
		}
	}
}
