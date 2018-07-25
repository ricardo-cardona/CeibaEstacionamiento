package parqueadero.parqueadero.negocio;

import java.util.Calendar;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;

public class ReglaPlaca implements ReglaIngreso {
	
	public static final String SIN_PLACA = "Se debe indicar la placa del vehículo.";
	public static final String DIA_NO_HABIL_PLACA = "El vehículo no puede ingresar al parqueadero, debido a que no es un día hábil.";
	
	private Calendar fecha;
	
	public ReglaPlaca(Calendar fecha) {
		this.fecha = fecha;
	}
	
	public void verificarRegla(Vehiculo vehiculo) {
		
		if (vehiculo.getPlaca() == null || vehiculo.getPlaca().trim().isEmpty()) {
			mostrarMensaje(SIN_PLACA);
		}
		
		if (vehiculo.getPlaca().toUpperCase().startsWith("A")) {
			int diaSemana = fecha.get(Calendar.DAY_OF_WEEK);
			if (diaSemana != Calendar.SUNDAY && diaSemana != Calendar.MONDAY) {
				mostrarMensaje(DIA_NO_HABIL_PLACA);
			}
		}
		
	}
	
	public void mostrarMensaje(String mensaje) {
		throw new IngresoParqueaderoExcepcion(mensaje);
	}
	
}
