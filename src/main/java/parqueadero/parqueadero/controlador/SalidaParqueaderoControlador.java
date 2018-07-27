package parqueadero.parqueadero.controlador;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import parqueadero.parqueadero.excepcion.SalidaParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.servicio.SalidaParqueaderoServicio;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SalidaParqueaderoControlador {
	
	@Autowired
	private SalidaParqueaderoServicio salidaParqueaderoServicio;
	
	@PutMapping("/salidas/{ingreso}")
	public ResponseEntity<Object> registrarSalidaParqueadero(@PathVariable Long ingreso) {
		
		IngresoParqueaderoEntity ingresoActualizado = salidaParqueaderoServicio.registrarSalidaParqueadero(ingreso);
		
		if (ingresoActualizado == null) {
			return ResponseEntity.badRequest().header("Error", "10").body(ingresoActualizado);
		} else {
			return ResponseEntity.ok().body(ingresoActualizado);
		}
		
	}
	
	@ExceptionHandler({SalidaParqueaderoExcepcion.class})
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
}
