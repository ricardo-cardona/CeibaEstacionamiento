package parqueadero.parqueadero.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero.parqueadero.persistencia.entidad.IngresoParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEnParqueaderoEntity;
import parqueadero.parqueadero.persistencia.entidad.VehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.IngresoParqueaderoRepositorio;
import parqueadero.parqueadero.persistencia.repositorio.VehiculoEnParqueaderoRepositorio;
import parqueadero.parqueadero.servicio.implementacion.IngresoParqueaderoServicioImpl;
import testdatabuilder.IngresoParqueaderoEntityTestDataBuilder;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;
import testdatabuilder.VehiculoEntityTestDataBuilder;

@RunWith(SpringRunner.class)
public class IngresoParqueaderoServicioTest {
	
	private static final int ID_VEHICULO = 1;
	private static final Long ID_TIPO_VEHICULO_CARRO = 1L;
	private static final String PLACA = "XYZ-789";
	private static final int SIN_CILINDRAJE = 0;
	private static final Long ID_INGRESO_PARQUEADERO = 1L;
	
	@TestConfiguration
    static class IngresoParqueaderoServicioTestContextConfiguration {
		
        @Bean
        public IngresoParqueaderoServicio ingresoParqueaderoServicio() {
            return new IngresoParqueaderoServicioImpl();
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
	
	@Test
	public void consultarVehiculosEnParqueaderoTest() {
		
		//arrange
		List<VehiculoEnParqueaderoEntity> vehiculos = new ArrayList<>();
		
		vehiculos.add(new VehiculoEnParqueaderoEntity());
		vehiculos.add(new VehiculoEnParqueaderoEntity());
		
		when(vehiculoEnParqueaderoRepositorio.vehiculosEnParqueadero()).thenReturn(vehiculos);
		
		//act
		List<VehiculoEnParqueaderoEntity> vehiculosConsultados = ingresoParqueaderoServicio.consultarVehiculosEnParqueadero();
		
		//assert
		assertEquals(vehiculos.size(), vehiculosConsultados.size());
		assertEquals(vehiculos.get(0).getPlaca(), vehiculosConsultados.get(0).getPlaca());
		assertEquals(vehiculos.get(1).getTipo(), vehiculosConsultados.get(1).getTipo());
		
	}
	
	@Test
	public void consultarVehiculosEnParqueaderoNullTest() {
		
		//arrange
		List<VehiculoEnParqueaderoEntity> vehiculos = new ArrayList<>();
		
		when(vehiculoEnParqueaderoRepositorio.vehiculosEnParqueadero()).thenReturn(vehiculos);
		
		//act
		List<VehiculoEnParqueaderoEntity> vehiculosConsultados = ingresoParqueaderoServicio.consultarVehiculosEnParqueadero();
		
		//assert
		assertTrue(vehiculosConsultados.isEmpty());
		
	}
	
	@Test
	public void consultarIngresoExistenteTest() {
		
		//arrange
		IngresoParqueaderoEntity ingreso = new IngresoParqueaderoEntityTestDataBuilder()
				.conId(ID_INGRESO_PARQUEADERO)
				.conFechaInicio(Calendar.getInstance())
				.build();
		
		Optional<IngresoParqueaderoEntity> optIngreso = Optional.of(ingreso); 
		
		when(ingresoParqueaderoRepositorio.findById(anyLong())).thenReturn(optIngreso);
		
		//act
		IngresoParqueaderoEntity ingresoConsultado = ingresoParqueaderoServicio.consultarIngreso(ingreso.getId());
		
		//assert
		assertEquals(ingreso.getId(), ingresoConsultado.getId());
		assertEquals(ingreso.getVehiculo().getPlaca(), ingresoConsultado.getVehiculo().getPlaca());
		assertEquals(ingreso.getFechaInicio(), ingresoConsultado.getFechaInicio());
		
	}
	
	@Test
	public void consultarIngresoNoExistenteTest() {
		
		//arrange
		IngresoParqueaderoEntity ingreso = new IngresoParqueaderoEntityTestDataBuilder()
				.conId(ID_INGRESO_PARQUEADERO)
				.conFechaInicio(Calendar.getInstance())
				.build();
		
		Optional<IngresoParqueaderoEntity> optIngreso = Optional.empty();
		
		when(ingresoParqueaderoRepositorio.findById(anyLong())).thenReturn(optIngreso);
		
		//act
		IngresoParqueaderoEntity ingresoConsultado = ingresoParqueaderoServicio.consultarIngreso(ingreso.getId());
		
		//assert
		assertNull(ingresoConsultado);
		
	}

}
