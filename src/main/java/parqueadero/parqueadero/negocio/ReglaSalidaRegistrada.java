package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.IngresoParqueadero;
import parqueadero.parqueadero.excepcion.SalidaParqueaderoExcepcion;

public class ReglaSalidaRegistrada implements ReglaSalida {
	
	public static final String SALIDA_YA_REGISTRADA = "Esta salida de parqueadero ya ha sido registrada.";
	
	public void verificarRegla(IngresoParqueadero ingreso) {
		
		if (ingreso.getFechaFin() != null) {
			throw new SalidaParqueaderoExcepcion(SALIDA_YA_REGISTRADA);
		}
		
	}
	
}
