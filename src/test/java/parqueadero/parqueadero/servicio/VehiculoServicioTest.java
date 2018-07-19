package parqueadero.parqueadero.servicio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

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
	private static final int ID_VEHICULO_2 = 2;
	private static final String PLACA = "ABC-123";
	private static final String PLACA_2 = "XYZ-789";
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
	
	@Test
	public void consultarVehiculoExistenteTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder()
				.conPlaca(PLACA)
				.build();
		
		when(vehiculoRepositorio.findByPlaca(PLACA)).thenReturn(vehiculo);
		
		//act
		VehiculoEntity vehiculoConsultado = vehiculoServicio.consultarVehiculo(PLACA);
		
		//assert
		assertEquals(vehiculo.getId(), vehiculoConsultado.getId());
		assertEquals(vehiculo.getPlaca(), vehiculoConsultado.getPlaca());
		assertEquals(vehiculo.getTipoVehiculo().getId(), vehiculoConsultado.getTipoVehiculo().getId());
		assertEquals(vehiculo.getCilindraje(), vehiculoConsultado.getCilindraje());
		
	}
	
	@Test
	public void consultarVehiculoNoExistenteTest() {

		//arrange
		when(vehiculoRepositorio.findByPlaca(PLACA)).thenReturn(null);
		
		//act
		VehiculoEntity vehiculoConsultado = vehiculoServicio.consultarVehiculo(PLACA);
		
		//assert
		assertNull(vehiculoConsultado);
		
	}
	
	@Test
	public void consultarVehiculoEstaEnparqueaderoTest() {
		
		//arrange
		VehiculoEntity vehiculo = new VehiculoEntityTestDataBuilder()
				.conPlaca(PLACA)
				.build();
		
		when(vehiculoRepositorio.vehiculoEstaEnParqueadero(PLACA)).thenReturn(vehiculo);
		
		//act
		VehiculoEntity vehiculoConsultado = vehiculoServicio.vehiculoEstaEnParqueadero(PLACA);
		
		//assert
		assertEquals(vehiculo.getId(), vehiculoConsultado.getId());
		assertEquals(vehiculo.getPlaca(), vehiculoConsultado.getPlaca());
		assertEquals(vehiculo.getTipoVehiculo().getId(), vehiculoConsultado.getTipoVehiculo().getId());
		assertEquals(vehiculo.getCilindraje(), vehiculoConsultado.getCilindraje());
		
	}
	
	@Test
	public void consultarVehiculoNoEstaEnparqueaderoTest() {
		
		//arrange
		when(vehiculoRepositorio.vehiculoEstaEnParqueadero(PLACA)).thenReturn(null);
		
		//act
		VehiculoEntity vehiculoConsultado = vehiculoServicio.vehiculoEstaEnParqueadero(PLACA);
		
		//assert
		assertNull(vehiculoConsultado);
		
	}
	
	@Test
	public void consultarVehiculosTest() {
		
		//arrange
		List<VehiculoEntity> vehiculos = new ArrayList<>();
		
		vehiculos.add(new VehiculoEntityTestDataBuilder().conId(ID_VEHICULO).conPlaca(PLACA).build());
		vehiculos.add(new VehiculoEntityTestDataBuilder().conId(ID_VEHICULO_2).conPlaca(PLACA_2).build());
		
		when(vehiculoRepositorio.findAll()).thenReturn(vehiculos);
		
		//act
		List<VehiculoEntity> vehiculosConsultados = vehiculoServicio.consultarVehiculos();
		
		//assert
		assertEquals(vehiculos.size(), vehiculosConsultados.size());
		assertEquals(vehiculos.get(0).getId(), vehiculosConsultados.get(0).getId());
		assertEquals(vehiculos.get(0).getPlaca(), vehiculosConsultados.get(0).getPlaca());
		assertEquals(vehiculos.get(1).getId(), vehiculosConsultados.get(1).getId());
		assertEquals(vehiculos.get(1).getPlaca(), vehiculosConsultados.get(1).getPlaca());
		
	}
	
	@Test
	public void consultarVehiculosVacioTest() {
		
		//arrange
		List<VehiculoEntity> vehiculos = new ArrayList<>();
		
		when(vehiculoRepositorio.findAll()).thenReturn(vehiculos);
		
		//act
		List<VehiculoEntity> vehiculosConsultados = vehiculoServicio.consultarVehiculos();
		
		//assert
		assertTrue(vehiculosConsultados.isEmpty());
		
	}

}
