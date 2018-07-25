package parqueadero.parqueadero.servicio;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface TipoVehiculoServicio {
	
	public TipoVehiculoEntity consultarTipoVehiculo(TipoVehiculoEntity tipoVehiculo);
	
	public List<TipoVehiculoEntity> consultarTiposDeVehiculo();
	
}
