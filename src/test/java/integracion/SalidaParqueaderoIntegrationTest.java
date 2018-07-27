package integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero.parqueadero.ParqueaderoApplication;
import parqueadero.parqueadero.controlador.IngresoParqueaderoControlador;
import parqueadero.parqueadero.controlador.SalidaParqueaderoControlador;
import parqueadero.parqueadero.excepcion.SalidaParqueaderoExcepcion;
import parqueadero.parqueadero.negocio.ReglaSalidaRegistrada;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;

@SpringBootTest(classes = ParqueaderoApplication.class)
@Transactional
@RunWith(SpringRunner.class)
@Rollback(value=true)
public class SalidaParqueaderoIntegrationTest {
	
	@Autowired
    private IngresoParqueaderoControlador ingresoParqueaderoControlador;
	
	@Autowired
    private SalidaParqueaderoControlador salidaParqueaderoControlador;
	
	private static final double VALOR_CERO = 0;
	private static final Long ID_TIPO_VEHICULO_CON_CILINDRAJE = 2L;
	private static final int MAYOR_A_ALTO_CILINDRAJE = 550;
	
	@Test
	public void registrarSalidaParqueaderoTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder().build();
		
		ResponseEntity<Object> respuesta = ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo);
		
		IngresoParqueaderoEntity ingreso = (IngresoParqueaderoEntity) respuesta.getBody();
		
		//act
		respuesta = salidaParqueaderoControlador.registrarSalidaParqueadero(ingreso.getId());
		
		IngresoParqueaderoEntity salida = (IngresoParqueaderoEntity) respuesta.getBody();
		
		//assert
		assertNotNull(salida);
		assertEquals(ingreso.getId(), salida.getId());
		assertNotNull(salida.getFechaFin());
		assertNotEquals(VALOR_CERO, salida.getValor());
		
	}
	
	@Test
	public void registrarSalidaParqueaderoVehiculoAltoCilindrajeTest() {
		
		//arrange
		TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntityTestDataBuilder()
				.conId(ID_TIPO_VEHICULO_CON_CILINDRAJE)
				.build();
		
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder()
				.conTipoVehiculo(tipoVehiculo)
				.conCilindraje(MAYOR_A_ALTO_CILINDRAJE)
				.build();
		
		ResponseEntity<Object> respuesta = ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo);
		
		IngresoParqueaderoEntity ingreso = (IngresoParqueaderoEntity) respuesta.getBody();
		
		//act
		respuesta = salidaParqueaderoControlador.registrarSalidaParqueadero(ingreso.getId());
		
		IngresoParqueaderoEntity salida = (IngresoParqueaderoEntity) respuesta.getBody();
		
		//assert
		assertNotNull(salida);
		assertEquals(ingreso.getId(), salida.getId());
		assertNotNull(salida.getFechaFin());
		assertNotEquals(VALOR_CERO, salida.getValor());
		
	}
	
	@Test
	public void registrarSalidaParqueaderoRegistradaTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder().build();
		
		ResponseEntity<Object> respuesta = ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo);
		
		IngresoParqueaderoEntity ingreso = (IngresoParqueaderoEntity) respuesta.getBody();
		
		salidaParqueaderoControlador.registrarSalidaParqueadero(ingreso.getId());
		
		//act
		try {
			salidaParqueaderoControlador.registrarSalidaParqueadero(ingreso.getId());
			fail();
		} catch(SalidaParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaSalidaRegistrada.SALIDA_YA_REGISTRADA, e.getMessage());
		}
		
	}

}
