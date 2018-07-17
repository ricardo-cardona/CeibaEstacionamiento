package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;

public class ReglaCilindraje implements ReglaNegocio {
	
	private static final String CILINDRAJE_INVALIDO = "El cilindraje es inválido de acuerdo al tipo de vehículo.";
	
	public boolean verificarRegla(Vehiculo vehiculo) {

		return (vehiculo.getTipoVehiculo().getTieneCilindraje() ? vehiculo.getCilindraje() > 0 : vehiculo.getCilindraje() == 0);
		
	}
	
	public void mostrarMensaje() {
		throw new IngresoParqueaderoExcepcion(CILINDRAJE_INVALIDO);
	}
	
}
