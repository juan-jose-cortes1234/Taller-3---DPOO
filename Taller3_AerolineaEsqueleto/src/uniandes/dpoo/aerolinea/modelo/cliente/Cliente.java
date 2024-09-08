package uniandes.dpoo.aerolinea.modelo.cliente;
import java.util.*;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	private List<Tiquete> tiquetesSinUsar;
	private List<Tiquete> tiquetesUsados;
	
	public Cliente() {
		this.tiquetesSinUsar = new ArrayList<Tiquete>();
		this.tiquetesUsados = new ArrayList<Tiquete>();
	}
	public void agregarTiquete(Tiquete tiquete) {
		this.tiquetesSinUsar.add(tiquete);
	}
	public int calcularValorTotalTiquetes() {
		int valorTotal = 0;
		for (Iterator<Tiquete> iterator = this.tiquetesSinUsar.iterator();iterator.hasNext();) {
			valorTotal = valorTotal + iterator.next().getTarifa();
		}
		return valorTotal;
	}
	public abstract String getIdentificador();
	public abstract String getTipoCliente();
	
	public void usarTiquetes(Vuelo vuelo) {
		for (Iterator<Tiquete> iterator = this.tiquetesSinUsar.iterator(); iterator.hasNext();) {
			Tiquete tiquete= iterator.next();
			if (tiquete.getVuelo().equals(vuelo)) {
				tiquete.marcarComoUsado();
			}
		}
	}
	
}
