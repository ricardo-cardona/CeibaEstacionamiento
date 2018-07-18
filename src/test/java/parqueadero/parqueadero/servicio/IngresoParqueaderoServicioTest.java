package parqueadero.parqueadero.servicio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero.parqueadero.excepcion.IngresoParqueaderoExcepcion;
import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;
import parqueadero.parqueadero.persistencia.repositorio.VehiculoEnParqueaderoRepositorio;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;

@RunWith(SpringRunner.class)
//@ContextConfiguration
public class IngresoParqueaderoServicioTest {
	
	private static final int ID_VEHICULO = 1;
	private static final Long ID_TIPO_VEHICULO_INVALIDO = -1L;
	private static final Long ID_TIPO_VEHICULO_CARRO = 1L;
	private static final String PLACA = "XYZ-789";
	private static final int SIN_CILINDRAJE = 0;
	
	@TestConfiguration
    static class IngresoParqueaderoServicioTestContextConfiguration {
		
        @Bean
        public IngresoParqueaderoServicio ingresoParqueaderoServicio() {
            return new IngresoParqueaderoServicio();
        }
        
    }
 
    @Autowired
    private IngresoParqueaderoServicio ingresoParqueaderoServicio;
 
    @MockBean
    private IngresoParqueaderoRepositorio ingresoParqueaderoRepositorio;
    
    @MockBean
    private VehiculoEnParqueaderoRepositorio vehiculoEnParqueaderoRepositorio;
	
    @MockBean
	private VehiculoServicio vehiculoServicio;
	
    @MockBean
	private TipoVehiculoServicio tipoVehiculoServicio;
    
	@Test
	public void registrarIngresoSinTipoVehiculoTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder()
				.conId(ID_VEHICULO)
				.conTipoVehiculo(null)
				.build();
		
		//act
		try {
			ingresoParqueaderoServicio.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch(IngresoParqueaderoExcepcion e) {
			//assert
			assertEquals(IngresoParqueaderoServicio.SIN_TIPO_VEHICULO, e.getMessage());
		}
		
	}
	
	@Test
	public void registrarIngresoConTipoVehiculoInvalidoTest() {
		
		//arrange
		TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntityTestDataBuilder()
				.conId(ID_TIPO_VEHICULO_INVALIDO)
				.build();
		
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder()
				.conId(ID_VEHICULO)
				.conTipoVehiculo(tipoVehiculo)
				.build();
		
		//act
		try {
			ingresoParqueaderoServicio.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch(IngresoParqueaderoExcepcion e) {
			//assert
			assertEquals(IngresoParqueaderoServicio.TIPO_VEHICULO_INCORRECTO, e.getMessage());
		}
		
	}
	
	/*@Test
	//@Sql("data.sql")
	@Sql(statements = {"INSERT INTO TIPO_VEHICULO (NOMBRE, CAPACIDAD_MAXIMA, VALOR_HORA, VALOR_DIA, TIENE_CILINDRAJE, ALTO_CILINDRAJE, VALOR_ADICIONAL_CILINDRAJE) VALUES ('CARRO', 20, 1000, 8000, FALSE, 0, 0);"})
	public void registrarIngresoConTipoVehiculoValidoTest() {
		
		//arrange
		TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntityTestDataBuilder()
				.conId(ID_TIPO_VEHICULO_CARRO)
				.conTieneCilindraje(false)
				.build();
		
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder()
				.conId(ID_VEHICULO)
				.conPlaca(PLACA)
				.conTipoVehiculo(tipoVehiculo)
				.conCilindraje(SIN_CILINDRAJE)
				.build();
		
		VehiculoEntity vehiculoRegistrado = new VehiculoEntityTestDataBuilder()
				.conId(ID_VEHICULO)
				.conPlaca(PLACA)
				.conTipoVehiculo(tipoVehiculo)
				.conCilindraje(SIN_CILINDRAJE)
				.build();
		
		when(vehiculoServicio.registrarVehiculo(vehiculo)).thenReturn(vehiculoRegistrado);
		
		//when(ingresoParqueaderoRepositorio.save(ingreso)).thenReturn();
		
		//act
		IngresoParqueaderoEntity ingreso = ingresoParqueaderoServicio.registrarIngresoVehiculo(vehiculo);
		
		assertNotEquals(0L, ingreso.getId().longValue());
		assertEquals(vehiculoRegistrado.getId(), ingreso.getVehiculo().getId());
		assertNotNull(ingreso.getFechaInicio());
		
	}*/

}
