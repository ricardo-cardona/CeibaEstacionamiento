package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Moto;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;

public class ReglaParqueaderoMotosLleno extends ReglaParqueaderoLleno {
	
	private static final String PARQUEADERO_MOTOS_LLENO = "El veh�culo no puede ingresar, ya que el parqueadero de motos est� lleno.";
	
	public boolean verificarRegla(Moto vehiculo) {
		
		return false;
		
	}
	
	@Override
	public void mostrarMensaje() {
		throw new IngresoParqueaderoExcepcion(PARQUEADERO_MOTOS_LLENO);
	}
	
}
