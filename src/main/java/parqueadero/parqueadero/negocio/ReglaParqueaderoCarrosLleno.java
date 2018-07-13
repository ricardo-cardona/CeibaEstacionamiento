package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Carro;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;

public class ReglaParqueaderoCarrosLleno extends ReglaParqueaderoLleno {
	
	private static final String PARQUEADERO_CARROS_LLENO = "El vehículo no puede ingresar, ya que el parqueadero de carros está lleno.";
	
	public boolean verificarRegla(Carro vehiculo) {
		
		return false;
		
	}
	
	@Override
	public void mostrarMensaje() {
		throw new IngresoParqueaderoExcepcion(PARQUEADERO_CARROS_LLENO);
	}
	
}
