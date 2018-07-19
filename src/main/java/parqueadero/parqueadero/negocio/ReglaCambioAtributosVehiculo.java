package parqueadero.parqueadero.negocio;

import parqueadero.parqueadero.dominio.Vehiculo;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.parqueadero.servicio.VehiculoServicio;

public class ReglaCambioAtributosVehiculo implements ReglaNegocio {
	
	private static final String CAMBIO_CILINDRAJE = "El vehículo se había registrado anteriormente con un cilindraje diferente, por favor revisar.";
	private static final String CAMBIO_TIPO_VEHICULO = "El vehículo se había registrado anteriormente con un tipo diferente, por favor revisar.";
	
	private String mensaje;
	private VehiculoServicio vehiculoServicio;
	
	public ReglaCambioAtributosVehiculo(VehiculoServicio vehiculoServicio) {
		this.vehiculoServicio = vehiculoServicio;
	}
	
	public boolean verificarRegla(Vehiculo vehiculo) {

		Vehiculo vehiculoRegistrado = VehiculoBuilder.convertirADominio(vehiculoServicio.consultarVehiculo(vehiculo.getPlaca()));
		
		if (vehiculoRegistrado == null) {
			return true;
		}
		
		if (vehiculo.getTipoVehiculo().getId() != vehiculoRegistrado.getTipoVehiculo().getId()) {
			setMensaje(CAMBIO_TIPO_VEHICULO);
			return false;
		}
		
		if (vehiculo.getCilindraje() != vehiculoRegistrado.getCilindraje()) {
			setMensaje(CAMBIO_CILINDRAJE);
			return false;
		}
		
		return true;
		
	}
	
	public void mostrarMensaje() {
		throw new IngresoParqueaderoExcepcion(getMensaje());
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
