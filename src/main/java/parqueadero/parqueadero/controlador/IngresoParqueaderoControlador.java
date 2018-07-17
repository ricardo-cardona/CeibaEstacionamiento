package parqueadero.parqueadero.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.servicio.IngresoParqueaderoServicio;

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
	
	@GetMapping("/parqueados")
	public List<VehiculoEnParqueaderoEntity> consultarVehiculosEnParqueadero() {
		
		return ingresoParqueaderoServicio.consultarVehiculosEnParqueadero();
		
	}
	
	@PutMapping("/salidas/{ingreso}")
	public ResponseEntity<Object> registrarSalidaParqueadero(@PathVariable Long ingreso) {
		
		IngresoParqueaderoEntity ingresoRegistrado = ingresoParqueaderoServicio.consultarIngreso(ingreso);
		ingresoParqueaderoServicio.registrarSalidaParqueadero(ingresoRegistrado);
		
		if (ingresoRegistrado == null) {
			return ResponseEntity.badRequest().header("Error", "10").body(ingresoRegistrado);
		} else {
			return ResponseEntity.ok().body(ingresoRegistrado);
		}
		
	}
	
}
