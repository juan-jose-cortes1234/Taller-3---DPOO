package uniandes.dpoo.aerolinea.modelo;
import java.util.*;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.tarifas.*;
import uniandes.dpoo.aerolinea.modelo.tarifas.*;
public class Vuelo {
	private String fecha;
	private Avion avion;
	private Map<String, Tiquete> tiquetes;
	private Ruta ruta;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		this.fecha = fecha;
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
				if (this.ruta.getCodigoRuta().equals(otroVuelo.getRuta().getCodigoRuta())) {
					if (this.ruta.getOrigen().getCodigo().equals(otroVuelo.getRuta().getOrigen().getCodigo()) && this.ruta.getDestino().getCodigo().equals(otroVuelo.getRuta().getDestino().getCodigo())) {
						if (this.tiquetes.equals(otroVuelo.getTiquetes())) {
							result = true;
						}
						//REVISAR LA LISTA DE TIQUETES
						//result = true;
						
					}
				}
			}
		}
		return result;
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException{
		GeneradorTiquetes generadorT = new GeneradorTiquetes();
		if ((this.avion.getCapacidad() - this.tiquetes.size()) < cantidad) {
			throw new VueloSobrevendidoException(this);
		}
		int cantidadR = 0;
		for (int i = 0 ; i < cantidad; i++) {
			Tiquete nuevoTiquete = GeneradorTiquetes.generarTiquete(this, cliente, calculadora.calcularTarifa(this, cliente));
			GeneradorTiquetes.registrarTiquete(nuevoTiquete);
			//boolean result = GeneradorTiquetes.validarTiquete(nuevoTiquete.getCodigo());
			
			//boolean result = GeneradorTiquetes.validarTiquete(nuevoTiquete.getCodigo());
			//if (!result) {
				
			//}
			this.tiquetes.put(nuevoTiquete.getCodigo(), nuevoTiquete);
			
		}
		cantidadR = calculadora.calcularTarifa(this,cliente) * cantidad;
		return cantidadR;
	}
}
