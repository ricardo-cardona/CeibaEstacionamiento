package parqueadero.parqueadero.servicio.implementacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import parqueadero.parqueadero.dominio.Parqueadero;
import parqueadero.parqueadero.negocio.ReglaSalida;
import parqueadero.parqueadero.negocio.ReglaSalidaInvalida;
import parqueadero.parqueadero.negocio.ReglaSalidaRegistrada;
import parqueadero.parqueadero.persistencia.builder.IngresoParqueaderoBuilder;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;
import parqueadero.parqueadero.servicio.IngresoParqueaderoServicio;
import parqueadero.parqueadero.servicio.SalidaParqueaderoServicio;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class SalidaParqueaderoServicioImpl implements SalidaParqueaderoServicio {
	
	@Autowired
	private IngresoParqueaderoServicio ingresoParqueaderoServicio;
	
	@Autowired
	private IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio;
	
	public IngresoParqueaderoEntity registrarSalidaParqueadero(Long ingreso) {
		
		IngresoParqueaderoEntity ingresoRegistrado = ingresoParqueaderoServicio.consultarIngreso(ingreso);
		
		verificarReglas(ingresoRegistrado);
		
		ingresoRegistrado.setFechaFin(Calendar.getInstance());
		ingresoRegistrado.setValor(calcularValorAPagar(ingresoRegistrado));
		
		return ingresoParqueaderoRepositorio.save(ingresoRegistrado);
		
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
		
		return diferenciaEnMilisegundos / Parqueadero.UNA_HORA_EN_MILISEGUNDOS;
		
	}
	
	private double valorACobrar(double horas, double valorDia, double valorHora) {
		
		double dias = horas / Parqueadero.CANTIDAD_MAXIMA_COBRO_DIA;
		
		double valor = Math.floor(dias) * valorDia;
		
		horas = horas % Parqueadero.CANTIDAD_MAXIMA_COBRO_DIA;
		horas = Math.floor(horas) + Math.ceil(horas % 1);
		
		if (horas >= Parqueadero.CANTIDAD_MAXIMA_COBRO_HORA) {
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
	
	private void verificarReglas(IngresoParqueaderoEntity ingresoRegistrado) {
		
		List<ReglaSalida> reglas = new ArrayList<>();
		
		reglas.add(new ReglaSalidaInvalida());
		reglas.add(new ReglaSalidaRegistrada());
		
		for (ReglaSalida regla : reglas) {
			regla.verificarRegla(IngresoParqueaderoBuilder.convertirADominio(ingresoRegistrado));
		}
		
	}
	
}
