package parqueadero.parqueadero.servicio.implementacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.negocio.ReglaCambioAtributosVehiculo;
import parqueadero.parqueadero.negocio.ReglaCilindraje;
import parqueadero.parqueadero.negocio.ReglaPlaca;
import parqueadero.parqueadero.negocio.ReglaIngreso;
import parqueadero.parqueadero.negocio.ReglaParqueaderoLleno;
import parqueadero.parqueadero.negocio.ReglaVehiculoEnParqueadero;
import parqueadero.parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;
import parqueadero.parqueadero.persistencia.repositorio.VehiculoEnParqueaderoRepositorio;
import parqueadero.parqueadero.servicio.IngresoParqueaderoServicio;
import parqueadero.parqueadero.servicio.TipoVehiculoServicio;
import parqueadero.parqueadero.servicio.VehiculoServicio;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class IngresoParqueaderoServicioImpl implements IngresoParqueaderoServicio {
	
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
	
	public VehiculoEnParqueaderoEntity consultarVehiculoEnParqueadero(Long ingreso) {
		
		return vehiculoEnParqueaderoRepositorio.vehiculoEnParqueadero(ingreso);
		
	}
	
	private void verificarReglas(VehiculoEntity vehiculo) {
		
		List<ReglaIngreso> reglas = new ArrayList<>();
		
		reglas.add(new ReglaCilindraje());
		reglas.add(new ReglaPlaca(Calendar.getInstance()));
		reglas.add(new ReglaCambioAtributosVehiculo(vehiculoServicio));
		reglas.add(new ReglaVehiculoEnParqueadero(vehiculoServicio));
		reglas.add(new ReglaParqueaderoLleno(ingresoParqueaderoRepositorio));
		
		for (ReglaIngreso regla : reglas) {
			regla.verificarRegla(VehiculoBuilder.convertirADominio(vehiculo));
		}
		
	}
	
}
