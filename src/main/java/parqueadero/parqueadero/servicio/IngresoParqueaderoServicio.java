package parqueadero.parqueadero.servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parqueadero.parqueadero.excepcion.SalidaParqueaderoExcepcion;
import parqueadero.parqueadero.negocio.ReglaCambioAtributosVehiculo;
import parqueadero.parqueadero.negocio.ReglaCilindraje;
import parqueadero.parqueadero.negocio.ReglaDiaHabilPlaca;
import parqueadero.parqueadero.negocio.ReglaNegocio;
import parqueadero.parqueadero.negocio.ReglaParqueaderoLleno;
import parqueadero.parqueadero.negocio.ReglaVehiculoEnParqueadero;
import parqueadero.parqueadero.persistencia.builder.VehiculoBuilder;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;
import parqueadero.parqueadero.persistencia.repositorio.VehiculoEnParqueaderoRepositorio;

@Service
public class IngresoParqueaderoServicio {
	
	@Autowired
	private IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio;
	
	@Autowired
	private VehiculoEnParqueaderoRepositorio vehiculoEnParqueaderoRepositorio;
	
	@Autowired
	private VehiculoServicio vehiculoServicio;
	
	@Autowired
	private TipoVehiculoServicio tipoVehiculoServicio;
	
	public IngresoParqueaderoEntity registrarIngresoVehiculo(VehiculoEntity vehiculo) {
		
		vehiculo.setTipoVehiculo(tipoVehiculoServicio.consultarTipoVehiculo(vehiculo.getTipoVehiculo()));
		
		verificarReglas(vehiculo);
		
		vehiculo = vehiculoServicio.registrarVehiculo(vehiculo);
		
		return guardarIngreso(vehiculo);
		
	}
	
	private IngresoParqueaderoEntity guardarIngreso(VehiculoEntity vehiculo) {
		
		IngresoParqueaderoEntity ingreso = new IngresoParqueaderoEntity();
		
		ingreso.setVehiculo(vehiculo);
		ingreso.setFechaInicio(Calendar.getInstance());
		
		return ingresoParqueaderoRepositorio.save(ingreso);
		
	}
	
	public IngresoParqueaderoEntity consultarIngreso(Long ingreso) {
		
		Optional<IngresoParqueaderoEntity> ingresoParqueadero = ingresoParqueaderoRepositorio.findById(ingreso);
		
		IngresoParqueaderoEntity ingresoRegistrado = null;
		
		if (ingresoParqueadero.isPresent()) {
			ingresoRegistrado = ingresoParqueadero.get();
		}
		
		return ingresoRegistrado;
		
	}
	
	public List<VehiculoEnParqueaderoEntity> consultarVehiculosEnParqueadero() {
		
		return vehiculoEnParqueaderoRepositorio.vehiculosEnParqueadero();
		
	}
	
	public IngresoParqueaderoEntity registrarSalidaParqueadero(IngresoParqueaderoEntity ingreso) {
		
		if (ingreso.getFechaFin() != null) {
			throw new SalidaParqueaderoExcepcion("Esta salida de parqueadero ya había sido registrada.");
		}
		
		ingreso.setFechaFin(Calendar.getInstance());
		ingreso.setValor(calcularValorAPagar(ingreso));
		ingreso = ingresoParqueaderoRepositorio.save(ingreso);
		
		return ingreso;
		
	}
	
	private double calcularValorAPagar(IngresoParqueaderoEntity ingreso){
		
		double valor;
		double diferenciaEnHoras;
		
		diferenciaEnHoras = diferenciaFechasEnHoras(ingreso.getFechaInicio(), ingreso.getFechaFin());
		
		valor = valorACobrar(diferenciaEnHoras, ingreso.getVehiculo().getTipoVehiculo().getValorDia(), ingreso.getVehiculo().getTipoVehiculo().getValorHora());
		
		if (esAltoCilindraje(ingreso.getVehiculo())) {
			valor = valor + ingreso.getVehiculo().getTipoVehiculo().getValorAdicionalCilindraje();
		}
		
		return valor;
		
	}
	
	private double diferenciaFechasEnHoras(Calendar fechaInicial, Calendar fechaFinal) {
		
		Long diferenciaEnMilisegundos = fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis();
		
		return diferenciaEnMilisegundos / (1000.0 * 60 * 60);
		
	}
	
	private double valorACobrar(double horas, double valorDia, double valorHora) {
		
		double dias = horas / 24;
		
		double valor = Math.floor(dias) * valorDia;
		
		horas = horas % 24;
		horas = Math.floor(horas) + Math.ceil(horas % 1);
		
		if (horas >= 9) {
			valor = valor + valorDia;
		} else {
			valor = valor + (horas * valorHora);
		}
		
		return valor;
		
	}
	
	private boolean esAltoCilindraje(VehiculoEntity vehiculo) {
		
		return (vehiculo.getTipoVehiculo().getTieneCilindraje()
				&& vehiculo.getCilindraje() > vehiculo.getTipoVehiculo().getAltoCilindraje());
		
	}
	
	public void verificarReglas(VehiculoEntity vehiculo) {
		
		List<ReglaNegocio> reglas = new ArrayList<>();
		
		reglas.add(new ReglaCilindraje());
		reglas.add(new ReglaDiaHabilPlaca(Calendar.getInstance()));
		reglas.add(new ReglaCambioAtributosVehiculo(vehiculoServicio));
		reglas.add(new ReglaVehiculoEnParqueadero(vehiculoServicio));
		reglas.add(new ReglaParqueaderoLleno(ingresoParqueaderoRepositorio));
		
		for (ReglaNegocio regla : reglas) {
			if (!regla.verificarRegla(VehiculoBuilder.convertirADominio(vehiculo))) {
				regla.mostrarMensaje();
			}
		}
		
	}
	
}
