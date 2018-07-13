package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;

public class ReglaParqueaderoLleno implements ReglaNegocio {
	
	private static final String PARQUEADERO_LLENO = "El veh�culo no puede ingresar, ya que el parqueadero est� lleno.";
	
	public boolean verificarRegla(Vehiculo vehiculo) {
		return false;
	}
	
	public void mostrarMensaje() {
		throw new IngresoParqueaderoExcepcion(PARQUEADERO_LLENO);
	}
	
}
