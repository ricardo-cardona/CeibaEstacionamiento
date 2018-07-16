package parqueadero.parqueadero.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.TipoVehiculoRepositorio;

@Service
public class TipoVehiculoServicio {
	
	@Autowired
	private TipoVehiculoRepositorio tipoVehiculoRepositorio;
	
	public TipoVehiculoEntity consultarTipoVehiculo(Long id) {
		
		Optional<TipoVehiculoEntity> tipoVehiculo = tipoVehiculoRepositorio.findById(id);
		
		if (tipoVehiculo.isPresent()) {
			return tipoVehiculo.get();
		}
		
		return null;
		
	}
	
	public List<TipoVehiculoEntity> consultarTiposDeVehiculo() {
		
		return tipoVehiculoRepositorio.findAll();
		
	}
	
}
