package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
	public static final double IMPUESTO = 0.28;
	public CalculadoraTarifas() {};
	public abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente) ;
	
	protected int calcularDistanciaVuelo(Ruta ruta) {
		return Aeropuerto.calcularDistancia(ruta.getOrigen(), ruta.getDestino());
	}
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		int costoBase = calcularCostoBase(vuelo, cliente);
		double descuento = calcularPorcentajeDescuento(cliente);
		int newCostoBase = (int) (costoBase -  costoBase * descuento);
		int tarifa = newCostoBase + calcularValorImpuestos(newCostoBase);
		return tarifa;
		
	}
	protected int calcularValorImpuestos(int costoBase) {
		int valorImpuestos =  (int) (costoBase*IMPUESTO);
		return valorImpuestos;
	}
}
