package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;

public class ReglaParqueaderoLleno implements ReglaNegocio {
	
	private static final String PARQUEADERO_LLENO = "El vehículo no puede ingresar, ya que el parqueadero está lleno.";
	private IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio;
	
	public ReglaParqueaderoLleno(IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio) {
		this.ingresoParqueaderoRepositorio = ingresoParqueaderoRepositorio;
	}
	
	public boolean verificarRegla(Vehiculo vehiculo) {
		
		return (ingresoParqueaderoRepositorio.cantidadTipoVehiculoEnParqueadero(vehiculo.getTipoVehiculo().getId())
				< vehiculo.getTipoVehiculo().getCapacidadMaxima());
		
	}
	
	public void mostrarMensaje() {
		throw new IngresoParqueaderoExcepcion(PARQUEADERO_LLENO);
	}
	
}
