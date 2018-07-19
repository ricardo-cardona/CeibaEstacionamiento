package parqueadero.parqueadero.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero.parqueadero.excepcion.TipoVehiculoExcepcion;
import parqueadero.parqueadero.persistencia.entidad.TipoVehiculoEntity;
import parqueadero.parqueadero.persistencia.repositorio.TipoVehiculoRepositorio;
import testdatabuilder.TipoVehiculoEntityTestDataBuilder;

@RunWith(SpringRunner.class)
public class TipoVehiculoServicioTest {
	
	private static final Long ID_TIPO_VEHICULO_CARRO = 1L;
	private static final Long ID_TIPO_VEHICULO_MOTO = 2L;
	private static final Long ID_TIPO_VEHICULO_INVALIDO = -1L;
	
	@TestConfiguration
    static class TipoVehiculoServicioTestContextConfiguration {
		
        @Bean
        public TipoVehiculoServicio tipoVehiculoServicio() {
            return new TipoVehiculoServicio();
        }
        
    }
 
    @Autowired
	private TipoVehiculoServicio tipoVehiculoServicio;
	
	@MockBean
	private TipoVehiculoRepositorio tipoVehiculoRepositorio;
    
	@Test
	public void consultarTipoVehiculoNullTest() {
		
		//arrange
		TipoVehiculoEntity tipoVehiculo = null;
		
		//act
		try {
			tipoVehiculoServicio.consultarTipoVehiculo(tipoVehiculo);
			fail();
		} catch(TipoVehiculoExcepcion e) {
			//assert
			assertEquals(TipoVehiculoServicio.SIN_TIPO_VEHICULO, e.getMessage());
		}
		
	}
	
	@Test
	public void consultarTipoVehiculoInvalidoTest() {
		
		//arrange
		TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntityTestDataBuilder()
				.conId(ID_TIPO_VEHICULO_INVALIDO)
				.build();
		
		//act
		try {
			tipoVehiculoServicio.consultarTipoVehiculo(tipoVehiculo);
			fail();
		} catch(TipoVehiculoExcepcion e) {
			//assert
			assertEquals(TipoVehiculoServicio.TIPO_VEHICULO_INCORRECTO, e.getMessage());
		}
		
	}
	
	@Test
	public void consultarTipoVehiculoValidoTest() {
		
		//arrange
		TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntityTestDataBuilder()
				.conId(ID_TIPO_VEHICULO_CARRO)
				.build();
		
		Optional<TipoVehiculoEntity> optTipoVehiculo = Optional.of(new TipoVehiculoEntityTestDataBuilder().conId(ID_TIPO_VEHICULO_CARRO).build());
		
		when(tipoVehiculoRepositorio.findById(tipoVehiculo.getId())).thenReturn(optTipoVehiculo);
		
		//act
		TipoVehiculoEntity tipoVehiculoConsultado = tipoVehiculoServicio.consultarTipoVehiculo(tipoVehiculo);
		
		//assert
		assertEquals(tipoVehiculo.getId(), tipoVehiculoConsultado.getId());
		
	}
	
	@Test
	public void consultarTiposVehiculoTest() {
		
		//arrange
		List<TipoVehiculoEntity> tipos = new ArrayList<>();
		
		tipos.add(new TipoVehiculoEntityTestDataBuilder().conId(ID_TIPO_VEHICULO_CARRO).build());
		tipos.add(new TipoVehiculoEntityTestDataBuilder().conId(ID_TIPO_VEHICULO_MOTO).build());
		
		when(tipoVehiculoRepositorio.findAll()).thenReturn(tipos);
		
		//act
		List<TipoVehiculoEntity> tiposConsultados = tipoVehiculoServicio.consultarTiposDeVehiculo();
		
		//assert
		assertEquals(tipos.size(), tiposConsultados.size());
		assertEquals(tipos.get(0).getId(), tiposConsultados.get(0).getId());
		assertEquals(tipos.get(1).getId(), tiposConsultados.get(1).getId());
		
	}
	
	@Test
	public void consultarTiposVehiculoVacioTest() {
		
		//arrange
		List<TipoVehiculoEntity> tipos = new ArrayList<>();
		
		when(tipoVehiculoRepositorio.findAll()).thenReturn(tipos);
		
		//act
		List<TipoVehiculoEntity> tiposConsultados = tipoVehiculoServicio.consultarTiposDeVehiculo();
		
		//assert
		assertTrue(tiposConsultados.isEmpty());
		
	}

}
