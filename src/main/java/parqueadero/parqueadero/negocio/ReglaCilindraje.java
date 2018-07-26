package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;

public class ReglaCilindraje implements ReglaIngreso {
	
	public static final String CILINDRAJE_INVALIDO = "El cilindraje es inv�lido de acuerdo al tipo de veh�culo.";
	
	public void verificarRegla(Vehiculo vehiculo) {

		if ((vehiculo.getTipoVehiculo().getTieneCilindraje() && vehiculo.getCilindraje() <= 0)
			|| (!vehiculo.getTipoVehiculo().getTieneCilindraje() && vehiculo.getCilindraje() > 0)) {
			mostrarMensaje(CILINDRAJE_INVALIDO);
		}
		
	}
	
	public void mostrarMensaje(String mensaje) {
		throw new IngresoParqueaderoExcepcion(mensaje);
	}
	
}
