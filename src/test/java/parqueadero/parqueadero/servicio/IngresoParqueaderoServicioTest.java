package parqueadero.parqueadero.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;
import parqueadero.parqueadero.persistencia.repositorio.VehiculoEnParqueaderoRepositorio;
import testdatabuilder.IngresoParqueaderoEntityTestDataBuilder;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;

@RunWith(SpringRunner.class)
public class IngresoParqueaderoServicioTest {
	
	private static final int ID_VEHICULO = 1;
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
	public void registrarIngresoVehiculoValidoTest() {
		
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
		
		when(tipoVehiculoServicio.consultarTipoVehiculo(vehiculo.getTipoVehiculo())).thenReturn(tipoVehiculo);
		when(vehiculoServicio.registrarVehiculo(vehiculo)).thenReturn(vehiculoRegistrado);
		when(ingresoParqueaderoRepositorio.save(any())).thenReturn(new IngresoParqueaderoEntityTestDataBuilder().conVehiculo(vehiculoRegistrado).build());
		
		//act
		IngresoParqueaderoEntity ingreso = ingresoParqueaderoServicio.registrarIngresoVehiculo(vehiculo);
		
		assertNotEquals(0L, ingreso.getId().longValue());
		assertEquals(vehiculoRegistrado.getId(), ingreso.getVehiculo().getId());
		assertNotNull(ingreso.getFechaInicio());
		
	}

}
