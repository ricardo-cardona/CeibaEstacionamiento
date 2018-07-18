package parqueadero.parqueadero.servicio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.VehiculoRepositorio;
import testdatabuilder.VehiculoEntityTestDataBuilder;

@RunWith(SpringRunner.class)
public class VehiculoServicioTest {
	
	private static final int ID_VEHICULO = 1;
	private static final String PLACA = "ABC-123";
	private static final int CILINDRAJE = 100;
	
	@TestConfiguration
    static class VehiculoServicioTestContextConfiguration {
		
        @Bean
        public VehiculoServicio vehiculoServicio() {
            return new VehiculoServicio();
        }
        
    }
 
    @Autowired
    private VehiculoServicio vehiculoServicio;
 
    @MockBean
    private VehiculoRepositorio vehiculoRepositorio;
	
	@Test
	public void registrarVehiculoNuevoTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder()
				.conId(0)
				.conPlaca(PLACA)
				.conCilindraje(CILINDRAJE)
				.build();
		
		when(vehiculoRepositorio.findByPlaca(vehiculo.getPlaca())).thenReturn(null);
		when(vehiculoRepositorio.save(vehiculo)).thenReturn(new VehiculoEntityTestDataBuilder()
				.conId(ID_VEHICULO)
				.conPlaca(PLACA)
				.conCilindraje(CILINDRAJE)
				.build());
		
		//act
		VehiculoEntity vehiculoRegistrado = vehiculoServicio.registrarVehiculo(vehiculo);
		
		//assert
		assertNotEquals(vehiculo.getId(), vehiculoRegistrado.getId());
		assertEquals(vehiculo.getPlaca(), vehiculoRegistrado.getPlaca());
		assertEquals(vehiculo.getTipoVehiculo().getId(), vehiculoRegistrado.getTipoVehiculo().getId());
		assertEquals(vehiculo.getCilindraje(), vehiculoRegistrado.getCilindraje());
		
	}
	
	@Test
	public void registrarVehiculoExistenteTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder()
				.conId(ID_VEHICULO)
				.conPlaca(PLACA)
				.conCilindraje(CILINDRAJE)
				.build();
		
		when(vehiculoRepositorio.findByPlaca(vehiculo.getPlaca())).thenReturn(new VehiculoEntityTestDataBuilder()
				.conId(ID_VEHICULO)
				.conPlaca(PLACA)
				.conCilindraje(CILINDRAJE)
				.build());
		
		//act
		VehiculoEntity vehiculoRegistrado = vehiculoServicio.registrarVehiculo(vehiculo);
		
		//assert
		assertEquals(vehiculo.getId(), vehiculoRegistrado.getId());
		assertEquals(vehiculo.getPlaca(), vehiculoRegistrado.getPlaca());
		assertEquals(vehiculo.getTipoVehiculo().getId(), vehiculoRegistrado.getTipoVehiculo().getId());
		assertEquals(vehiculo.getCilindraje(), vehiculoRegistrado.getCilindraje());
		
	}

}
