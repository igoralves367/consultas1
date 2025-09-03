package br.com.wakax.consulta.fipe.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.wakax.consulta.fipe.application.service.FipeService;
import br.com.wakax.consulta.fipe.domain.*;

@ExtendWith(MockitoExtension.class)
class FipeControllerTest {

  @Mock private FipeService fipeService;
  @InjectMocks private FipeController fipeController;

  private TipoVeiculo tipoVeiculo;
  private List<Marca> marcas;
  private ModelosResponse modelosResponse;

  @BeforeEach
  void setUp() {
    tipoVeiculo = TipoVeiculo.CARROS;

    marcas = Arrays.asList(new Marca("1", "Acura"), new Marca("2", "Agrale"));

    modelosResponse =
        new ModelosResponse(
            Arrays.asList(new Modelo(5585, "AMAROK CD2.0 16V TDI 4x2")),
            Arrays.asList(new Ano("2022-3", "2022 Diesel")));
  }

  @Test
  void deveConsultarMarcasComSucesso() {
    when(fipeService.consultaMarcas(any(TipoVeiculo.class))).thenReturn(marcas);

    List<Marca> response = fipeController.consultaMarcas(tipoVeiculo);

    assertNotNull(response);
    assertEquals(2, response.size());
    verify(fipeService, times(1)).consultaMarcas(tipoVeiculo);
  }

  @Test
  void deveConsultarModelosComSucesso() {
    when(fipeService.consultaModelos(any(TipoVeiculo.class), anyString()))
        .thenReturn(modelosResponse);

    ModelosResponse response = fipeController.consultaModelos(tipoVeiculo, "59");

    assertNotNull(response);
    assertEquals(1, response.modelos().size());
    verify(fipeService, times(1)).consultaModelos(tipoVeiculo, "59");
  }
}
