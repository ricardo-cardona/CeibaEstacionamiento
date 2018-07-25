package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;

public class ReglaParqueaderoLleno implements ReglaIngreso {
	
	public static final String PARQUEADERO_LLENO = "El vehículo no puede ingresar, ya que el parqueadero está lleno.";
	
	private IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio;
	
	public ReglaParqueaderoLleno(IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio) {
		this.ingresoParqueaderoRepositorio = ingresoParqueaderoRepositorio;
	}
	
	public void verificarRegla(Vehiculo vehiculo) {
		
		if (ingresoParqueaderoRepositorio.cantidadTipoVehiculoEnParqueadero(vehiculo.getTipoVehiculo().getId())
				>= vehiculo.getTipoVehiculo().getCapacidadMaxima()) {
			mostrarMensaje(PARQUEADERO_LLENO);
		}
		
	}
	
	public void mostrarMensaje(String mensaje) {
		throw new IngresoParqueaderoExcepcion(mensaje);
	}
	
}
