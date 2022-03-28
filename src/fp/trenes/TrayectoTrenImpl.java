package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Validators;

public class TrayectoTrenImpl implements TrayectoTren {

	private String codigoTren;
	private String nombreTrayecto;
	private TipoTren tipoTren;
	
	// La clase LinkedList implementa la lógica para trabajar con listas genéricas
	// Permite insertar y extraer elementos de cualquier parte de la lista
	private List<String> estaciones;
	private List<LocalTime> horasSalida;
	private List<LocalTime> horasLlegada;

	public TrayectoTrenImpl(String codigoTren, 
						    String nombreTrayecto, 
						    TipoTren tipoTren, 
						    String origen, 
						    String destino,
						    LocalTime horaSalida, 
						    LocalTime horaLlegada) {
		//super();
		
		// Están informadas las fechas
		Checkers.checkNoNull(horaSalida, horaLlegada);
		// Otras restricciones del ejercicio
		Checkers.check("El código del tren no está formado por 5 dígitos", 
				esCodigoTrenOK(codigoTren));
		Checkers.check("La hora salida no es anterior a la de llegada", 
				horaSalida.isBefore(horaLlegada));

		this.codigoTren = codigoTren;
		this.nombreTrayecto = nombreTrayecto;
		this.tipoTren = tipoTren;
		
		this.estaciones = new LinkedList<String>();
		estaciones.add(origen);
		estaciones.add(destino);
		
		this.horasSalida = new LinkedList<LocalTime>();
		horasSalida.add(horaSalida);
		horasSalida.add(null);
		
		this.horasLlegada = new LinkedList<LocalTime>();
		horasLlegada.add(null);
		horasLlegada.add(horaLlegada);
	}

	public String getCodigoTren() {
		return this.codigoTren;
	}
	public String getNombreTrayecto() {
		return this.nombreTrayecto;
	}
	public TipoTren getTipoTren() {
		return this.tipoTren;
	}
	public List<String> getEstaciones() {
		return new ArrayList<String>(this.estaciones);
	}
	public List<LocalTime> getHorasLlegada() {
		return new ArrayList<LocalTime>(this.horasLlegada);
	}
	public List<LocalTime> getHorasSalida() {
		return new ArrayList<LocalTime>(this.horasSalida);
	}
	// Propiedades derivadas
	public LocalTime getHoraSalida() {
		return this.horasSalida.get(0);
	}
	public LocalTime getHoraLlegada() {
		//int ultimo = this.horasLlegada.size() - 1;
		return this.horasLlegada.get(this.horasLlegada.size() - 1);
	}

	public Duration getDuracionTrayecto() {
		return Duration.between(getHoraSalida(), getHoraLlegada());
	}

	// Métodos (OTRAS OPERACIONES)
	public LocalTime getHoraSalida(String estacion) {
		
		LocalTime res = null;
		int pos = estaciones.indexOf(estacion);
		
		if (pos >= 0) {
			res = horasSalida.get(pos);
		}
		return res;
	}

	public LocalTime getHoraLlegada(String estacion) {
		
		LocalTime res = null;
		int pos = estaciones.indexOf(estacion);
		
		if (pos >= 0) {
			res = horasLlegada.get(pos);
		}
		return res;
	}

	public void anadirEstacionIntermedia(int posicion, String nombre, LocalTime horaLlegada, LocalTime horaSalida) {
		
		Checkers.check("La posicion intermedia no está entre 1 y n", 
				posicion > 0 && posicion <= estaciones.size() - 1);
		Checkers.check("La hora salida no es posterior a la de llegada", 
				horaSalida.isAfter(horaLlegada));
		Checkers.check("Hora llegada no es posterior a hora salida estación anterior", 
				horaLlegada.isAfter(horasSalida.get(posicion-1)));
		Checkers.check("Hora salida no es anterior a hora llegada estación posterior", 
				horaSalida.isBefore(horasLlegada.get(posicion)));
		
		estaciones.add(posicion, nombre);
		horasSalida.add(posicion, horaSalida);
		horasLlegada.add(posicion, horaLlegada);
	}

	public void eliminarEstacionIntermedia(String estacion) {
		
		int index = estaciones.indexOf(estacion);
		
		Checkers.check("La estación no está en el trayecto", index != -1);
		Checkers.check("La estación a eliminar no puede ser la primera", index != 0);
		Checkers.check("La estación a eliminar no puede ser la última", index != estaciones.size() - 1);
		
		estaciones.remove(index);
		horasLlegada.remove(index);
		horasSalida.remove(index);
	}
	
	// *************************************************
	// ** Método para validar la logitud 
	// ** del código y solo debe contener digitos.  
	// *************************************************
	private Boolean esCodigoTrenOK(String codigo) {
		return codigo.length() == 5 && 
				Validators.sonDigitos(codigo);
	}
	
	// *************************************************
	// ** Método para formatear 
	// ** la hora como HH:MM
	// *************************************************
	private String formateaHora(LocalTime hora) {
		
		String res = "     ";
		
		if (hora != null) {
			res = hora.format(DateTimeFormatter.ofPattern("hh:mm"));
		}
		
		return res;
	}

	// ***************************
	// ** Método equals
	// ***************************
	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof TrayectoTren) {
			TrayectoTren tt = (TrayectoTren) obj;
			res = getCodigoTren().equals(tt.getCodigoTren()) && 
					getNombreTrayecto().equals(tt.getNombreTrayecto())
					&& getHoraSalida().equals(tt.getHoraSalida());

		}
		return res;
	}

	// ***************************
	// ** Método hascCode
	// ***************************
	public int hashCode() {
		return getCodigoTren().hashCode() + 31 * getNombreTrayecto().hashCode() + 31*31*getHoraSalida().hashCode();
	}

	public int compareTo(TrayectoTren tt) {
		int res = getNombreTrayecto().compareTo(tt.getNombreTrayecto());
		if (res == 0) {
			res = getHoraSalida().compareTo(tt.getHoraSalida());
			if (res == 0) {
				res = getCodigoTren().compareTo(tt.getCodigoTren());
			}
		}
		return res;
	}
    
	// ***************************
	// ** Método toString
	// ***************************
	public String toString() {
		String res = getNombreTrayecto() + " - " + getTipoTren() + 
				" (" + getCodigoTren() + ")\n";
		for (int i = 0; i < estaciones.size(); i++) {
			res += "\t" + estaciones.get(i) + "\t" 
					+ formateaHora(horasLlegada.get(i)) + "\t"
					+ formateaHora(horasSalida.get(i)) + "\n";
		}

		return res;
	}
}