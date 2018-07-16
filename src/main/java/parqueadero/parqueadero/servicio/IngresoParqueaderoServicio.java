package parqueadero.parqueadero.servicio;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parqueadero.parqueadero.dominio.Parqueadero;
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.excepcion.SalidaParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
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
		
		IngresoParqueaderoEntity ingreso = null;
		
		Calendar fechaActual = Calendar.getInstance();
		
		vehiculo.setTipoVehiculo(consultarTipoVehiculo(vehiculo.getTipoVehiculo()));
		
		verificarReglas(vehiculo);
		
		vehiculo = vehiculoServicio.registrarVehiculo(vehiculo);
		
		ingreso = new IngresoParqueaderoEntity();
		ingreso.setVehiculo(vehiculo);
		ingreso.setFechaInicio(fechaActual);
		ingreso = ingresoParqueaderoRepositorio.save(ingreso);
		
		return ingreso;
		
	}
	
	public List<IngresoParqueaderoEntity> consultarIngresos() {
		
		return ingresoParqueaderoRepositorio.findAll();
		
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
		
		// calcular la diferencia en horas entre las fechas de inicio y fin (incluir decimales)
		diferenciaEnHoras = diferenciaFechasEnHoras(ingreso.getFechaInicio(), ingreso.getFechaFin());
		
		// de acuerdo a la cantidad de horas, establecer la forma de cobro (por horas o por días)
		valor = valorACobrar(diferenciaEnHoras, ingreso.getVehiculo().getTipoVehiculo().getValorDia(), ingreso.getVehiculo().getTipoVehiculo().getValorHora());
		
		// si se trata de una moto con alto cilindraje, sumar el valor adicional a cobrar
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
	
	private boolean parqueaderoEstaLleno(TipoVehiculoEntity tipoVehiculo) {
		
		return (ingresoParqueaderoRepositorio.cantidadTipoVehiculoEnParqueadero(tipoVehiculo.getId())
				>= tipoVehiculo.getCapacidadMaxima());
		
	}
	
	private int cantidadMaximaTipoVehiculo(String tipoVehiculo) {
		
		if (tipoVehiculo.equalsIgnoreCase("carro")) {
			return Parqueadero.CANTIDAD_MAXIMA_CARROS;
		}
		
		if (tipoVehiculo.equalsIgnoreCase("moto")) {
			return Parqueadero.CANTIDAD_MAXIMA_MOTOS;
		}
		
		return 0;
		
	}
	
	private boolean esDiaHabilParaPlaca(String placa, Calendar fecha) {
		
		if (placa.toUpperCase().startsWith("A")) {
			int diaSemana = fecha.get(Calendar.DAY_OF_WEEK);
			return (diaSemana == Calendar.SUNDAY || diaSemana == Calendar.MONDAY);
		}
		
		return true;
		
	}
	
	private boolean vehiculoSeEncuentraEnParqueadero(String placa) {
		
		VehiculoEntity vehiculo = vehiculoServicio.vehiculoEstaEnParqueadero(placa);
		
		return (vehiculo != null);
		
	}
	
	private boolean cambioCilindraje(VehiculoEntity vehiculo, int cilindraje) {
		
		return (vehiculo.getCilindraje() != cilindraje);
		
	}
	
	private boolean cambioTipoVehiculo(VehiculoEntity vehiculo, TipoVehiculoEntity tipoVehiculo) {
		
		return (vehiculo.getTipoVehiculo() != tipoVehiculo);
		
	}
	
	private boolean cilindrajeValidoSegunTipoVehiculo(VehiculoEntity vehiculo) {
		
		return ((vehiculo.getTipoVehiculo().getTieneCilindraje() && vehiculo.getCilindraje() > 0)
				|| (!vehiculo.getTipoVehiculo().getTieneCilindraje() && vehiculo.getCilindraje() == 0));
		
	}
	
	private TipoVehiculoEntity consultarTipoVehiculo(TipoVehiculoEntity tipoVehiculo) {
		
		if (tipoVehiculo == null) {
			throw new IngresoParqueaderoExcepcion("No se indicó tipo de vehículo.");
		}
		
		tipoVehiculo = tipoVehiculoServicio.consultarTipoVehiculo(tipoVehiculo.getId());
		
		if (tipoVehiculo == null) {
			throw new IngresoParqueaderoExcepcion("El tipo de vehículo indicado es incorrecto.");
		}
		
		return tipoVehiculo;
	}
	
	public void verificarReglas(VehiculoEntity vehiculo) {
		
		/*List<ReglaNegocio> reglasNegocio = new ArrayList<>();
		
		reglasNegocio.add(new ReglaDiaHabilPlaca());
		reglasNegocio.add(new ReglaParqueaderoLleno());
		
		for (ReglaNegocio regla : reglasNegocio) {
			if (!regla.verificarRegla(VehiculoBuilder.convertirADominio(vehiculo))) {
				regla.mostrarMensaje();
			}
		}*/
		
		Calendar fechaActual = Calendar.getInstance();
		
		if (!cilindrajeValidoSegunTipoVehiculo(vehiculo)){
			throw new IngresoParqueaderoExcepcion("Las motos deben tener cilindraje, los carros no.");
		}
		
		if (!esDiaHabilParaPlaca(vehiculo.getPlaca(), fechaActual)) {
			throw new IngresoParqueaderoExcepcion("El vehículo no puede ingresar al parqueadero, debido a que no es un día hábil.");
		}
		
		VehiculoEntity vehiculoRegistrado = vehiculoServicio.consultarVehiculo(vehiculo.getPlaca());
		
		if (vehiculoRegistrado != null
				&& cambioTipoVehiculo(vehiculo, vehiculoRegistrado.getTipoVehiculo())) {
			throw new IngresoParqueaderoExcepcion("El vehículo se había registrado anteriormente con otro tipo, por favor revisar.");
		}
		
		if (vehiculoRegistrado != null
				&& cambioCilindraje(vehiculo, vehiculoRegistrado.getCilindraje())) {
			throw new IngresoParqueaderoExcepcion("El vehículo se había registrado anteriormente con otro cilindraje, por favor revisar.");
		}
		
		if (vehiculoSeEncuentraEnParqueadero(vehiculo.getPlaca())) {
			throw new IngresoParqueaderoExcepcion("El vehículo ya se encuentra en el parqueadero, no puede volver a ingresar.");
		}
		
		if (parqueaderoEstaLleno(vehiculo.getTipoVehiculo())) {
			throw new IngresoParqueaderoExcepcion("El vehículo no puede ingresar, debido a que el parqueadero se encuentra completamente ocupado.");
		}
		
	}
	
}
