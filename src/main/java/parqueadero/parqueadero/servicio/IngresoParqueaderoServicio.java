package parqueadero.parqueadero.servicio;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero.parqueadero.dominio.Parqueadero;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;

@Service
public class IngresoParqueaderoServicio {
	
	@Autowired
	private IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio;
	
	@Autowired
	private VehiculoServicio vehiculoServicio;
	
	public IngresoParqueaderoEntity registrarIngresoVehiculo(VehiculoEntity vehiculo) {
		
		IngresoParqueaderoEntity ingreso = null;
		
		Calendar fechaActual = Calendar.getInstance();
		
		verificarReglas(vehiculo);
		
		vehiculo = vehiculoServicio.registrarVehiculo(vehiculo);
		
		ingreso = new IngresoParqueaderoEntity();
		ingreso.setVehiculo(vehiculo);
		ingreso.setFechaInicio(fechaActual);
		ingreso = ingresoParqueaderoRepositorio.save(ingreso);
		
		return ingreso;
		
	}
	
	public List<IngresoParqueaderoEntity> consultarIngresos() {
		
		return ingresoParqueaderoRepositorio.findAll();
		
	}
	
	public IngresoParqueaderoEntity consultarIngreso(Long ingreso) {
		
		Optional<IngresoParqueaderoEntity> ingresoParqueadero = ingresoParqueaderoRepositorio.findById(ingreso);
		
		IngresoParqueaderoEntity ingresoRegistrado = null;
		
		if (ingresoParqueadero.isPresent()) {
			ingresoRegistrado = ingresoParqueadero.get();
		}
		
		return ingresoRegistrado;
		
	}
	
	public IngresoParqueaderoEntity registrarSalidaParqueadero(IngresoParqueaderoEntity ingresoParqueadero) {
		
		ingresoParqueadero.setFechaFin(Calendar.getInstance());
		ingresoParqueadero.setValor(9999); //aquí se debe usar el método encargado de calcular el valor a pagar
		ingresoParqueadero = ingresoParqueaderoRepositorio.save(ingresoParqueadero);
		
		return ingresoParqueadero;
		
	}
	
	private boolean parqueaderoEstaLleno(String tipoVehiculo) {
		
		return (ingresoParqueaderoRepositorio.cantidadTipoVehiculoEnParqueadero(tipoVehiculo) 
				>= cantidadMaximaTipoVehiculo(tipoVehiculo));
		
	}
	
	private int cantidadMaximaTipoVehiculo(String tipoVehiculo) {
		
		if (tipoVehiculo.equalsIgnoreCase("carro")) {
			return Parqueadero.CANTIDAD_MAXIMA_CARROS;
		}
		
		if (tipoVehiculo.equalsIgnoreCase("moto")) {
			return Parqueadero.CANTIDAD_MAXIMA_MOTOS;
		}
		
		return 0;
		
	}
	
	private boolean esDiaHabilParaPlaca(String placa, Calendar fecha) {
		
		if (placa.toUpperCase().startsWith("A")) {
			int diaSemana = fecha.get(Calendar.DAY_OF_WEEK);
			return (diaSemana == Calendar.SUNDAY || diaSemana == Calendar.MONDAY);
		}
		
		return true;
		
	}
	
	private boolean vehiculoSeEncuentraEnParqueadero(String placa) {
		
		VehiculoEntity vehiculo = vehiculoServicio.vehiculoEstaEnParqueadero(placa);
		
		return (vehiculo != null);
		
	}
	
	private boolean cambioCilindraje(VehiculoEntity vehiculo, int cilindraje) {
		
		return (vehiculo.getCilindraje() != cilindraje);
		
	}
	
	private boolean cambioTipoVehiculo(VehiculoEntity vehiculo, String tipoVehiculo) {
		
		return (!vehiculo.getTipoVehiculo().equalsIgnoreCase(tipoVehiculo));
		
	}
	
	private boolean cilindrajeValidoSegunTipoVehiculo(VehiculoEntity vehiculo) {
		
		return ((vehiculo.getTipoVehiculo().equalsIgnoreCase("moto") && vehiculo.getCilindraje() > 0)
				|| (vehiculo.getTipoVehiculo().equalsIgnoreCase("carro") && vehiculo.getCilindraje() == 0));
		
	}
	
	public void verificarReglas(VehiculoEntity vehiculo) {
		
		/*List<ReglaNegocio> reglasNegocio = new ArrayList<>();
		
		reglasNegocio.add(new ReglaDiaHabilPlaca());
		reglasNegocio.add(new ReglaParqueaderoLleno());
		
		for (ReglaNegocio regla : reglasNegocio) {
			if (!regla.verificarRegla(VehiculoBuilder.convertirADominio(vehiculo))) {
				regla.mostrarMensaje();
			}
		}*/
		
		Calendar fechaActual = Calendar.getInstance();
		
		if (!cilindrajeValidoSegunTipoVehiculo(vehiculo)){
			throw new IngresoParqueaderoExcepcion("Las motos deben tener cilindraje, los carros no.");
		}
		
		if (!esDiaHabilParaPlaca(vehiculo.getPlaca(), fechaActual)) {
			throw new IngresoParqueaderoExcepcion("El vehículo no puede ingresar al parqueadero, debido a que no es un día hábil.");
		}
		
		VehiculoEntity vehiculoRegistrado = vehiculoServicio.consultarVehiculo(vehiculo.getPlaca());
		
		if (vehiculoRegistrado != null
				&& cambioTipoVehiculo(vehiculo, vehiculoRegistrado.getTipoVehiculo())) {
			throw new IngresoParqueaderoExcepcion("El vehículo se había registrado anteriormente con otro tipo, por favor revisar.");
		}
		
		if (vehiculoRegistrado != null
				&& cambioCilindraje(vehiculo, vehiculoRegistrado.getCilindraje())) {
			throw new IngresoParqueaderoExcepcion("El vehículo se había registrado anteriormente con otro cilindraje, por favor revisar.");
		}
		
		if (vehiculoSeEncuentraEnParqueadero(vehiculo.getPlaca())) {
			throw new IngresoParqueaderoExcepcion("El vehículo ya se encuentra en el parqueadero, no puede volver a ingresar.");
		}
		
		if (parqueaderoEstaLleno(vehiculo.getTipoVehiculo())) {
			throw new IngresoParqueaderoExcepcion("El vehículo no puede ingresar, debido a que el parqueadero se encuentra completamente ocupado.");
		}
		
	}
	
}
