package uniandes.dpoo.aerolinea.persistencia;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public interface IPersistenciaAerolinea {
	void cargarAerolinea(String archivo,Aerolinea aerolinea)  throws java.io.IOException, InformacionInconsistenteException;
	void salvarAerolinea(String archivo, Aerolinea aerolinea) throws java.io.IOException;
	
}
