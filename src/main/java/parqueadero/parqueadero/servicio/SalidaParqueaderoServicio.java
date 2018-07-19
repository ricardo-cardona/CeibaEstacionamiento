package parqueadero.parqueadero.servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero.parqueadero.dominio.Parqueadero;
import parqueadero.parqueadero.negocio.ReglaSalida;
import parqueadero.parqueadero.negocio.ReglaSalidaInvalida;
import parqueadero.parqueadero.negocio.ReglaSalidaRegistrada;
import parqueadero.parqueadero.persistencia.builder.IngresoParqueaderoBuilder;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;

@Service
public class SalidaParqueaderoServicio {
	
	@Autowired
	private IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio;
	
	public IngresoParqueaderoEntity registrarSalidaParqueadero(Long ingreso) {
		
		IngresoParqueaderoEntity ingresoRegistrado = consultarIngreso(ingreso);
		
		verificarReglas(ingresoRegistrado);
		
		ingresoRegistrado.setFechaFin(Calendar.getInstance());
		ingresoRegistrado.setValor(calcularValorAPagar(ingresoRegistrado));
		
		return ingresoParqueaderoRepositorio.save(ingresoRegistrado);
		
	}
	
	public IngresoParqueaderoEntity consultarIngreso(Long ingreso) {
		
		Optional<IngresoParqueaderoEntity> ingresoParqueadero = ingresoParqueaderoRepositorio.findById(ingreso);
		
		IngresoParqueaderoEntity ingresoRegistrado = null;
		
		if (ingresoParqueadero.isPresent()) {
			ingresoRegistrado = ingresoParqueadero.get();
		}
		
		return ingresoRegistrado;
		
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
