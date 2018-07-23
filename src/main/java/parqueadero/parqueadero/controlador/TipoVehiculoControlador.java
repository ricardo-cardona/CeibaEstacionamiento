package parqueadero.parqueadero.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.servicio.TipoVehiculoServicio;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TipoVehiculoControlador {
	
	@Autowired
	private TipoVehiculoServicio tipoVehiculoServicio;
	
	@GetMapping("/tipos")
	public List<TipoVehiculoEntity> consultarVehiculos() {
		
		return tipoVehiculoServicio.consultarTiposDeVehiculo();
		
	}
	
}
