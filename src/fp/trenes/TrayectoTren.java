package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

// Interface TrayectoTren (colecci�n de m�todos abstractos). 
// Solo se especifica qu� debe hacer, pero no se implementa. 
// Las clases que implementen esta interface, ser�n la que describa la l�gica del comportamiento de los m�todos
public interface TrayectoTren extends Comparable<TrayectoTren> {

	// Getters de Propiedades b�sicas
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
	
	// M�todos para obtener hora salida/llegada de una estaci�n dada
	LocalTime getHoraSalida(String estacion);
	LocalTime getHoraLlegada(String estacion);
	
	// M�todos para a�adir/eliminar una estaci�n intermedia
	void anadirEstacionIntermedia(int posicion, String estacion, 
			LocalTime horaLlegada, LocalTime horaSalida);
	void eliminarEstacionIntermedia(String estacion);
}