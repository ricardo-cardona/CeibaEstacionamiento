package parqueadero.parqueadero.servicio;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface IngresoParqueaderoServicio {
	
	public IngresoParqueaderoEntity registrarIngresoVehiculo(VehiculoEntity vehiculo);
	
	public IngresoParqueaderoEntity consultarIngreso(Long ingreso);
	
	public List<VehiculoEnParqueaderoEntity> consultarVehiculosEnParqueadero();
	
	public VehiculoEnParqueaderoEntity consultarVehiculoEnParqueadero(Long ingreso);
	
}
