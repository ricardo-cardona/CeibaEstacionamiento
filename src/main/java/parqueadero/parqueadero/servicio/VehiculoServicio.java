package parqueadero.parqueadero.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.VehiculoRepositorio;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class VehiculoServicio {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	public VehiculoEntity registrarVehiculo(VehiculoEntity vehiculo) {
		
		VehiculoEntity vehiculoRegistrado = vehiculoRepositorio.findByPlaca(vehiculo.getPlaca());
		
		if (vehiculoRegistrado == null) {
			vehiculoRegistrado = vehiculoRepositorio.save(vehiculo);
		}
		
		return vehiculoRegistrado;
		
	}
	
	public List<VehiculoEntity> consultarVehiculos() {
		
		return vehiculoRepositorio.findAll();
		
	}
	
	public VehiculoEntity consultarVehiculo(String placa) {
		
		return vehiculoRepositorio.findByPlaca(placa);
		
	}
	
	public VehiculoEntity vehiculoEstaEnParqueadero(String placa) {
		
		return vehiculoRepositorio.vehiculoEstaEnParqueadero(placa);
		
	}
	
}
