package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

// Interface TrayectoTren (colección de métodos abstractos). 
// Solo se especifica qué debe hacer, pero no se implementa. 
// Las clases que implementen esta interface, serán la que describa la lógica del comportamiento de los métodos
public interface TrayectoTren extends Comparable<TrayectoTren> {

	// Getters de Propiedades básicas
	String getCodigoTren();
	String getNombreTrayecto();
	TipoTren getTipoTren();
	List<String> getEstaciones();
	List<LocalTime> getHorasSalida();
	List<LocalTime> getHorasLlegada();
	
	// Getters de Propiedades derivadas
	Duration getDuracionTrayecto();
	LocalTime getHoraSalida();
	LocalTime getHoraLlegada();
	
	// Métodos para obtener hora salida/llegada de una estación dada
	LocalTime getHoraSalida(String estacion);
	LocalTime getHoraLlegada(String estacion);
	
	// Métodos para añadir/eliminar una estación intermedia
	void anadirEstacionIntermedia(int posicion, String estacion, 
			LocalTime horaLlegada, LocalTime horaSalida);
	void eliminarEstacionIntermedia(String estacion);
}