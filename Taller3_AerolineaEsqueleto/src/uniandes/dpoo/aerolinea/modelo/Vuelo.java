package uniandes.dpoo.aerolinea.modelo;
import java.util.*;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;


public class Vuelo {
	private String fecha;
	private Avion avion;
	private Map<String, Tiquete> tiquetes;
	private Ruta ruta;
	
	public Vuelo(Ruta ruta, String vuelo, Avion avion) {
		this.fecha = vuelo;
		this.avion = avion;
		this.ruta = ruta;
		this.tiquetes = new HashMap<String, Tiquete>();
		
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
		return this.tiquetes.values();
	}
	@Override
	public boolean equals(Object obj) {
		Vuelo otroVuelo = (Vuelo) obj;
		boolean result = false;
		if (this.avion.getNombre().equals(otroVuelo.getAvion().getNombre())) {
			if (this.fecha.equals(otroVuelo.getFecha())){
				if (this.ruta.equals(otroVuelo.getRuta())) {
					if (this.ruta.getOrigen().equals(otroVuelo.getRuta().getOrigen()) && this.ruta.getDestino().equals(otroVuelo.getRuta().getDestino())) {
						result = true;
					}
				}
			}
		}
		return result;
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) {
		GeneradorTiquetes generadorT = new GeneradorTiquetes();
		for (int i = 0 ; i < cantidad; i++) {
			Tiquete nuevoTiquete = generadorT.generarTiquete(this, cliente, calculadora.cal);
			generadorT.validarTiquete(nuevoTiquete.getcodigo());
		}
	}
}
