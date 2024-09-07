package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
    // TODO completar
	private String codigoRuta;
	private Aeropuerto destino;
	private String horaLlegada;
	private String horaSalida;
	private Aeropuerto origen;
	
	public Ruta(String codigoRuta, Aeropuerto destino, String horaLlegada, String horaSalida, Aeropuerto origen) {
		this.codigoRuta = codigoRuta;
		this.destino = destino;
		this.horaLlegada = horaLlegada;
		this.horaSalida = horaSalida;
		this.origen = origen;
	}
	
	public String getCodigoRuta() {
		return this.codigoRuta;

	}
	public Aeropuerto getDestino() {
		return this.destino;
	}
	public String getHoraLlegada() {
		return this.horaLlegada;

	}
	public String getHoraSalida() {
		return this.horaSalida;
	}
	public Aeropuerto getOrigen() {
		return this.origen;
		
	}
	public int getDuracion() {
		int minutosL = getMinutos(this.horaLlegada);		
		int minutosS = getMinutos(this.horaSalida);
		
		int horaS = getHoras(this.horaSalida);
		int horaL = getHoras(this.horaLlegada);
		
		
		if (horaS > horaL) {
			int duracion = (24-horaS)*60 + horaL*60 + minutosL + minutosS;
			return duracion;
		}else {
			int duracion = (horaL-horaS)*60 + minutosL + minutosS;
			return duracion;
		}
	
	}
    /**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * 
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * 
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }

    
}
