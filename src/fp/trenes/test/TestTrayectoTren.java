package fp.trenes.test;

import java.time.LocalTime;

import fp.trenes.TipoTren;
import fp.trenes.TrayectoTren;
import fp.trenes.TrayectoTrenImpl;
//import fp.trenes.TrayectoTrenImpl2;

public class TestTrayectoTren {

	public static void main(String[] args) {
		
		// Propiedades Clase TrayectoTremImpl son:
		//   String codigoTren, 
		//   String nombreTrayecto, 
		//   TipoTren tipoTren, 
		//   String origen, 
		//   String destino,
		//   LocalTime horaSalida, 
		//   LocalTime horaLlegada) 
		
		TrayectoTren tt = new TrayectoTrenImpl("02471", 
											   "Sevilla-Madrid", 
											   TipoTren.AV_CITY, 
											   "SEVILLA-SANTA JUSTA", 
											   "MADRID-PUERTA DE ATOCHA", 
											   LocalTime.of(7, 0), 
											   LocalTime.of(10, 2));
		System.out.println("Trayecto del Tren\n");
		System.out.println(tt);
		System.out.println("Duración: " + 
		tt.getDuracionTrayecto().toMinutes()/60 + " h. y "
				 + tt.getDuracionTrayecto().toMinutes()%60 + " minutos");
		
		//TrayectoTren tt = new TrayectoTrenImpl2("02471", "Sevilla-Madrid", TipoTren.AV_CITY, 
		//		"SEVILLA-SANTA JUSTA", "MADRID-PUERTA DE ATOCHA", 
		//		LocalTime.of(7, 0), LocalTime.of(10, 2));
		//System.out.println(tt);
		
		tt.anadirEstacionIntermedia(1, 
									"PUERTOLLANO", 
									LocalTime.of(8,40), 
									LocalTime.of(8,41));
		System.out.println(tt);
		
		tt.anadirEstacionIntermedia(1, 
									"CORDOBA", 
									LocalTime.of(7,45), 
									LocalTime.of(7,50));
		System.out.println(tt);

		tt.anadirEstacionIntermedia(2, 
									"VILLANUEVA DE CORDOBA-LOS PEDROCHES", 
									LocalTime.of(8,13), 
									LocalTime.of(8,14));
		System.out.println(tt);

		tt.anadirEstacionIntermedia(4, 
									"CIUDAD REAL", 
									LocalTime.of(8,57), 
									LocalTime.of(8,58));
		System.out.println(tt);
		
		// Prueba para verificar que sale excepción controlada 
		// Se intenta borrar una estación que no está dada de alta
		tt.eliminarEstacionIntermedia("TOLEDO");
		System.out.println(tt);
	}
}
