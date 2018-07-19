package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.excepcion.SalidaParqueaderoExcepcion;

public class ReglaSalidaInvalida implements ReglaSalida {
	
	public static final String SALIDA_INVALIDA = "La salida que se intenta realizar no corresponde a ninguna registrada anteriormente, por favor revisar.";
	
	public void verificarRegla(IngresoParqueadero ingreso) {
		
		if (ingreso == null) {
			throw new SalidaParqueaderoExcepcion(SALIDA_INVALIDA);
		}
		
	}
	
}
