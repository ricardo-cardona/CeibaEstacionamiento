package parqueadero.parqueadero.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.servicio.VehiculoServicio;

@RestController
public class VehiculoControlador {
	
	@Autowired
	private VehiculoServicio vehiculoServicio;
	
	@GetMapping("/vehiculos")
	public List<VehiculoEntity> consultarVehiculos() {
		
		return vehiculoServicio.consultarVehiculos();
		
	}
	
	@GetMapping("/vehiculos/{placa}")
	public ResponseEntity<VehiculoEntity> consultarVehiculo(@PathVariable String placa) {
		
		VehiculoEntity vehiculoConsultado = vehiculoServicio.consultarVehiculo(placa);
		
		if (vehiculoConsultado == null) {
			return ResponseEntity.badRequest().header("Error", "10").body(vehiculoConsultado);
		} else {
			return ResponseEntity.ok().body(vehiculoConsultado);
		}
		
	}
	
	@PostMapping("/vehiculos")
	public ResponseEntity<VehiculoEntity> registrarEntradaVehiculo(@RequestBody VehiculoEntity vehiculo) {
		
		VehiculoEntity vehiculoRegistrado = vehiculoServicio.registrarVehiculo(vehiculo);
		
		if (vehiculoRegistrado == null) {
			return ResponseEntity.badRequest().header("Error", "10").body(vehiculoRegistrado);
		} else {
			return ResponseEntity.ok().body(vehiculoRegistrado);
		}
		
	}
	
}
