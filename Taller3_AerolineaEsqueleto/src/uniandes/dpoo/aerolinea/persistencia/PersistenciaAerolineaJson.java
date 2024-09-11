package uniandes.dpoo.aerolinea.persistencia;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteTiqueteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.*;

public class PersistenciaAerolineaJson extends Object implements IPersistenciaAerolinea{
	
	
    public void cargarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException, InformacionInconsistenteException
    {
        String content = new String(Files.readAllBytes(Paths.get(archivo)));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray jsonArray = jsonObject.getJSONArray("vuelos");
        for (int i = 0; i< jsonArray.length(); i++) {
        	try {
        		//Vuelo newVuelo;
        		JSONObject jsonObj = jsonArray.getJSONObject(i);
        		
        		String fecha = jsonObj.getString("fecha");
        		
        		JSONObject avion = jsonObj.getJSONObject("avion");
        		int capacidad = avion.getInt("capacidad");
        		String nombreAvion = avion.getString("nombre");
        		Avion newAvion = new Avion(nombreAvion, capacidad);
        		
        		JSONObject ruta = jsonObj.getJSONObject("ruta");
        		String horaSalida = ruta.getString("horaSalida");
        		String horaLlegada = ruta.getString("horaLlegada");
        		String codigoRuta = ruta.getString("codigoRuta");
        		JSONObject origen = jsonObj.getJSONObject("origen");
        		JSONObject destino = jsonObj.getJSONObject("destino");
        		
        		//aparado origen
        		String codigoO = origen.getString("codigo");
        		
        		//Set<String> codigosUtilizadosO = new HashSet<String>();
        		//JSONArray codigos = origen.getJSONArray("codigosUtilizados");
        		//for (Object codigoi : codigos) {
        		//	codigosUtilizados.add(codigoi.toString());
        		//}
        		
        		double latitudO = origen.getDouble("latitud");
        		double longitudO = origen.getDouble("longitud");
        		String nombreOrigen = origen.getString("nombre");
        		String nombreCiudadOrigen = origen.getString("nombreCiudad");
        		Aeropuerto aeropuertoOrigen = new Aeropuerto(nombreOrigen, codigoO, nombreCiudadOrigen, latitudO, longitudO);
        		
        		//apartado destino
        		String codigoD = destino.getString("codigo");
        		
        		//Set<String> codigosUtilizadosD = new HashSet<String>();
        		//JSONArray codigos = origen.getJSONArray("codigosUtilizados");
        		//for (Object codigoi : codigos) {
        		//	codigosUtilizados.add(codigoi.toString());
        		//}
        		
        		double latitudD = destino.getDouble("latitud");
        		double longitudD = destino.getDouble("longitud");
        		String nombreDestino = destino.getString("nombre");
        		String nombreCiudadDestino = destino.getString("nombreCiudad");
        		Aeropuerto aeropuertoDestino = new Aeropuerto(nombreDestino, codigoD, nombreCiudadDestino, latitudD, longitudD);
        		
        		Ruta newRuta = new Ruta(codigoRuta, aeropuertoDestino,horaLlegada, horaSalida, aeropuertoOrigen);
        		//newVuelo = new Vuelo(newRuta, fecha, newAvion);
        		aerolinea.agregarRuta(newRuta);
        		aerolinea.agregarAvion(newAvion);
        		aerolinea.programarVuelo(fecha, codigoRuta, nombreAvion);
        		
        		
        	}catch (IOException e) {
        		throw e;
        	}
        	catch (Exception ex) {
        		throw new InformacionInconsistenteException("Hubo un error programando el vuelo");
        	}
        }
    }

    public void salvarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException
    {
        JSONArray vuelosArray = new JSONArray();
        for (Vuelo vueloJava : aerolinea.getVuelos()) {
        	JSONObject vueloJson = new JSONObject();
        	vueloJson.put("fecha", vueloJava.getFecha());
        	
        	JSONObject avion = new JSONObject();
        	avion.put("nombre", vueloJava.getAvion().getNombre());
        	avion.put("capacidad", vueloJava.getAvion().getCapacidad());
        	vueloJson.put("avion", avion);
        	
        	JSONObject origen = new JSONObject();
        	origen.put("latitud", vueloJava.getRuta().getOrigen().getLatitud());
        	origen.put("longitud", vueloJava.getRuta().getOrigen().getLongitud());
        	origen.put("nombre", vueloJava.getRuta().getOrigen().getNombre());
        	origen.put("nombreCiudad", vueloJava.getRuta().getOrigen().getNombreCiudad());
        	origen.put("codigo", vueloJava.getRuta().getOrigen().getCodigo());
        	
        	JSONObject destino = new JSONObject();
        	destino.put("latitud", vueloJava.getRuta().getDestino().getLatitud());
        	destino.put("longitud", vueloJava.getRuta().getDestino().getLongitud());
        	destino.put("nombre", vueloJava.getRuta().getDestino().getNombre());
        	destino.put("nombreCiudad", vueloJava.getRuta().getDestino().getNombreCiudad());
        	destino.put("codigo", vueloJava.getRuta().getDestino().getCodigo());
        	
        	JSONObject ruta = new JSONObject();
        	ruta.put("horaSalida", vueloJava.getRuta().getHoraSalida());
        	ruta.put("horaLlegada", vueloJava.getRuta().getHoraLlegada());
        	ruta.put("codigoRuta", vueloJava.getRuta().getCodigoRuta());
        	ruta.put("origen", origen);
        	ruta.put("destino", destino);
        	
        	vueloJson.put("ruta", ruta);
        	vuelosArray.put(vueloJson);
        }
        
        try (FileWriter file = new FileWriter(archivo)) {
            file.write(vuelosArray.toString(4)); 
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
