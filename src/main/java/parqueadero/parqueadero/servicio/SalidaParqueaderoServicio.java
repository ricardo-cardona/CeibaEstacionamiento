package parqueadero.parqueadero.servicio;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface SalidaParqueaderoServicio {
	
	public IngresoParqueaderoEntity registrarSalidaParqueadero(Long ingreso);
	
}
