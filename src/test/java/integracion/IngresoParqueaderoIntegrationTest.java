package integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

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
import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.excepcion.SalidaParqueaderoExcepcion;
import parqueadero.parqueadero.negocio.ReglaSalidaRegistrada;
import parqueadero.parqueadero.negocio.ReglaVehiculoEnParqueadero;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;

@SpringBootTest(classes = ParqueaderoApplication.class)
@Transactional
@RunWith(SpringRunner.class)
@Rollback(value=true)
public class IngresoParqueaderoIntegrationTest {
	
	@Autowired
    private IngresoParqueaderoControlador ingresoParqueaderoControlador;
	
	private static final String PLACA_VEHICULO_1 = "VEH-111";
	private static final String PLACA_VEHICULO_2 = "VEH-222";
	private static final double VALOR_CERO = 0;
	private static final Long ID_TIPO_VEHICULO_CON_CILINDRAJE = 2L;
	private static final int MAYOR_A_ALTO_CILINDRAJE = 550;
	
	@Test
	public void registrarIngresoVehiculoTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder().build();
		
		//act
		ResponseEntity<Object> respuesta = ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo);
		
		IngresoParqueaderoEntity ingresoRegistrado = (IngresoParqueaderoEntity) respuesta.getBody();
		
		//assert
		assertNotNull(ingresoRegistrado);
		assertEquals(vehiculo.getPlaca(), ingresoRegistrado.getVehiculo().getPlaca());
		
	}
	
	@Test
	public void registrarIngresoVehiculoRegistradoTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder().build();
		
		ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo);
		
		//act
		try {
			ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch(IngresoParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaVehiculoEnParqueadero.VEHICULO_YA_ESTA_EN_PARQUEADERO, e.getMessage());
		}
		
	}
	
	@Test
	public void consultarIngresoTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder().build();
		
		ResponseEntity<Object> respuesta = ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo);
		
		IngresoParqueaderoEntity ingresoRegistrado = (IngresoParqueaderoEntity) respuesta.getBody();
		
		//act
		VehiculoEnParqueaderoEntity parqueado = ingresoParqueaderoControlador.consultarIngreso(ingresoRegistrado.getId());
		
		//assert
		assertNotNull(parqueado);
		assertEquals(ingresoRegistrado.getId().longValue(), parqueado.getId());
		assertEquals(vehiculo.getPlaca(), parqueado.getPlaca());
		
	}
	
	@Test
	public void consultarVehiculosEnParqueaderoTest() {
		
		//arrange
		VehiculoEntity vehiculo1 = new VehiculoEntityTestDataBuilder()
				.conPlaca(PLACA_VEHICULO_1)
				.build();
		
		VehiculoEntity vehiculo2 = new VehiculoEntityTestDataBuilder()
				.conPlaca(PLACA_VEHICULO_2)
				.build();
		
		ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo1);
		ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo2);
		
		//act
		List<VehiculoEnParqueaderoEntity> parqueados = ingresoParqueaderoControlador.consultarVehiculosEnParqueadero();
		
		//assert
		assertNotNull(parqueados);
		assertEquals(2, parqueados.size());
		
	}
	
	@Test
	public void registrarSalidaParqueaderoTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder().build();
		
		ResponseEntity<Object> respuesta = ingresoParqueaderoControlador.registrarIngresoVehiculo(vehiculo);
		
		IngresoParqueaderoEntity ingreso = (IngresoParqueaderoEntity) respuesta.getBody();
		
		//act
		respuesta = ingresoParqueaderoControlador.registrarSalidaParqueadero(ingreso.getId());
		
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
		respuesta = ingresoParqueaderoControlador.registrarSalidaParqueadero(ingreso.getId());
		
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
		
		ingresoParqueaderoControlador.registrarSalidaParqueadero(ingreso.getId());
		
		//act
		try {
			ingresoParqueaderoControlador.registrarSalidaParqueadero(ingreso.getId());
			fail();
		} catch(SalidaParqueaderoExcepcion e) {
			//assert
			assertEquals(ReglaSalidaRegistrada.SALIDA_YA_REGISTRADA, e.getMessage());
		}
		
	}

}
