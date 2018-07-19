package parqueadero.parqueadero.servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero.parqueadero.negocio.ReglaCambioAtributosVehiculo;
import parqueadero.parqueadero.negocio.ReglaCilindraje;
import parqueadero.parqueadero.negocio.ReglaDiaHabilPlaca;
import parqueadero.parqueadero.negocio.ReglaNegocio;
import parqueadero.parqueadero.negocio.ReglaParqueaderoLleno;
import parqueadero.parqueadero.negocio.ReglaVehiculoEnParqueadero;
import parqueadero.parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;
import parqueadero.parqueadero.persistencia.repositorio.VehiculoEnParqueaderoRepositorio;

@Service
public class IngresoParqueaderoServicio {
	
	@Autowired
	private IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio;
	
	@Autowired
	private VehiculoEnParqueaderoRepositorio vehiculoEnParqueaderoRepositorio;
	
	@Autowired
	private VehiculoServicio vehiculoServicio;
	
	@Autowired
	private TipoVehiculoServicio tipoVehiculoServicio;
	
	public IngresoParqueaderoEntity registrarIngresoVehiculo(VehiculoEntity vehiculo) {
		
		vehiculo.setTipoVehiculo(tipoVehiculoServicio.consultarTipoVehiculo(vehiculo.getTipoVehiculo()));
		
		verificarReglas(vehiculo);
		
		vehiculo = vehiculoServicio.registrarVehiculo(vehiculo);
		
		return guardarIngreso(vehiculo);
		
	}
	
	private IngresoParqueaderoEntity guardarIngreso(VehiculoEntity vehiculo) {
		
		IngresoParqueaderoEntity ingreso = new IngresoParqueaderoEntity();
		
		ingreso.setVehiculo(vehiculo);
		ingreso.setFechaInicio(Calendar.getInstance());
		
		return ingresoParqueaderoRepositorio.save(ingreso);
		
	}
	
	public IngresoParqueaderoEntity consultarIngreso(Long ingreso) {
		
		Optional<IngresoParqueaderoEntity> ingresoParqueadero = ingresoParqueaderoRepositorio.findById(ingreso);
		
		IngresoParqueaderoEntity ingresoRegistrado = null;
		
		if (ingresoParqueadero.isPresent()) {
			ingresoRegistrado = ingresoParqueadero.get();
		}
		
		return ingresoRegistrado;
		
	}
	
	public List<VehiculoEnParqueaderoEntity> consultarVehiculosEnParqueadero() {
		
		return vehiculoEnParqueaderoRepositorio.vehiculosEnParqueadero();
		
	}
	
	public void verificarReglas(VehiculoEntity vehiculo) {
		
		List<ReglaNegocio> reglas = new ArrayList<>();
		
		reglas.add(new ReglaCilindraje());
		reglas.add(new ReglaDiaHabilPlaca(Calendar.getInstance()));
		reglas.add(new ReglaCambioAtributosVehiculo(vehiculoServicio));
		reglas.add(new ReglaVehiculoEnParqueadero(vehiculoServicio));
		reglas.add(new ReglaParqueaderoLleno(ingresoParqueaderoRepositorio));
		
		for (ReglaNegocio regla : reglas) {
			if (!regla.verificarRegla(VehiculoBuilder.convertirADominio(vehiculo))) {
				regla.mostrarMensaje();
			}
		}
		
	}
	
}
