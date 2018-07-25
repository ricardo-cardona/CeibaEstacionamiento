package parqueadero.parqueadero.servicio;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface VehiculoServicio {
	
	public VehiculoEntity registrarVehiculo(VehiculoEntity vehiculo);
	
	public List<VehiculoEntity> consultarVehiculos();
	
	public VehiculoEntity consultarVehiculo(String placa);
	
	public VehiculoEntity vehiculoEstaEnParqueadero(String placa);
	
}
