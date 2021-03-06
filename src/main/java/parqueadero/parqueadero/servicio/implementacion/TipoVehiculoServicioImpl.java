package parqueadero.parqueadero.servicio.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.excepcion.TipoVehiculoExcepcion;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.TipoVehiculoRepositorio;
import parqueadero.parqueadero.servicio.TipoVehiculoServicio;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class TipoVehiculoServicioImpl implements TipoVehiculoServicio {
	
	@Autowired
	private TipoVehiculoRepositorio tipoVehiculoRepositorio;
	
	public static final String SIN_TIPO_VEHICULO = "No se indic� tipo de veh�culo.";
	public static final String TIPO_VEHICULO_INCORRECTO = "El tipo de veh�culo indicado es incorrecto.";
	
	public TipoVehiculoEntity consultarTipoVehiculo(TipoVehiculoEntity tipoVehiculo) {
		
		if (tipoVehiculo == null) {
			throw new TipoVehiculoExcepcion(SIN_TIPO_VEHICULO);
		}
		
		Optional<TipoVehiculoEntity> optTipoVehiculo = tipoVehiculoRepositorio.findById(tipoVehiculo.getId());
		
		if (!optTipoVehiculo.isPresent()) {
			throw new TipoVehiculoExcepcion(TIPO_VEHICULO_INCORRECTO);
		}
		
		return optTipoVehiculo.get();
	}
	
	public List<TipoVehiculoEntity> consultarTiposDeVehiculo() {
		
		return tipoVehiculoRepositorio.findAll();
		
	}
	
}
