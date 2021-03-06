package parqueadero.parqueadero.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.excepcion.TipoVehiculoExcepcion;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.servicio.IngresoParqueaderoServicio;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class IngresoParqueaderoControlador {
	
	@Autowired
	private IngresoParqueaderoServicio ingresoParqueaderoServicio;
	
	@PostMapping("/ingresos")
	public ResponseEntity<Object> registrarIngresoVehiculo(@RequestBody VehiculoEntity vehiculo) {
		
		IngresoParqueaderoEntity ingresoRegistrado = ingresoParqueaderoServicio.registrarIngresoVehiculo(vehiculo);
		
		if (ingresoRegistrado == null) {
			return ResponseEntity.badRequest().header("Error", "10").body(ingresoRegistrado);
		} else {
			return ResponseEntity.ok().body(ingresoRegistrado);
		}
		
	}
	
	@GetMapping("/ingresos/{ingreso}")
	public VehiculoEnParqueaderoEntity consultarIngreso(@PathVariable Long ingreso) {
		
		return ingresoParqueaderoServicio.consultarVehiculoEnParqueadero(ingreso);
		
	}
	
	@GetMapping("/parqueados")
	public List<VehiculoEnParqueaderoEntity> consultarVehiculosEnParqueadero() {
		
		return ingresoParqueaderoServicio.consultarVehiculosEnParqueadero();
		
	}
	
	@ExceptionHandler({IngresoParqueaderoExcepcion.class, TipoVehiculoExcepcion.class})
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
}
