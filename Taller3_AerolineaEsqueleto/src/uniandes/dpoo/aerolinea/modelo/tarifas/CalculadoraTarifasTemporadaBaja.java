package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	protected final int COSTO_POR_KM_NATURAL = 600;
	protected final int COSTO_POR_KM_CORPORATIVO = 900;
	protected final double DESCUENTO_PEQ = 0.02;
	protected final double DESCUENTO_MEDIANAS = 0.1 ;
	protected final double DESCUENTO_GRANDES = 0.2;
	
	
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		Ruta ruta = vuelo.getRuta();
		
		int distancia = calcularDistanciaVuelo(ruta);
		if (cliente.getTipoCliente().equals("Corporativo")) {
			return distancia*this.COSTO_POR_KM_CORPORATIVO;
		}else {
			return distancia*this.COSTO_POR_KM_NATURAL;
		}
	}
	public  double calcularPorcentajeDescuento(Cliente cliente) {
		
	}
}
