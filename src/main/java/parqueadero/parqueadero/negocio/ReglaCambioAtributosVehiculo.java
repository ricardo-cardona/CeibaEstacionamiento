package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.parqueadero.servicio.VehiculoServicio;

public class ReglaCambioAtributosVehiculo implements ReglaIngreso {
	
	public static final String CAMBIO_CILINDRAJE = "El vehículo se había registrado anteriormente con un cilindraje diferente, por favor revisar.";
	public static final String CAMBIO_TIPO_VEHICULO = "El vehículo se había registrado anteriormente con un tipo diferente, por favor revisar.";
	
	private VehiculoServicio vehiculoServicio;
	
	public ReglaCambioAtributosVehiculo(VehiculoServicio vehiculoServicio) {
		this.vehiculoServicio = vehiculoServicio;
	}
	
	public void verificarRegla(Vehiculo vehiculo) {

		Vehiculo vehiculoRegistrado = VehiculoBuilder.convertirADominio(vehiculoServicio.consultarVehiculo(vehiculo.getPlaca()));
		
		if (vehiculoRegistrado == null) {
			return;
		}
		
		if (!vehiculo.getTipoVehiculo().getId().equals(vehiculoRegistrado.getTipoVehiculo().getId())) {
			mostrarMensaje(CAMBIO_TIPO_VEHICULO);
		}
		
		if (vehiculo.getCilindraje() != vehiculoRegistrado.getCilindraje()) {
			mostrarMensaje(CAMBIO_CILINDRAJE);
		}
		
	}
	
	public void mostrarMensaje(String mensaje) {
		throw new IngresoParqueaderoExcepcion(mensaje);
	}

}
