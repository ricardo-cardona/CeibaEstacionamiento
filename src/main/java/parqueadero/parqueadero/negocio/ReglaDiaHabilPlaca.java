package parqueadero.parqueadero.negocio;

import java.util.Calendar;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;

public class ReglaDiaHabilPlaca implements ReglaNegocio {
	
	private static final String DIA_NO_HABIL_PLACA = "El veh�culo no puede ingresar al parqueadero, debido a que no es un d�a h�bil.";
	private Calendar fecha;
	
	public ReglaDiaHabilPlaca(Calendar fecha) {
		this.fecha = fecha;
	}
	
	public boolean verificarRegla(Vehiculo vehiculo) {
		
		if (vehiculo.getPlaca().toUpperCase().startsWith("A")) {
			int diaSemana = fecha.get(Calendar.DAY_OF_WEEK);
			return (diaSemana == Calendar.SUNDAY || diaSemana == Calendar.MONDAY);
		}
		
		return true;
		
	}
	
	public void mostrarMensaje() {
		throw new IngresoParqueaderoExcepcion(DIA_NO_HABIL_PLACA);
	}
	
}
